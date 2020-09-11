package com.hb0730.boot.admin.excel.utils;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.google.common.collect.Lists;
import com.hb0730.boot.admin.project.system.post.model.dto.PostExcelDTO;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

public class ExcelUtilsTest {

    @Test
    public void writeTest() throws IOException {
        ExcelProperties properties = new ExcelProperties("C:\\Users\\12780\\Downloads", "test", ExcelTypeEnum.XLS, PostExcelDTO.class);
        OutputStream outputStream = ExcelUtils.getOutputStream(properties);
        List<PostExcelDTO> data = Lists.newArrayList();
        data.add(new PostExcelDTO("S001", "全栈开发", 1, new Date()));
        data.add(new PostExcelDTO("S002", "全栈开发", 1, new Date()));
        data.add(new PostExcelDTO("S003", "全栈开发", 1, new Date()));
        data.add(new PostExcelDTO("S004", "全栈开发", 1, new Date()));
        ExcelUtils.write(outputStream,data,ExcelTypeEnum.XLS,PostExcelDTO.class);
    }
}
