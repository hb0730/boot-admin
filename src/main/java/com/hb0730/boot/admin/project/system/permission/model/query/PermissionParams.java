package com.hb0730.boot.admin.project.system.permission.model.query;

import com.hb0730.boot.admin.domain.model.query.BaseParams;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 权限查询参数
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class PermissionParams extends BaseParams {
    /**
     * 菜单id
     */
    private Long menuId;
}
