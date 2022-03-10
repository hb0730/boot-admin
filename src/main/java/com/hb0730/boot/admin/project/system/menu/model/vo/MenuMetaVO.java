package com.hb0730.boot.admin.project.system.menu.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 前端菜单元
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuMetaVO implements Serializable {
    /**
     * 页签标题
     */
    private String title;
    /**
     * 图标
     */
    private String icon;
    /**
     * 国际化
     */
    private Boolean i18n = false;
    /**
     * iframe
     */
    private String frameSrc;
    /**
     * 是否在菜单中显示
     */
    private Boolean showLink;
    /**
     * 是否显示父级菜单
     */
    private Boolean showParent = true;
    /**
     * 菜单升序排序，
     */
    private Integer rank;
    /**
     * 路由权限设置
     */
    private List<String> authority;
    /**
     * 路由组件缓存
     */
    private Boolean keepAlive;
}
