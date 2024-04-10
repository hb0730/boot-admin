package com.hb0730.excel;

import cn.hutool.core.io.FileUtil;
import com.hb0730.excel.model.TestData;
import org.junit.jupiter.api.Assertions;

import java.util.List;

class ExcelImportTest {

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("空导入")
    void emptyImportTest() {
        try (ExcelImport excelImport = new ExcelImport(FileUtil.file("excel/emptyExport.xlsx"), 2)) {
            List<TestData> dataList = excelImport.getDataList(TestData.class);
            Assertions.assertEquals(0, dataList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("简单导入")
    void simpleImportTest() {
        try (ExcelImport excelImport = new ExcelImport(FileUtil.file("excel/simpleExport.xlsx"), 2)) {
            List<TestData> dataList = excelImport.getDataList(TestData.class);
            Assertions.assertEquals(3, dataList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("多Sheet导入")
    void multiSheetImportTest() {
        try (ExcelImport excelImport = new ExcelImport(FileUtil.file("excel/multiSheetExport.xlsx"), 2)) {
            List<TestData> dataList = excelImport.getDataList(TestData.class);
            Assertions.assertEquals(3, dataList.size());
            excelImport.setSheet("1", 2);
            List<TestData> dataList2 = excelImport.getDataList(TestData.class);
            Assertions.assertEquals(3, dataList2.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("自定义Sheet导入")
    void customSheetImportTest() {
        try (ExcelImport excelImport = new ExcelImport(FileUtil.file("excel/customSheetExport.xlsx"), 2, "自定义1")) {
            List<TestData> dataList = excelImport.getDataList(TestData.class);
            Assertions.assertEquals(3, dataList.size());
            excelImport.setSheet("自定义2", 2);
            List<TestData> dataList2 = excelImport.getDataList(TestData.class);
            Assertions.assertEquals(3, dataList2.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}