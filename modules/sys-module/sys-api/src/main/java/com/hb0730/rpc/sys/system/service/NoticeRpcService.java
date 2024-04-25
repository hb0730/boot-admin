package com.hb0730.rpc.sys.system.service;

import com.hb0730.common.api.JR;
import com.hb0730.common.api.JsfPage;
import com.hb0730.rpc.sys.system.domain.NoticeDto;
import com.hb0730.rpc.sys.system.domain.query.NoticeQuery;

import java.util.Collections;
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
        return JR.fail("暂未实现");
    }

    /**
     * 查询
     *
     * @param query .
     * @return .
     */
    default JR<List<NoticeDto>> list(NoticeQuery query) {
        return JR.fail("暂未实现");
    }

    /**
     * 添加
     *
     * @param noticeDto .
     * @return .
     */
    default JR<String> save(NoticeDto noticeDto) {
        return JR.fail("暂未实现");
    }

    /**
     * 更新
     *
     * @param noticeDto .
     * @return .
     */
    default JR<String> updateById(NoticeDto noticeDto) {
        return JR.fail("暂未实现");
    }

    /**
     * 删除
     *
     * @param ids .
     * @return .
     */
    default JR<String> deleteByIds(List<String> ids) {
        return JR.fail("暂未实现");
    }

    /**
     * 通过id关闭通知
     *
     * @param id .
     * @return .
     */
    default JR<String> closeNoticeById(String id) {
        return closeNoticeByIds(Collections.singletonList(id));
    }

    /**
     * 通过id关闭通知
     *
     * @param ids .
     * @return .
     */
    default JR<String> closeNoticeByIds(List<String> ids) {
        return JR.fail("暂未实现");
    }

}
