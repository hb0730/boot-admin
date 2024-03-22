package com.hb0730.biz.dto.sys.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hb0730.base.enums.ValueEnum;
import com.hb0730.base.enums.sys.GenderEnums;
import com.hb0730.data.dto.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/15
 */
@Getter
@Setter
@Accessors(chain = true)
public class UserDto extends BaseDto {
    @Schema(description = "用户ID")
    private Integer id;
    /**
     * 角色
     */
    @Schema(description = "角色")
    private List<RoleSmallDto> roles;
    /**
     * 部门
     */
    @Schema(description = "部门")
    private DeptSmallDto dept;
    /**
     * 用户名
     */
    @Schema(description = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;
    /**
     * 密码
     */
    @Schema(description = "密码", hidden = true)
    @JsonIgnore
    private String password;
    /**
     * 昵称
     */
    @Schema(description = "昵称")
    @NotBlank(message = "昵称不能为空")
    private String nickname;
    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;
    /**
     * 手机号
     */
    @Schema(description = "手机号")
    private String phone;
    /**
     * 头像
     */
    @Schema(description = "头像")
    private String avatar;
    /**
     * 性别
     */
    @Schema(description = "性别,0 保密,1 男,2 女")
    private Integer gender;

    @Schema(description = "性别名称")
    public String getGenderName() {
        GenderEnums valueEnum = ValueEnum.valueToEnum(GenderEnums.class, gender, GenderEnums.UNKNOWN);
        return valueEnum.getName();

    }

    /**
     * 是否管理员
     */
    @Schema(description = "是否管理员")
    private Boolean admin = false;
    /**
     * 最后登录时间
     */
    @Schema(description = "最后登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastLoginTime;

    /**
     * 最近修改密码时间
     */
    @Schema(description = "最近修改密码时间")
    private Date pwdResetTime;
    /**
     * 状态
     * <p>
     * 1 正常
     * 0 禁用
     */
    private Boolean enabled;
}
