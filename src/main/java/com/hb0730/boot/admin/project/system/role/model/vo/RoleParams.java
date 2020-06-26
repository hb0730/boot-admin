package com.hb0730.boot.admin.project.system.role.model.vo;

import com.hb0730.boot.admin.commons.web.model.BaseParams;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <p>
 * 角色请求参数
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class RoleParams extends BaseParams {
    /**
     * 查询全部
     */
    private Integer isAll;
    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色因为名称
     */
    private String enname;

    /**
     * 是否启用
     */
    private Integer isEnabled;
}
