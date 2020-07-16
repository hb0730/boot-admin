package com.hb0730.boot.admin.project.system.role.model.vo;

import com.hb0730.boot.admin.commons.domain.model.web.BusinessVO;
import com.hb0730.boot.admin.commons.utils.convert.InputConverter;
import com.hb0730.boot.admin.project.system.role.model.entity.SystemRoleEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 系统角色
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SystemRoleVO extends BusinessVO implements InputConverter<SystemRoleEntity> {

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
     * 名称
     */
    @NotBlank(message = "名称不为空")
    private String name;

    /**
     * 英文名称
     */
    @NotBlank(message = "英文名称不为空")
    private String enname;

    /**
     * 排序
     */
    private Integer sort;
}
