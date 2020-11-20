package com.hb0730.boot.admin.domain.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode
@ToString
class Domain implements Serializable {
    /**
     * 创建者
     */
    @TableField(value = "create_user_id", fill = FieldFill.INSERT)
    private Long createUserId;
    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 修改者
     */
    @TableField(value = "update_user_id", fill = FieldFill.UPDATE)
    private Long updateUserId;
    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 是否启用
     */
    @TableField(value = "is_enabled", fill = FieldFill.INSERT)
    private Integer isEnabled = 1;
    /**
     * 是否删除
     */
    @TableField(value = "del_flag")
    @TableLogic
    private Integer delFlag = 0;
    /**
     * 版本
     */
    @Version
    @TableField(value = "version", fill = FieldFill.INSERT)
    private Integer version;

    public static final String VERSION = "version";

    public static final String DEL_FLAG = "del_flag";

    public static final String CREATE_USER_ID = "create_user_id";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_USER_ID = "update_user_id";

    public static final String UPDATE_TIME = "update_time";

    public static final String IS_ENABLED = "is_enabled";
}
