package com.hb0730.sys.system.service;

import com.hb0730.base.BaseTest;
import com.hb0730.base.utils.DateUtil;
import com.hb0730.rpc.sys.system.domain.query.NoticeQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class INoticeServiceTest extends BaseTest {
    
    @Test
    @DisplayName("分页查询")
    void page(@Autowired INoticeService noticeService) {

        NoticeQuery noticeQuery = new NoticeQuery();
        noticeQuery.setNoticeTimeEnd(
                DateUtil.string2Date("2024-03-12")
        );
        noticeQuery.setNoticeTimeStart(
                DateUtil.string2Date("2024-04-2")
        );
        noticeQuery.setCurrent(1);
        noticeQuery.setSize(10);
        log.info("分页查询:{}", noticeService.page(noticeQuery));

    }
}