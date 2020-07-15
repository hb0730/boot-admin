package com.hb0730.boot.admin.project.system.post.model.vo;

import com.hb0730.boot.admin.commons.domain.model.web.BaseParams;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <p>
 * 岗位
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class PostParams extends BaseParams {
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
