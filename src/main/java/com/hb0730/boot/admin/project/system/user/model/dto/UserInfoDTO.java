package com.hb0730.boot.admin.project.system.user.model.dto;

import com.hb0730.boot.admin.domain.model.InputConverter;
import com.hb0730.boot.admin.domain.model.dto.BaseDTO;
import com.hb0730.boot.admin.project.system.user.model.entity.UserInfoEntity;
import com.hb0730.commons.lang.collection.CollectionUtils;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 用户信息
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO extends BaseDTO implements InputConverter<UserInfoEntity> {

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
     * 昵称
     */
    @NotBlank(message = "用户名不为空")
    private String nickName;

    /**
     * 用户手机号
     */
    private String phoneNumber;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 是否为管理员
     */
    private Integer isAdmin = 0;
    /**
     * 所属组织
     */
    private Long deptId;
    /**
     * 角色id
     */
    private Collection<Long> roleIds;

    public void setRoleIds(Collection<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return;
        }
        this.roleIds = new ArrayList<>(roleIds);
    }

    /**
     * 岗位id
     */
    private Collection<Long> postIds;

    public void setPostIds(Collection<Long> postIds) {
        if (CollectionUtils.isEmpty(postIds)) {
            return;
        }
        this.postIds = new ArrayList<>(postIds);
    }

    /**
     * 权限id
     */
    private Collection<Long> permissionIds;

    public void setPermissionIds(Collection<Long> permissionIds) {
        if (CollectionUtils.isEmpty(permissionIds)) {
            return;
        }
        this.permissionIds = new ArrayList<>(permissionIds);
    }

}
