package com.hb0730.boot.admin.project.system.user.model.vo;

import com.hb0730.boot.admin.commons.web.model.BaseParams;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <p>
 * 用户请求
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class UserParams extends BaseParams {
    /**
     * 组织id
     */
    private Long deptId;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 是否启用
     */
    private Integer isEnabled;
}
