package com.hb0730.boot.admin.project.system.user.account.model.dto;

import com.hb0730.boot.admin.domain.model.InputConverter;
import com.hb0730.boot.admin.domain.model.dto.BaseDTO;
import com.hb0730.boot.admin.project.system.user.account.model.entity.UserAccountEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 用户账号
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserAccountDTO extends BaseDTO implements InputConverter<UserAccountEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 备注
     */
    private String description;

    /**
     * id
     */
    private Long id;

    /**
     * 用户账号
     */
    @NotBlank(message = "用户账号不为空")
    private String username;

    /**
     * 用户密码
     */
    @NotBlank(message = "用户密码不为空")
    private String password;

    /**
     * 用户id
     */
    @NotNull(message = "用户id不为空")
    private Long userId;
}
