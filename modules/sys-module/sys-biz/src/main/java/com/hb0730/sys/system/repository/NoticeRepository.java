package com.hb0730.sys.system.repository;

import com.hb0730.sys.system.domain.SysNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/2
 */
@Repository
public interface NoticeRepository extends JpaRepository<SysNotice, String>, JpaSpecificationExecutor<SysNotice> {
    /**
     * 统计不在ids中的数据
     *
     * @param ids .
     * @return .
     */
    Integer countByIdNotInAndEnabledIsTrue(List<String> ids);

    /**
     * 获取ID
     *
     * @param ids 需要排除的ID
     * @return .
     */
    @Query("SELECT id from SysNotice where enabled = true and id not in ?1")
    List<String> getIdsByIdNotIn(List<String> ids);

    /**
     * 统计当前已有的公告
     *
     * @return .
     */
    int countByEnabledIsTrue();

    /**
     * 获取所有ID
     *
     * @return .
     */
    @Query("SELECT id from SysNotice where enabled = true")
    List<String> getIdsNotices();
}
