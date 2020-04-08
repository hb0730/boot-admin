package com.hb0730.boot.admin.commons.constant;

import java.io.File;

/**
 * <p>
 * 系统常量
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class SystemConstants {
    /**
     * User home directory.
     */
    public final static String USER_HOME = System.getProperties().getProperty("user.home");

    /**
     * 启用
     */
    public static final int USE = 1;

    /**
     * 禁用
     */
    public static final int NOT_USE = 0;

    /**
     * 是否全部
     */
    public  static final int IS_ALL=-1;

    /**
     *修改
     */
    public static final  int IS_UPDATE=1;
    /**
     * 非修改
     */
    public static final  int NO_UPDATE=0;
    /**
     * 默认父id为-1
     */
    public static final long PARENT_ID = -1L;

    /**
     * 默认密码
     */
    public static final String DEFAULT_PASSWORD="123456";

    /**
     * 路径分隔符
     */
    public static final String FILE_SEPARATOR = File.separator;
}
