package com.hb0730.excel;

import cn.hutool.core.bean.BeanDesc;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.hb0730.base.exception.ExcelException;
import com.hb0730.excel.annotation.ExcelField;
import com.hb0730.excel.filedtype.FieldType;
import com.hb0730.excel.util.ExcelFieldUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * excel导入,支持xls和xlsx
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/9
 */
@Slf4j
public class ExcelImport implements Closeable {

    /**
     * Excel工作簿
     */
    @Getter
    private Workbook workbook;
    private Sheet sheet;
    private int headerNum;
    private final Map<Class<? extends FieldType>, FieldType> fieldTypes = new HashMap<>();

    /**
     * 构造,读取第一个sheet的第一个行为表头<br>
     * <strong>此方法不会关闭流</strong>
     *
     * @param file 文件
     * @throws FileNotFoundException 文件未找到异常
     */
    public ExcelImport(File file) throws IOException {
        this(file, 0);
    }

    /**
     * 构造,读取第一个sheet<br>
     * <strong>此方法不会关闭流</strong>
     *
     * @param file    文件
     * @param headNum 表头行数
     * @throws FileNotFoundException 文件未找到异常
     */
    public ExcelImport(File file, int headNum) throws IOException {
        this(file, headNum, "0");
    }

    /**
     * 构造
     * <strong>此方法不会关闭流</strong>
     *
     * @param file             文件
     * @param headNum          表头行数
     * @param sheetIndexOrName sheet的名字或者序号
     * @throws FileNotFoundException 文件未找到异常
     */
    public ExcelImport(File file, int headNum, String sheetIndexOrName) throws IOException {
        this(file.getName(), new FileInputStream(file), headNum, sheetIndexOrName);
    }

    /**
     * 构造,读取第一个sheet的第一个行为表头<br>
     * <strong>此方法不会关闭流</strong>
     *
     * @param filename 文件名，主要用于识别文件类型（xls或xlsx）
     * @param is       {@link InputStream}
     */
    public ExcelImport(String filename, InputStream is) throws IOException {
        this(filename, is, 0);
    }

    /**
     * 构造,读取第一个sheet<br>
     * <strong>此方法不会关闭流</strong>
     *
     * @param filename 文件名,主要用于识别文件类型（xls或xlsx）
     * @param is       {@link InputStream}
     * @param headNum  表头行数
     */
    public ExcelImport(String filename, InputStream is, int headNum) throws IOException {
        this(filename, is, headNum, "0");
    }

    /**
     * 构造
     * <strong>此方法不会关闭流</strong>
     *
     * @param filename         文件名,主要用于识别文件类型（xls或xlsx）
     * @param is               {@link InputStream}
     * @param headNum          表头行数
     * @param sheetIndexOrName sheet的名字或者序号
     */
    public ExcelImport(String filename, InputStream is, int headNum, String sheetIndexOrName) throws IOException {
        if (StrUtil.isBlank(filename)) {
            throw new ExcelException("File name is blank !");
        }
        if (!filename.toLowerCase().endsWith(".xls") && !filename.toLowerCase().endsWith(".xlsx")) {
            throw new ExcelException("File format error!");
        }
        this.workbook = WorkbookFactory.create(is);
        setSheet(sheetIndexOrName, headNum);
        log.debug("Init Excel Import [{}] success!", filename);
    }

    /**
     * 设置读取的Sheet
     *
     * @param sheetIndexOrName sheet的名字或者序号
     * @param headerNum        表头行数
     */
    public void setSheet(String sheetIndexOrName, int headerNum) {
        Integer index = Convert.toInt(sheetIndexOrName);
        if (null == index) {
            this.sheet = this.workbook.getSheet(sheetIndexOrName);
        } else {
            this.sheet = this.workbook.getSheetAt(index);
        }
        if (null == this.sheet) {
            throw new ExcelException("No sheet with index or name: " + sheetIndexOrName);
        }
        this.headerNum = headerNum;
    }

    /**
     * 获取数据开始行
     *
     * @return 数据开始行
     */
    protected Integer getDataRowNum() {
        return this.headerNum;
    }

    /**
     * 获取最后一个数据行号
     *
     * @return 最后一个数据行号
     */
    protected Integer getLastDataRowNum() {
        return this.sheet.getLastRowNum() + 1;
    }

    /**
     * 获取cell值
     *
     * @param row   行
     * @param index 列
     * @return 值
     */
    public Object getCellValue(Row row, int index) {
        if (null == row) {
            return null;
        }
        Object val = "";
        try {
            Cell cell = row.getCell(index);
            CellType cellType = cell.getCellType();
            // 根据cell中的类型来输出数据
            if (cellType == CellType.STRING) {
                // 字符串
                val = cell.getStringCellValue();
            } else if (cellType == CellType.NUMERIC) {
                // 数字
                //区分日期和数字，
                double numericCellValue = cell.getNumericCellValue();
                // 判断是否是日期
                if (DateUtil.isCellDateFormatted(cell)) {
                    val = DateUtil.getJavaDate(numericCellValue);
                } else if ((Double) numericCellValue % 1.0 > 0.0) {
                    val = (new DecimalFormat("0.00")).format(numericCellValue);
                } else {
                    val = (new DecimalFormat("0")).format(numericCellValue);
                }
            } else if (cellType == CellType.FORMULA) {
                // 公式
                try {
                    val = cell.getStringCellValue();
                } catch (Exception e) {
                    FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
                    evaluator.evaluateFormulaCell(cell);
                    CellValue cellValue = evaluator.evaluate(cell);
                    val = switch (cellValue.getCellType()) {
                        case NUMERIC -> cellValue.getNumberValue();
                        case STRING -> cellValue.getStringValue();
                        case BOOLEAN -> cellValue.getBooleanValue();
                        case ERROR -> ErrorEval.getText(cellValue.getErrorValue());
                        default -> cell.getCellFormula();
                    };
                }
            } else if (cell.getCellType() == CellType.BOOLEAN) {
                val = cell.getBooleanCellValue();
            } else if (cell.getCellType() == CellType.ERROR) {
                val = cell.getErrorCellValue();
            }
            return val;
        } catch (Exception e) {
            return val;
        }
    }


