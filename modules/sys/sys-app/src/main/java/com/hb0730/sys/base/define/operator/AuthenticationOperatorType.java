package com.hb0730.sys.base.define.operator;


import com.hb0730.operator.log.core.annotation.OperatorModule;
import com.hb0730.operator.log.core.enums.OperatorRiskLevelEnums;
import com.hb0730.operator.log.core.factory.InitializingOperatorTypes;
import com.hb0730.operator.log.core.model.OperatorType;

/**
 * 认证操作类型
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/10
 */
@OperatorModule("basic:authentication")
public class AuthenticationOperatorType extends InitializingOperatorTypes {
    public static final String LOGIN = "authentication:login";

    public static final String LOGOUT = "authentication:logout";

    public static final String UPDATE_PASSWORD = "authentication:update-password";

    public static final String UPDATE_EMAIL_PHONE = "authentication:update-email-phone";

    public static final String CREATE_ACCESS_TOKEN = "authentication:create-access-token";

    public static final String CANCEL_ACCESS_TOKEN = "authentication:cancel-access-token";

    public static final String RESTORE_ACCESS_TOKEN = "authentication:restore-access-token";

    public static final String DELETE_ACCESS_TOKEN = "authentication:delete-access-token";

    public static final String UPDATE_USER_SETTINGS = "authentication:update-user-settings";

    public static final String SOCIAL_BIND = "authentication:social-bind";

    public static final String SOCIAL_UNBIND = "authentication:social-unbind";

    public static final String UPDATE_USER_SUBSCRIBE_MSG = "authentication:update-user-subscribe-msg";

    @Override
    public OperatorType[] types() {
        return new OperatorType[]{
                new OperatorType(OperatorRiskLevelEnums.L, LOGIN, "用户登录"),
                new OperatorType(OperatorRiskLevelEnums.L, LOGOUT, "用户登出"),
                new OperatorType(OperatorRiskLevelEnums.H, UPDATE_PASSWORD, "修改密码"),
                new OperatorType(OperatorRiskLevelEnums.H, UPDATE_EMAIL_PHONE, "修改邮箱或手机号"),
                new OperatorType(OperatorRiskLevelEnums.L, CREATE_ACCESS_TOKEN, "创建访问令牌"),
                new OperatorType(OperatorRiskLevelEnums.M, CANCEL_ACCESS_TOKEN, "注销访问令牌"),
                new OperatorType(OperatorRiskLevelEnums.M, RESTORE_ACCESS_TOKEN, "恢复访问令牌"),
                new OperatorType(OperatorRiskLevelEnums.H, DELETE_ACCESS_TOKEN, "删除访问令牌"),
                new OperatorType(OperatorRiskLevelEnums.L, UPDATE_USER_SETTINGS, "更新用户设置"),
                new OperatorType(OperatorRiskLevelEnums.L, SOCIAL_BIND, "社交账号绑定"),
                new OperatorType(OperatorRiskLevelEnums.M, SOCIAL_UNBIND, "社交账号解绑"),
                new OperatorType(OperatorRiskLevelEnums.H, UPDATE_USER_SUBSCRIBE_MSG, "更新用户订阅消息")


        };
    }
}
