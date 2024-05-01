package com.hb0730.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hb0730.basic.domain.entity.BasNoticeRecord;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/29
 */
@Repository
public interface BasNoticeRecordMapper extends BaseMapper<BasNoticeRecord> {

    /**
     * 根据用户id查询公告id
     *
     * @param userId 用户id
     * @return 公告id
     */
    @Select("SELECT notice_id FROM bas_notice_record WHERE user_id = #{userId}")
    List<String> findNoticeIdByUserId(String userId);
}
