package com.hb0730.base.utils;

import com.hb0730.base.R;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * servlet工具
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/24
 */

public class ServletUtil extends cn.hutool.extra.servlet.JakartaServletUtil {
    /**
     * 获取 ip 位置
     *
     * @param ip ip
     * @return ip 位置
     */
    public static String getLocal(String ip) {
        return "";
    }

    /**
     * 获取请求的User-Agent
     *
     * @param request 请求
     * @return User-Agent
     */
    public static String getUserAgent(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }

    /**
     * 写出json,并设置contentType为application/json;charset=UTF-8
     *
     * @param response 响应
     * @param message  信息
     */
    public static void writeJson(HttpServletResponse response, R<?> message) {
        String content = JsonUtil.DEFAULT.toJson(message);
        write(response, content, "application/json;charset=UTF-8");
    }

}
