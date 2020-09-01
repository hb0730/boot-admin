package com.hb0730.boot.admin.project.system.menu.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 前端菜单元
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@AllArgsConstructor
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
     * 是否缓存
     */
    private Boolean cache;
    /**
     * 是否认证
     */
    private Boolean auth;
}
