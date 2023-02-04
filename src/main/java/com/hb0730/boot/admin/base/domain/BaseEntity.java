package com.hb0730.boot.admin.base.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/4
 */
@Data
@EqualsAndHashCode
@ToString
public class BaseEntity implements Serializable {

    /**
     * ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    protected String id;

    /**
     * 创建人
     */
    protected String createdBy;

    /**
     * 创建时间
     */
    protected LocalDateTime created;

    /**
     * 更新人
     */
    protected String modifiedBy;

    /**
     * 更新时间
     */
    protected LocalDateTime modified;
}
