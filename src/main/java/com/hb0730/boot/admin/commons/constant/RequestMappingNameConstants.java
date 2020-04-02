package com.hb0730.boot.admin.commons.constant;

/**
 * <p>
 * 请求路径
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public interface RequestMappingNameConstants {
    /**
     * <p>
     * 用户
     * </p>
     */
    String REQUEST_USER = "/api/v1/user";

    /**
     * 菜单
     */
    String REQUEST_MENU = "/api/v1/menu";

    /**
     * 系统权限
     */
    String REQUEST_PERMISSION = "/api/v1/permission";

    /**
     * 系统组织
     */
    String REQUEST_ORG = "/api/v1/org";

    /**
     * 系统角色
     */
    String REQUEST_ROLE = "/api/v1/role";

    /**
     * 系统岗位
     */
    String REQUEST_POST = "/api/v1/post";

    /**
     * 数据字典
     */
    String REQUEST_DICT = "/api/v1/dict";

    /**
     * 访问日志(登录日志)
     */
    String REQUEST_LOGININFO = "/api/v1/logininfo";

    /**
     * 操作日志
     */
    String REQUEST_OPERLOG = "/api/v1/monitor/operLog";

    /**
     * 在线用户
     */
    String REQUEST_USER_ONLINE = "/api/v1/monitor/online";
}
