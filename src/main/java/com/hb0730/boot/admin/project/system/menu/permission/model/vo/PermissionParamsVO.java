package com.hb0730.boot.admin.project.system.menu.permission.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
public class PermissionParamsVO implements Serializable {
    /**
     * 权限标识
     */
    private String mark;

    /**
     * 权限名称
     */
    private String name;
}
