package com.hb0730.rpc.sys.system.service;

import com.hb0730.common.api.JR;
import com.hb0730.common.api.JsfPage;
import com.hb0730.rpc.sys.system.domain.NoticeDto;
import com.hb0730.rpc.sys.system.domain.query.NoticeQuery;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/2
 */
public interface NoticeRpcService {
    /**
     * 分页查询
     *
     * @param query .
     * @return .
     */
    default JR<JsfPage<NoticeDto>> page(NoticeQuery query) {
        return null;
    }

    /**
     * 添加
     *
     * @param noticeDto .
     * @return .
     */
    default JR<String> save(NoticeDto noticeDto) {
        return null;
    }

    /**
     * 更新
     *
     * @param noticeDto .
     * @return .
     */
    default JR<String> updateById(NoticeDto noticeDto) {
        return null;
    }

    /**
     * 删除
     *
     * @param ids .
     * @return .
     */
    default JR<String> deleteByIds(List<String> ids) {
        return null;
    }

}
