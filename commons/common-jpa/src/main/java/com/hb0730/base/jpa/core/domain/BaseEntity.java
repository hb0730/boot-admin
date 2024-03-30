package com.hb0730.base.jpa.core.domain;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/23
 */
@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {
    /**
     * 创建时间
     */
    private Date created;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 修改时间
     */
    private Date modified;
    /**
     * 修改人
     */
    private String modifiedBy;
}
