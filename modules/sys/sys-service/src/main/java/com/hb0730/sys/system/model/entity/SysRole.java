package com.hb0730.sys.system.model.entity;

import com.hb0730.core.entity.BizEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 角色
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/3
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRole extends BizEntity {
    /**
     * 名称
     */
    private String roleName;
    /**
     * 编码
     */
    private String roleCode;
    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private Boolean status = true;
}
