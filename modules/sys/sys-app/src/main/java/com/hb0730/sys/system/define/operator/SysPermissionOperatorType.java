package com.hb0730.sys.system.define.operator;


import com.hb0730.operator.log.core.annotation.OperatorModule;
import com.hb0730.operator.log.core.enums.OperatorRiskLevelEnums;
import com.hb0730.operator.log.core.factory.InitializingOperatorTypes;
import com.hb0730.operator.log.core.model.OperatorType;

/**
 * 权限操作类型
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/15
 */
@OperatorModule("basic:permission")
public class SysPermissionOperatorType extends InitializingOperatorTypes {
    public static final String ADD = "sys:permission:create";
    public static final String EDIT = "sys:permission:edit";
    public static final String DELETE = "sys:permission:delete";
    public static final String GRANT = "sys:permission:grant";

    @Override
    public OperatorType[] types() {
        return new OperatorType[]{
                new OperatorType(OperatorRiskLevelEnums.L, ADD, "新增权限"),
                new OperatorType(OperatorRiskLevelEnums.M, EDIT, "修改权限"),
                new OperatorType(OperatorRiskLevelEnums.H, DELETE, "删除权限"),
                new OperatorType(OperatorRiskLevelEnums.H, GRANT, "授权")
        };
    }
}
