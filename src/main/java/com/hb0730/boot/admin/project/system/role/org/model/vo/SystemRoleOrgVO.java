package com.hb0730.boot.admin.project.system.role.org.model.vo;

import com.hb0730.boot.admin.commons.web.model.BusinessVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色组织(数据范围)
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SystemRoleOrgVO extends BusinessVO {

    private static final long serialVersionUID = 1L;

    /**
     * 备注
     */
    private String description;

    /**
     * id
     */
    private Long id;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 组织id
     */
    private Long orgId;
}
