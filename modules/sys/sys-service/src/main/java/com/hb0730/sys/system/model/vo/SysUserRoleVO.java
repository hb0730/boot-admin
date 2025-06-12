package com.hb0730.sys.system.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/15
 */
@Data
public class SysUserRoleVO implements Serializable {
    /**
     * id
     */
    @Schema(description = "id")
    private String id;
    /**
     * 用户id
     */
    @Schema(description = "用户id")
    private String userId;
    /**
     * 角色id
     */
    @Schema(description = "角色id")
    private String roleId;
    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    private String roleName;
    /**
     * 有效期时间
     */
    @Schema(description = "有效期时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endTime;
}
