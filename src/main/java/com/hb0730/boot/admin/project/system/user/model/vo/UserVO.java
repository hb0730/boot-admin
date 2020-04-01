package com.hb0730.boot.admin.project.system.user.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 * 包含用户角色和用户岗位
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserVO extends SystemUserVO {
    /**
     * 用户岗位id
     */
    private List<Long> postId;

    /**
     * 用户角色id
     */
    private List<Long> roleId;
}
