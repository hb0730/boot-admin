package com.hb0730.data.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/4
 */

@Getter
@Setter
@MappedSuperclass
public class DomainEntity implements Serializable {

    /**
     * 创建人
     */
    protected String createdBy;

    /**
     * 创建时间
     */
    protected java.util.Date created;

    /**
     * 更新人
     */
    protected String modifiedBy;

    /**
     * 更新时间
     */
    protected java.util.Date modified;
}
