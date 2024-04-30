package com.hb0730.excel;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.hb0730.base.exception.ExcelException;
import com.hb0730.excel.annotation.ExcelField;
import com.hb0730.excel.filedtype.FieldType;
import com.hb0730.excel.util.ExcelFieldUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * excel导出
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/9
 */
@Slf4j
public class ExcelExport implements Closeable {
    @Getter
    private Workbook workbook;
    private Sheet sheet;
    private List<Object[]> annotationList;
    private final Map<Class<? extends FieldType>, FieldType> fieldTypes;
    @Setter
    private Map<String, CellStyle> styles;

    public ExcelExport(String title, Class<?> clazz) {
        this(null, title, clazz);
    }

    public ExcelExport(String sheetName, String title, Class<?> clazz) {
        this(null, sheetName, title, clazz);
    }

    public ExcelExport(Workbook workbook, String sheetName, String title, Class<?> clazz) {
        this.fieldTypes = new HashMap<>();
        if (null != workbook) {
            this.workbook = workbook;
        }
        if (null == this.workbook) {
            this.workbook = createWorkbook();
        }
        createSheet(sheetName, title, clazz);
    }

    public ExcelExport(String title, List<String> headerList, List<Integer> headerWidthList) {
        this(null, title, headerList, headerWidthList);
    }

    public ExcelExport(String sheetName, String title, List<String> headerList, List<Integer> headerWidthList) {
        this(null, sheetName, title, headerList, headerWidthList);
    }

    public ExcelExport(Workbook workbook, String sheetName, String title, List<String> headerList, List<Integer> headerWidthList) {
        this.fieldTypes = new HashMap<>();
        if (null != workbook) {
            this.workbook = workbook;
        }
        if (null == this.workbook) {
            this.workbook = createWorkbook();
        }
        createSheet(sheetName, title, headerList, headerWidthList);
    }


    /**
     * 创建workbook
     *
     * @return workbook
     */
    public Workbook createWorkbook() {
        return new SXSSFWorkbook(500);
    }

    /**
     * 创建sheet
     *
     * @param sheetName sheet名称
     * @param title     标题
     * @param clazz     类
     */
    public void createSheet(String sheetName, String title, Class<?> clazz) {
        this.annotationList = ExcelFieldUtil.getAnnotationValue(clazz);

        List<String> headerList = new ArrayList<>();
        List<Integer> headerWidthList = new ArrayList<>();
        for (Object[] objects : annotationList) {
            ExcelField excelField = (ExcelField) objects[0];
            headerList.add(excelField.title());
            headerWidthList.add(excelField.width());
        }
        createSheet(sheetName, title, headerList, headerWidthList);
    }

    /**
     * 创建sheet
     *
     * @param sheetName       sheet名称
     * @param title           标题
     * @param headerList      表头[字段名,字段名,字段名]
     * @param headerWidthList 表头宽度,如果为0则隐藏
     */
    public void createSheet(String sheetName, String title, List<String> headerList, List<Integer> headerWidthList) {
        sheetName = StrUtil.blankToDefault(sheetName, StrUtil.blankToDefault(title, "Sheet1"));
        this.sheet = workbook.createSheet(sheetName);
        // 创建样式
        this.styles = createStyles(workbook);

        int rowIndex = 0;
        Row headerRow = null;
        // 创建标题
        if (StrUtil.isNotBlank(title)) {
            headerRow = this.sheet.createRow(rowIndex++);
            headerRow.setHeightInPoints(30.0f);
            Cell titleCell = headerRow.createCell(0);
            titleCell.setCellValue(title);
            titleCell.setCellStyle(styles.get("header"));
            this.sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(headerRow.getRowNum(), headerRow.getRowNum(), headerRow.getRowNum(), headerList.size() - 1));
        }
        if (CollectionUtil.isEmpty(headerList)) {
            throw new ExcelException("headerList is empty");
        }

        // 创建表头
        headerRow = this.sheet.createRow(rowIndex++);
        headerRow.setHeightInPoints(16.0f);

