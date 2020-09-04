package com.hb0730.boot.admin.project.system.user.model.query;

import com.hb0730.boot.admin.domain.model.query.BaseParams;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 用户请求条件
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class UserInfoParams extends BaseParams {
    /**
     * 部门id
     */
    private Long deptId;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 账号
     */
    private String username;
    /**
     * 状态
     */
    private Integer isEnabled;
}
