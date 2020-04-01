package com.hb0730.boot.admin.project.system.role.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *     角色请求参数
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
public class RoleParams implements Serializable {
    /**
     * 查询全部
     */
    private Integer isAll;
}
