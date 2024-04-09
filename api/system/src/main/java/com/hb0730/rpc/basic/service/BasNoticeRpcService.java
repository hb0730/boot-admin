package com.hb0730.rpc.basic.service;

import com.hb0730.commons.JR;
import com.hb0730.commons.JsfPage;
import com.hb0730.rpc.basic.domain.BasNoticeDto;
import com.hb0730.rpc.basic.domain.query.BasNoticeQuery;

import java.util.List;

/**
 * 公告 RPC 服务
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/7
 */
public interface BasNoticeRpcService {

    /**
     * 统计未读公告
     *
     * @param userId .
     * @return .
     */
    default JR<Integer> countUnRead(String userId) {
        return JR.fail("暂未实现");
    }

    /**
     * 分页查询
     *
     * @param query .
     * @return .
     */
    default JR<JsfPage<BasNoticeDto>> page(BasNoticeQuery query) {
        return JR.fail("暂未实现");
    }

    /**
     * 读公告
     *
     * @param userId    .
     * @param noticeIds .
     * @return .
     */
    default JR<String> read(String userId, List<String> noticeIds) {
        return JR.fail("暂未实现");
    }
}
