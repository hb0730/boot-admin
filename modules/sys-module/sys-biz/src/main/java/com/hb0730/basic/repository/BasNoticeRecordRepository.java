package com.hb0730.basic.repository;

import com.hb0730.basic.domain.BasNoticeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/7
 */
@Repository
public interface BasNoticeRecordRepository extends JpaRepository<BasNoticeRecord, String>, JpaSpecificationExecutor<BasNoticeRecord> {

    /**
     * 根据用户ID查询已读的公告ID
     *
     * @param userId .
     * @return .
     */
    @Query("SELECT notice.id FROM BasNoticeRecord WHERE user.id = ?1")
    List<String> findNoticeIdByUserId(String userId);
}
