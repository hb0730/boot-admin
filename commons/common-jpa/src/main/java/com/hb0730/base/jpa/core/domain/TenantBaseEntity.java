package com.hb0730.base.jpa.core.domain;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 租户基础实体
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/23
 */
@Getter
@Setter
@Accessors(chain = true)
@MappedSuperclass
public class TenantBaseEntity extends BaseEntity {
    /**
     * 租户ID
     */
    @NotBlank(message = "租户代码不能为空")
    private String sysCode;
}
