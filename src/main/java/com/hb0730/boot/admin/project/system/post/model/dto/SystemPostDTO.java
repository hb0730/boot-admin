package com.hb0730.boot.admin.project.system.post.model.dto;

import com.hb0730.boot.admin.domain.model.domain.BusinessDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SystemPostDTO extends BusinessDomain {

    /**
     * 备注
     */
    private String description;

    /**
     * id
     */
    private Long id;

    /**
     * 岗位名称
     */
    private String name;

    /**
     * 岗位英文名称
     */
    private String enname;

    /**
     * 岗位编码
     */
    private String number;

    /**
     * 排序
     */
    private Integer sort;
}
