package com.hb0730.basic.service;

import com.hb0730.common.api.JsfPage;
import com.hb0730.rpc.basic.domain.BasNoticeDto;
import com.hb0730.rpc.basic.domain.query.BasNoticeQuery;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/7
 */
public interface IBasNoticeService {

    /**
     * 统计未读的通过
     *
     * @param userId .
     * @return .
     */
    Integer countUnRead(String userId);

    /**
     * 分页查询公告
     *
     * @param query .
     * @return .
     */
    JsfPage<BasNoticeDto> page(BasNoticeQuery query);

    /**
     * 读公告
     *
     * @param userId   .
     * @param noticeId .
     */
    void reads(String userId, List<String> noticeId);
}
