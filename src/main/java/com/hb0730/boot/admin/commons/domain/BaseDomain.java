package com.hb0730.boot.admin.commons.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
public class BaseDomain implements Serializable {
    /**
     * 创建人
     */
    @TableField(value = "create_user_id", fill = FieldFill.INSERT, insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private Long createUserId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT, insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private Date createTime;

    /**
     * 更新者
     */
    @TableField(value = "update_user_id", fill = FieldFill.UPDATE, insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private Long updateUserId;

    /**
     * 创建时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE, insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)

    private Date updateTime;

    /**
     * <p>
     * 是否启用
     * </p>
     */
    @TableField(value = "is_enabled", fill = FieldFill.INSERT, insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    private Integer isEnabled;

    /**
     * 是否删除
     */
    @TableField(value = "del_flag", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    @TableLogic
    private Integer delFlag = 0;
    /**
     * 版本
     */
    @TableField(value = "version", fill = FieldFill.INSERT, insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY, whereStrategy = FieldStrategy.NOT_EMPTY)
    @Version
    private Integer version;


    public static final String VERSION = "version";

    public static final String DEL_FLAG = "del_flag";

    public static final String CREATE_USER_ID = "create_user_id";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_USER_ID = "update_user_id";

    public static final String UPDATE_TIME = "update_time";

    public static final String IS_ENABLED = "is_enabled";
}
