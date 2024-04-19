package com.hb0730.sys.system.service;

import com.hb0730.rpc.sys.system.domain.query.NoticeQuery;
import com.hb0730.sys.system.domain.SysNotice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/2
 */
public interface INoticeService {
    /**
     * 统计不在ids中的数据
     *
     * @param ids .
     * @return .
     */
    Integer countByIdNotIn(List<String> ids);

    /**
     * 获取不在ids之列的ID集合
     *
     * @param ids .
     * @return .
     */
    List<String> getIdsByIdNotIn(List<String> ids);

    /**
     * 统计所有的公告
     *
     * @return .
     */
    int countNotices();

    /**
     * 获取ID集合
     *
     * @return .
     */
    List<String> getIdsNotices();

    /**
     * 分页查询
     *
     * @param specification .
     * @param pageable      .
     * @return .
     */
    Page<SysNotice> page(Specification<SysNotice> specification, Pageable pageable);

    /**
     * 分页查询
     *
     * @param query .
     * @return .
     */
    Page<SysNotice> page(NoticeQuery query);

    /**
     * 保存
     *
     * @param sysNotice .
     */
    void save(SysNotice sysNotice);

    /**
     * 根据id更新
     *
     * @param sysNotice .
     */
    void updateById(SysNotice sysNotice);

    /**
     * 根据ID删除
     *
     * @param ids .
     */
    void deleteByIds(List<String> ids);

}
