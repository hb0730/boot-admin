package com.hb0730.boot.admin.project.system.org.model.vo;

import com.hb0730.boot.admin.domain.model.web.BusinessVO;
import com.hb0730.boot.admin.model.converter.InputConverter;
import com.hb0730.boot.admin.project.system.org.model.entity.SystemOrgEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 系统组织
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SystemOrgVO extends BusinessVO implements InputConverter<SystemOrgEntity> {

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
     * 父id
     */
    private Long parentId;

    /**
     * 名称
     */
    @NotBlank(message = "名称不为空")
    private String name;

    /**
     * 英文名称
     */
    private String enname;

    /**
     * 编码
     */
    @NotBlank(message = "编码不为空")
    private String number;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 祖级列表
     */
    private String ancestors;

    /**
     * 负责人
     */
    @NotBlank(message = "编码不为空")
    private String leader;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 联系邮箱
     */
    private String email;
}
