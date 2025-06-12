package com.hb0730.sys.system.model.vo;

import com.hb0730.base.utils.TreeUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/5
 */
@Data
@Schema(description = "菜单权限树")
public class SysPermissionTreeVO implements Serializable, TreeUtil.Node<SysPermissionTreeVO, String> {
    /**
     * ID
     */
    @Schema(description = "ID")
    private String id;
    /**
     * 父级ID
     */
    @Schema(description = "父级ID")
    private String parentId;
    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称")
    private String title;
    @Schema(description = "子菜单")
    private List<SysPermissionTreeVO> children;


    @Override
    public void setChildren(List<SysPermissionTreeVO> children) {
        this.children = children;
    }
}
