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
    private String name;

    private String path;

    private Boolean hidden;

    private String redirect;

    private String component;

    private Boolean alwaysShow;

    private MenuMetaVO meta;

    private List<VueMenuVO> children;
}
