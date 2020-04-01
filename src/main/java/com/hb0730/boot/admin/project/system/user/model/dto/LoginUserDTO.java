package com.hb0730.boot.admin.project.system.user.model.dto;

import com.hb0730.boot.admin.commons.domain.BusinessDomain;
import com.hb0730.boot.admin.project.system.permission.model.dto.SystemPermissionDTO;
import com.hb0730.boot.admin.project.system.post.model.dto.SystemPostDTO;
import com.hb0730.boot.admin.project.system.role.model.dto.SystemRoleDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LoginUserDTO  extends BusinessDomain {
    private Long id;
    /**
     * 用户账号
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phonenumber;

    /**
     * 用户性别
     */
    private Integer sex;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 用户权限
     */
    private List<SystemPermissionDTO> permissions;
    /**
     * 角色信息
     */
    private List<SystemRoleDTO> roles;

    /**
     * 岗位信息
     */
    private List<SystemPostDTO> posts;
}
