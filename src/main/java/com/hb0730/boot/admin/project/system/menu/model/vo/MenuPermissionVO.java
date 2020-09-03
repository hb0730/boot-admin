package com.hb0730.boot.admin.project.system.menu.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单权限
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode
@ToString
public class MenuPermissionVO implements Serializable {
    /**
     * id
     */
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 是否为权限
     */
    private Boolean isPermission;
    /**
     * 子集
     */
    private List<MenuPermissionVO> children;
}
