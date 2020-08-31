package com.hb0730.boot.admin.domain.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode
@ToString
class DomainDTO implements Serializable {
    private static final long serialVersionUID = -3936221129643979906L;
    /**
     * 创建者
     */
    private Long createUserId;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改者
     */
    private Long updateUserId;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 是否启用
     */
    private Integer isEnabled;
}
