package com.hb0730.boot.admin.project.system.user.post.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.hb0730.boot.admin.commons.domain.BusinessDomain;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户岗位
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_system_user_post")
public class SystemUserPostEntity extends BusinessDomain {

    private static final long serialVersionUID=1L;

    /**
     * 备注
     */
    @TableField("description")
    private String description;

    /**
     * id
     */
    @TableId("id")
    private Long id;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 岗位id
     */
    @TableField("post_id")
    private Long postId;


    public static final String DESCRIPTION = "description";

    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String POST_ID = "post_id";

}
