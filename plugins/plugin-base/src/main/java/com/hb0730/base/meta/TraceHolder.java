package com.hb0730.base.meta;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * 日志链路追踪工具类
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/3
 */
public class TraceHolder {

    private final static ThreadLocal<String> TRACE_ID = new TransmittableThreadLocal<>();

    /**
     * 设置链路追踪id
     *
     * @param traceId 链路追踪id
     */
    public static void setTraceId(String traceId) {
        TRACE_ID.set(traceId);
    }

    /**
     * 获取链路追踪id
     *
     * @return 链路追踪id
     */
    public static String getTraceId() {
        return TRACE_ID.get();
    }

    /**
     * 清除链路追踪id
     */
    public static void clear() {
        TRACE_ID.remove();
    }
}
