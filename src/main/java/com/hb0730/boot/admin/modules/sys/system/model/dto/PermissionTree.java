package com.hb0730.boot.admin.modules.sys.system.model.dto;

import com.hb0730.boot.admin.data.domain.TreeData;
import com.hb0730.boot.admin.data.enums.MenuTypeEnums;
import com.hb0730.boot.admin.data.enums.ValueEnum;
import com.hb0730.boot.admin.modules.sys.system.model.entity.SysPermission;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限树
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/6
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class PermissionTree extends SysPermission implements TreeData<PermissionTree> {
    @Schema(description = "子菜单")
    private List<PermissionTree> children = new ArrayList<>();

    public PermissionTree setChildren(List<PermissionTree> children) {
        this.children = children;
        return this;
    }

    public List<PermissionTree> getChildren() {
        return children;
    }


    @Schema(description = "是否显示")
    public boolean isShowLink() {
        return getShowLink() == 1;
    }

    @Schema(description = "是否显示父类")
    public boolean isShowParent() {
        return getShowParent() == 1;
    }

    @Schema(description = "是否缓存")
    public boolean isKeepAlive() {
        return getKeepAlive() == 1;
    }

    @Schema(description = "是否iframe")
    public boolean isFrame() {
        return getIsFrame() == 1;
    }

    @Schema(description = "菜单类型名称")
    public String getMenuTypeName() {
        if (ValueEnum.hash(MenuTypeEnums.class, getMenuType())) {
            return ValueEnum.valueToEnum(MenuTypeEnums.class, getMenuType()).getName();
        }
        return null;
    }

    @Schema(description = "是否启用")
    public boolean isEnabled() {
        return getEnabled() == 1;
    }
}