        for (int i = 0; i < headerList.size(); i++) {
            Cell cell = headerRow.createCell(i);

            cell.setCellStyle(styles.get("header"));

            cell.setCellValue(headerList.get(i));
        }

        boolean isDefWidth = headerWidthList != null && headerWidthList.size() == headerList.size();

        for (int i = 0; i < headerList.size(); i++) {
            int colWidth = -1;

            if (isDefWidth) {
                colWidth = headerWidthList.get(i);
            }

            if (colWidth == -1) {
                colWidth = this.sheet.getColumnWidth(i) * 2;
                colWidth = Math.max(colWidth, 3000);
            }

            if (colWidth == 0) {
                this.sheet.setColumnHidden(i, true);
            } else {
                this.sheet.setColumnWidth(i, colWidth);
            }
        }

        log.debug("create sheet [{}] success", sheetName);
    }

    /**
     * 新增一行
     *
     * @return .
     */
    public Row addRow() {
        return sheet.createRow(this.sheet.getLastRowNum() + 1);
    }

    /**
     * 新增一个单元格
     *
     * @param row   行
     * @param index 列
     * @param value 值
     * @return .
     */
    public Cell addCell(Row row, int index, Object value) {
        return addCell(row, index, value, ExcelField.Align.AUTO, FieldType.class, null);
    }

    /**
     * 新增一个单元格
     *
     * @param row        行
     * @param index      列
     * @param value      值
     * @param align      对齐方式
     * @param fieldType  字段类型
     * @param dataFormat 数据格式
     * @return .
     */
    public Cell addCell(Row row, int index, Object value, ExcelField.Align align,
                        Class<? extends FieldType> fieldType, String dataFormat) {
        Cell cell = row.createCell(index);
        String defaultDataFormat = null;

        try {
            if (null == value) {
                cell.setCellValue("");
            } else {
                if (null != fieldType && fieldType != FieldType.class) {
                    FieldType type = getFieldType(fieldType);
                    cell.setCellValue(type.setValue(value));
                    defaultDataFormat = type.getDataFormat();
                }

                if (value instanceof String) {
                    cell.setCellValue((String) value);
                } else if (value instanceof Integer) {
                    cell.setCellValue((Integer) value);
                    defaultDataFormat = "0";
                } else if (value instanceof Long) {
                    cell.setCellValue((Long) value);
                    defaultDataFormat = "0";
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    defaultDataFormat = "0.00";
                } else if (value instanceof Float) {
                    cell.setCellValue((Float) value);
                    defaultDataFormat = "0.00";
                } else if (value instanceof BigDecimal) {
                    cell.setCellValue(((BigDecimal) value).doubleValue());
                } else if (value instanceof Date) {
                    cell.setCellValue((Date) value);
                    defaultDataFormat = "yyyy-MM-dd";
                }
            }
            CellStyle cellStyle = this.styles.get("data_column_" + index);
            if (null == cellStyle) {
                cellStyle = this.workbook.createCellStyle();
                String styleIndex = align.ordinal() >= 1 && align.ordinal() <= 3 ? align.ordinal() + "" : "";
                cellStyle.cloneStyleFrom(this.styles.get("data" + styleIndex));
                if (StrUtil.isNotBlank(dataFormat)) {
                    defaultDataFormat = dataFormat;
                }

                if (defaultDataFormat == null) {
                    defaultDataFormat = "@";
                }
                cellStyle.setDataFormat(this.workbook.createDataFormat().getFormat(defaultDataFormat));
                this.styles.put("data_column_" + index, cellStyle);
            }
            cell.setCellStyle(cellStyle);
        } catch (Exception e) {
            log.info("Set cell value [{},{}] error: {}", row.getRowNum(), index, e.toString());
            cell.setCellValue(StrUtil.EMPTY);
        }
        return cell;
    }

    /**
     * 创建样式
     *
     * @param workbook workbook 对象
     * @return 样式 map
     */
    protected Map<String, CellStyle> createStyles(Workbook workbook) {
        Map<String, CellStyle> styles = new HashMap<>(4);
        // 标题样式
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        Font titleFont = workbook.createFont();
        titleFont.setFontName("Arial");
        titleFont.setFontHeightInPoints((short) 16);
        titleFont.setBold(true);
        style.setFont(titleFont);
        styles.put("title", style);
        // 默认样式
        style = workbook.createCellStyle();
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font dataFont = workbook.createFont();
        dataFont.setFontName("Arial");
        dataFont.setFontHeightInPoints((short) 10);
        style.setFont(dataFont);
        styles.put("data", style);
        //这里对于 @ExcelField 中的属性Align进行了处理
        // 样式1
        style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.cloneStyleFrom(styles.get("data"));
        styles.put("data1", style);
        // 样式2
        style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.LEFT);
        style.cloneStyleFrom(styles.get("data"));
        styles.put("data2", style);
        // 样式3
        style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.RIGHT);
        style.cloneStyleFrom(styles.get("data"));
        styles.put("data3", style);

        // header 样式
        style = workbook.createCellStyle();
        style.cloneStyleFrom((CellStyle) styles.get("data"));
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font headerFont = workbook.createFont();
        headerFont.setFontName("Arial");
        headerFont.setFontHeightInPoints((short) 10);
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(headerFont);
        styles.put("header", style);

        return styles;
    }


    /**
     * 设置数据
     *
     * @param data 数据
     * @param <E>  泛型
     */
    public <E> ExcelExport setData(List<E> data) {
        if (CollectionUtil.isEmpty(data)) {
            return this;
        }
        for (E e : data) {
            StringBuilder sb = new StringBuilder();
            Row row = addRow();
            for (int i = 0; i < annotationList.size(); i++) {
                Object[] annotation = annotationList.get(i);
                ExcelField excelField = (ExcelField) annotation[0];
                Object value = null;
                if (StrUtil.isNotBlank(excelField.attrName())) {
                    value = ReflectUtil.getFieldValue(e, excelField.attrName());
                    if (null == value) {
                        value = ReflectUtil.invoke(e, "get" + StrUtil.upperFirst(excelField.attrName()));
                    }
                } else if (annotation[1] instanceof Field field) {
                    value = ReflectUtil.getFieldValue(e, field);
                } else if (annotation[1] instanceof Method method) {
                    value = ReflectUtil.invoke(e, method);
                }
                addCell(row, i, value, excelField.align(), excelField.fieldType(), excelField.dataFormat());
                sb.append(value).append(",");
            }
            log.debug("write data [{}] success", sb);
        }
        return this;
    }

    /**
     * 写入数据,不会关闭流
     *
     * @param os 输出流
     * @return .
     */
    public ExcelExport write(OutputStream os) {
        try {
            this.workbook.write(os);
        } catch (IOException var3) {
            log.error("write excel error: {}", var3.getMessage());
            throw new ExcelException("write excel error: " + var3.getMessage());
        }
        return this;
    }

    /**
     * 写入数据
     *
     * @param file 文件
     * @return .
     */
    public ExcelExport write(File file) {
        try (BufferedOutputStream outputStream = FileUtil.getOutputStream(file)) {
            this.workbook.write(outputStream);
        } catch (IOException var3) {
            log.error("write excel error: {}", var3.getMessage());
            throw new ExcelException("write excel error: " + var3.getMessage());
        }
        return this;
    }

    /**
     * 写入数据
     *
     * @param filename 文件名
     * @return .
     */
    public ExcelExport write(String filename) {
        File file = FileUtil.file(filename);
        return write(file);
    }

    /**
     * 获取字段类型
     *
     * @param fieldType 字段类型
     * @return .
     */
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
        if (workbook instanceof SXSSFWorkbook) {
            ((SXSSFWorkbook) workbook).dispose();
        }
        workbook.close();
    }
}