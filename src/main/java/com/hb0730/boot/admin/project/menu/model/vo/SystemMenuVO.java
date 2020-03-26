package com.hb0730.boot.admin.project.menu.model.vo;

import com.hb0730.boot.admin.commons.web.model.BusinessVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SystemMenuVO extends BusinessVO {
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
    @NotEmpty(message = "菜单名称不能为空")
    private String name;

    /**
     * 英文名称
     */
    private String enname;

    /**
     * 路径
     */
    @NotEmpty(message = "菜单路径不能为空")
    private String url;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 是否有下级
     */
    private Integer hasChild;

    /**
     * 是否为根节点
     */
    private Integer isRoot;

    /**
     * 菜单级别
     */
    private Integer level;
}
