package com.hb0730.basic.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/29
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString
@EqualsAndHashCode
@TableName("bas_notice_record")
public class BasNoticeRecord implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 公告id
     */
    private String noticeId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 是否已读
     */
    @TableField(value = "`is_read`", property = "read")
    private Boolean read;
    /**
     * 阅读时间
     */
    private Date readTime;
}