    /**
     * 获取数据列表
     *
     * @param clazz 类
     * @param <E>   泛型
     * @return 数据列表
     */
    public <E> List<E> getDataList(Class<E> clazz) {
        List<Object[]> annotationList = ExcelFieldUtil.getAnnotationValue(clazz);
        List<E> dataList = Lists.newArrayList();

        //读取数据
        for (int i = getDataRowNum(); i < getLastDataRowNum(); i++) {
            StringBuilder sb = new StringBuilder();
            Row row = this.sheet.getRow(i);
            if (null == row) {
                continue;
            }
            // 实例化对象
            E obj = ReflectUtil.newInstance(clazz);

            for (int e = 0; e < annotationList.size(); e++) {
                Object[] objects = annotationList.get(e);
                ExcelField excelField = (ExcelField) objects[0];
                int columnIndex = excelField.column() != -1 ? excelField.column() : e;
                Object val = getCellValue(row, columnIndex);
                if (val != null) {
                    Class<?> valType = Class.class;
                    if (objects[1] instanceof Field) {
                        valType = ((Field) objects[1]).getType();
                    } else if (objects[1] instanceof Method method) {
                        if ("get".equals(method.getName().substring(0, 3))) {
                            valType = method.getReturnType();
                        } else if ("set".equals(method.getName().substring(0, 3))) {
                            valType = ((Method) objects[1]).getParameterTypes()[0];
                        }
                    }
                    try {

                        FieldType ft = null;
                        // 如果有数据转换器，执行转换
                        if (StrUtil.isNotBlank(excelField.attrName())) {
                            if (excelField.fieldType() != FieldType.class) {
                                ft = this.getFieldType(excelField.fieldType());
                                val = ft.getValue(Convert.toStr(val));
                            }
                        } else {
                            if (valType == String.class) {
                                String str = Convert.toStr(val);
                                if (StrUtil.endWith(str, ".0")) {
                                    val = StrUtil.subBefore(str, ".", false);
                                } else {
                                    val = str;
                                }
                            } else if (valType == Integer.class) {
                                val = Convert.toInt(val);
                            } else if (valType == Long.class) {
                                val = Convert.toLong(val);
                            } else if (valType == Double.class) {
                                val = Convert.toDouble(val);
                            } else if (valType == Float.class) {
                                val = Convert.toFloat(val);
                            } else if (valType == Short.class) {
                                val = Convert.toShort(val);
                            } else if (valType == Character.class) {
                                val = Convert.toChar(val);
                            } else if (valType == Boolean.class) {
                                val = Convert.toBool(val);
                            } else if (valType == java.util.Date.class) {
                                if (val instanceof String) {
                                    val = Convert.toDate(val);
                                } else if (val instanceof Double) {
                                    val = DateUtil.getJavaDate((Double) val);
                                }
                            } else if (excelField.fieldType() != FieldType.class) {
                                ft = this.getFieldType(excelField.fieldType());
                                val = ft.getValue(Convert.toStr(val));
                            }
                        }

                    } catch (Exception ex) {
                        log.error("Get value [{}] error on row [{}] column [{}]:{}", val, i, columnIndex, ex.getMessage());
                        val = null;
                    }
                    if (StrUtil.isNotBlank(excelField.attrName())) {
                        BeanDesc beanDesc = new BeanDesc(clazz);
                        Method method = beanDesc.getSetter(excelField.attrName());
                        if (null != method) {
                            ReflectUtil.invoke(e, method, val);
                        }
                    } else if (objects[1] instanceof Field) {
                        ReflectUtil.setFieldValue(obj, (Field) objects[1], val);
                    } else if (objects[1] instanceof Method) {
                        ReflectUtil.invoke(obj, (Method) objects[1], val);
                    }
                }
                sb.append(val).append(StrUtil.COMMA);
            }
            dataList.add(obj);
            log.debug("Read line [{}] {}", i, sb);
        }
        log.debug("Read success: [{}] lines", dataList.size());
        return dataList;
    }

    private FieldType getFieldType(Class<? extends FieldType> fieldType) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        FieldType ft = (FieldType) this.fieldTypes.get(fieldType);
        if (ft == null) {
            ft = (FieldType) fieldType.getDeclaredConstructor().newInstance();
            this.fieldTypes.put(fieldType, ft);
        }

        return ft;
    }

    @Override
    public void close() throws IOException {
        this.fieldTypes.clear();
        if (null != this.workbook) {
            this.workbook.close();
        }
    }
}