package com.hb0730.boot.admin.project.user.model.vo;

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
public class UserParamsVO implements Serializable {
    /**
     * 组织id
     */
    private Long deptId;
    /**
     * 是否查询全部
     */
    private Integer isAll;
}
