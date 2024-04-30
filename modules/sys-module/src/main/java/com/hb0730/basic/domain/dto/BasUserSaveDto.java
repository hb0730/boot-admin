package com.hb0730.basic.domain.dto;

import com.hb0730.mybatis.core.domain.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/30
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class BasUserSaveDto extends BaseDto {
    @Schema(description = "用户id")
    private String id;
    @Schema(description = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;
    @Schema(description = "密码,只有新增时需要传入")
    private String password;
    @Schema(description = "昵称")
    private String nickname;
    @Schema(description = "手机号")
    private String phone;
    @Schema(description = "邮箱")
    private String email;
    @Schema(description = "头像")
    private String avatar;
    @Schema(description = "性别")
    private Integer gender;
    @Schema(description = "状态")
    private Boolean enabled;
    @Schema(description = "机构id")
    private String orgId;
    @Schema(description = "角色id")
    private List<String> roleIds;
    @Schema(description = "系统编码", hidden = true)
    private String sysCode;
}
