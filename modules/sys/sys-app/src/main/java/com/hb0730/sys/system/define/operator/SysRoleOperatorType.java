package com.hb0730.sys.system.define.operator;


import com.hb0730.operator.log.core.annotation.OperatorModule;
import com.hb0730.operator.log.core.enums.OperatorRiskLevelEnums;
import com.hb0730.operator.log.core.factory.InitializingOperatorTypes;
import com.hb0730.operator.log.core.model.OperatorType;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/15
 */
@OperatorModule("basic:role")
public class SysRoleOperatorType extends InitializingOperatorTypes {
    public static final String ADD = "sys:role:create";
    public static final String EDIT = "sys:role:edit";
    public static final String DELETE = "sys:role:delete";
    public static final String GRANT = "sys:role:grant";
    public static final String OPEN_API = "sys:role:open_api:grant";

    @Override
    public OperatorType[] types() {
        return new OperatorType[]{
                new OperatorType(OperatorRiskLevelEnums.L, ADD, "新增角色"),
                new OperatorType(OperatorRiskLevelEnums.M, EDIT, "修改角色"),
                new OperatorType(OperatorRiskLevelEnums.H, DELETE, "删除角色"),
                new OperatorType(OperatorRiskLevelEnums.M, GRANT, "授权"),
                new OperatorType(OperatorRiskLevelEnums.M, OPEN_API, "分配开放接口")
        };
    }
}
