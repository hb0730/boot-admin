package com.hb0730.boot.admin.project.dict.service.impl;

import com.hb0730.boot.admin.project.dict.service.ISystemDictService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class SystemDictServiceImplTest {
    @Autowired
    private ISystemDictService systemDictService;
    @Test
    public void getDictForMap() {
        Map<String, List> dictForMap = systemDictService.getDictForMap();
    }
}
