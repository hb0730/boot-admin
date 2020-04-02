package com.hb0730.boot.admin.project.system.post.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
public class PostParams implements Serializable {
    /**
     * 是否获取全部
     */
    public Integer isAll;

    /**
     * 岗位编码
     */
    public String number;

    /**
     * 岗位名称
     */
    public String name;

    /**
     * 岗位状态
     */
    public Integer isEnabled;
}
