package com.hb0730.boot.admin.commons.utils.excel;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.google.common.collect.Maps;
import com.hb0730.boot.admin.commons.utils.bean.BeanUtils;
import com.hb0730.boot.admin.project.monitor.job.model.dto.JobExportDto;
import com.hb0730.boot.admin.project.monitor.job.model.entity.SystemJobEntity;
import com.hb0730.boot.admin.project.monitor.job.service.ISystemJobService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class ExcelUtilsTest {
    @Autowired
    private ISystemJobService systemJobService;

    @Test
    public void write() throws IOException {
        List<SystemJobEntity> list = systemJobService.list();
        List<JobExportDto> exportDtos = BeanUtils.transformFromInBatch(list, JobExportDto.class);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        ExcelProperties properties = new ExcelProperties("E:/logHome", "test", ExcelTypeEnum.XLSX, JobExportDto.class);
        ExcelUtils.write(ExcelUtils.getOutputStream(properties), exportDtos, ExcelTypeEnum.XLSX, JobExportDto.class);
    }

    @Test
    public void writeSheet() throws FileNotFoundException {
        List<SystemJobEntity> list = systemJobService.list();
        List<JobExportDto> exportDtos = BeanUtils.transformFromInBatch(list, JobExportDto.class);
        Map<String, List<JobExportDto>> maps = Maps.newHashMap();
        maps.put("sheet1", exportDtos);
        maps.put("sheet2", exportDtos);
        ExcelProperties properties = new ExcelProperties("E:/logHome", "test", ExcelTypeEnum.XLSX, JobExportDto.class);
        ExcelUtils.writeSheet(ExcelUtils.getOutputStream(properties), maps, ExcelTypeEnum.XLSX, JobExportDto.class);

    }
}
