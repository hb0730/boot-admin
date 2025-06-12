package com.hb0730.sys.system.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hb0730.desensitize.core.annotation.Desensitize;
import com.hb0730.desensitize.core.enums.DesensitizationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/14
 */
@Data
@EqualsAndHashCode
@Schema(description = "用户")
public class SysUserVO implements Serializable {
    @Schema(description = "用户ID")
    private String id;
    /**
     * 用户账号
     */
    @Schema(description = "用户账号")
    private String username;
    /**
     * 用户昵称
     */
    @Schema(description = "用户昵称")
    private String nickname;
    /**
     * 用户头像
     */
    @Schema(description = "用户头像")
    private String avatar;
    /**
     * 用户邮箱
     */
    @Schema(description = "用户邮箱")
    @Desensitize(type = DesensitizationType.EMAIL)
    private String email;

    /**
     * 用户手机号
     */
    @Schema(description = "用户手机号")
    @Desensitize(type = DesensitizationType.MOBILE)
    private String phone;

    /**
     * 用户性别 0-未知 1-男 2-女
     */
    @Schema(description = "用户性别 0-未知 1-男 2-女")
    private Integer gender;
    /**
     * 最后登录时间
     */
    @Schema(description = "最后登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastLoginTime;

    /**
     * 是否系统用户
     */
    @Schema(description = "是否系统用户")
    private Boolean isSystem;

    /**
     * 用户状态 0-正常 1-禁用
     */
    @Schema(description = "用户状态 0-正常 1-禁用")
    private Boolean status;
}
