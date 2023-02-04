package com.hb0730.boot.admin.modules.sys.system.model.entity;

import com.hb0730.boot.admin.base.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 角色信息
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/4
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class SysRole extends BaseEntity {
    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 描述
     */
    private String description;

    /**
     * 删除状态（1，启用，2禁用）
     */
    private Integer enabled;
}
