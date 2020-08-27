package com.hb0730.boot.admin.project.system.user.info.model.vo;

import com.hb0730.boot.admin.domain.model.InputConverter;
import com.hb0730.boot.admin.domain.model.vo.BaseVO;
import com.hb0730.boot.admin.project.system.user.info.model.entity.UserInfoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 用户信息
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class UserInfoVO extends BaseVO implements InputConverter<UserInfoEntity> {

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
     * 昵称
     */
    private String nikeName;

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
}
