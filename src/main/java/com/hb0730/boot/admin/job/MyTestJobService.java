package com.hb0730.boot.admin.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/6/13
 */
@Component
@Slf4j
public class MyTestJobService {

    public void start(String params) {
        log.info("MyTestJobService start {}", params);
    }
}
