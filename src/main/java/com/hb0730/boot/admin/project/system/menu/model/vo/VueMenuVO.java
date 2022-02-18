package com.hb0730.boot.admin.project.system.menu.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 构建前端路由菜单
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
public class VueMenuVO implements Serializable {
    /**
     * router 名称
     */
    private String name;
    /**
     * router 地址
     */
    private String path;
    /**
     * 组件地址
     */
    private String component;
    /**
     * 重定向
     */
    private String redirect;
    /**
     * 路由元数据
     */
    private MenuMetaVO meta;
    /**
     * 子集
     */
    private List<VueMenuVO> children;
}
