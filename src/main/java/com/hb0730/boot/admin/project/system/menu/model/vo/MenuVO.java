package com.hb0730.boot.admin.project.system.menu.model.vo;

import com.hb0730.boot.admin.domain.model.vo.BusinessVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 菜单
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuVO extends BusinessVO {

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
     * 父菜单id
     */
    @NotNull(message = "父id不为空")
    private Long parentId;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件地址
     */
    private String component;

    /**
     * 图标
     */
    private String icon;

    /**
     * 展示顺序
     */
    private Integer sort;
}