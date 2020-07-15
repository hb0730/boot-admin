package com.hb0730.boot.admin.project.system.menu.permission.model.vo;

import com.hb0730.boot.admin.commons.domain.model.web.BaseParams;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 权限过滤参数
 *
 * @author bing_huang
 * @date 2020/06/26 12:22
 * @since V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class PermissionParams extends BaseParams {
    /**
     * 权限标识
     */
    private String mark;

    /**
     * 权限名称
     */
    private String name;

    private Long id;
}
