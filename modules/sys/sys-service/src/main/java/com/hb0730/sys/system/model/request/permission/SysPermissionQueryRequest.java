package com.hb0730.sys.system.model.request.permission;

import com.hb0730.mybatis.query.annotation.Like;
import com.hb0730.mybatis.query.doamin.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 权限查询
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/5
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "菜单&权限查询")
public class SysPermissionQueryRequest extends PageRequest {
    @Like
    @Schema(description = "名称")
    private String title;

    @Schema(description = "父级ID")
    private String parentId;
}
