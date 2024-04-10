package com.hb0730.excel;

import cn.hutool.core.date.DateUtil;
import com.hb0730.excel.model.TestData;
import com.hb0730.excel.model.TestData2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

class ExcelExportTest {
    public List<TestData> data() {
        return List.of(new TestData("张三", 18, DateUtil.parse("2024-04-10", "yyyy-MM-dd"), "北京",
                        new BigDecimal("20000.021")),
                new TestData("李四", 20, DateUtil.parse("2024-04-10", "yyyy-MM-dd"), "上海",
                        new BigDecimal("30000.021")),
                new TestData("王五", 22, DateUtil.parse("2024-04-10", "yyyy-MM-dd"), "广州",
                        new BigDecimal("40000.021")));
    }

    @Test
    @DisplayName("空导出")
    public void emptyExportTest() {
        try (ExcelExport excelExport = new ExcelExport("空导出", TestData.class)) {
            excelExport.write("excel/emptyExport.xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("简单导出")
    public void simpleExportTest() {
        try (ExcelExport excelExport = new ExcelExport("简单导出", TestData.class)) {
            excelExport.setData(data());
            excelExport.write("excel/simpleExport.xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("多Sheet导出")
    public void multiSheetExportTest() {
        try (ExcelExport excelExport = new ExcelExport("多Sheet导出", TestData.class)) {
            excelExport.setData(data());
            excelExport.createSheet("Sheet2", "Sheet2", TestData.class);
            excelExport.setData(data());
            excelExport.write("excel/multiSheetExport.xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("ExcelFields导出")
    public void excelFieldsExportTest() {
        try (ExcelExport excelExport = new ExcelExport("ExcelFields导出", TestData2.class)) {
            excelExport.setData(data());
            excelExport.write("excel/excelFieldsExport.xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("自定义Sheet导出")
    public void customSheetExportTest() {
        try (ExcelExport excelExport = new ExcelExport("自定义1", "自定义Sheet导出", TestData.class)) {
            excelExport.setData(data());
            excelExport.createSheet("自定义2", "Sheet2", TestData.class);
            excelExport.setData(data());
            excelExport.write("excel/customSheetExport.xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}