package com.hb0730.sys.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hb0730.mybatis.core.domain.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/29
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@TableName("sys_notice")
public class SysNotice extends BaseEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 公告日期（起）
     */
    private Date noticeTimeStart;

    /**
     * 公告日期（止）
     */
    private Date noticeTimeEnd;

    /**
     * 是否启用  1启用  2禁用
     */
    @TableField(value = "`is_enabled`", property = "enabled")
    private Boolean enabled = true;
    /**
     * 网点ID 多个以逗号隔开
     */
    private String orgId;
}
