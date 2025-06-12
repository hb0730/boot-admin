package com.hb0730.sys.system.model.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/15
 */
@Data
@EqualsAndHashCode
@Schema(description = "保存用户角色")
public class SysUserRoleUpdateRequest implements Serializable {
    @Schema(description = "角色ID")
    private String roleId;
    @Schema(description = "结束时间")
    private Date endTime;
}
