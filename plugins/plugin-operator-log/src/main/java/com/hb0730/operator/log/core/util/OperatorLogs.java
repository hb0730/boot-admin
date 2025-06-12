package com.hb0730.operator.log.core.util;

import cn.hutool.core.map.MapUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hb0730.base.exception.BasicException;
import com.hb0730.base.meta.IUserInfo;
import com.hb0730.operator.log.core.define.Wrapper;

import java.util.Collection;
import java.util.Map;

/**
 * 操作日志 工具类
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/9
 */
public class OperatorLogs {
    private static final String UN_SAVE_FLAG = "__un__save__";


    /**
     * 拓展信息
     */
    private static final ThreadLocal<Map<String, Object>> EXTRA_HOLDER = ThreadLocal.withInitial(MapUtil::newHashMap);

    /**
     * 当前用户 优先于登录用户
     */
    private static final ThreadLocal<IUserInfo> USER_HOLDER = new ThreadLocal<>();
    private static ObjectMapper objectMapper;


    /**
     * 添加日志参数
     *
     * @param key   key
     * @param value value
     */
    public static void add(String key, Object value) {
        EXTRA_HOLDER.get().put(key, value);
    }

    /**
     * 添加日志参数 json
     *
     * @param key   key
     * @param value value
     */
    public static void addJson(String key, Object value) {
//        EXTRA_HOLDER.get().put(key, JSON.parseObject(JSON.toJSONString(value, serializeFilters)));
        try {
            EXTRA_HOLDER.get().put(
                    key,
                    objectMapper.readValue(objectMapper.writeValueAsBytes(value), Object.class)
            );
        } catch (Exception e) {
            throw new BasicException(e);
        }
    }


    /**
     * 添加日志参数
     *
     * @param map map
     */
    public static void add(Map<String, ?> map) {
        EXTRA_HOLDER.get().putAll(map);
    }

    /**
     * 添加日志参数
     *
     * @param obj obj
     */
    @SuppressWarnings("unchecked")
    public static void add(Object obj) {
        if (obj == null) {
            return;
        }
        if (obj instanceof Map) {
            EXTRA_HOLDER.get().putAll((Map<String, ?>) obj);
        } else if (obj instanceof Collection<?>) {
            Wrapper<Object> objectWrapper = new Wrapper<>(obj);
            try {
                EXTRA_HOLDER.get().putAll(objectMapper.readValue(objectMapper.writeValueAsBytes(objectWrapper), Map.class));
            } catch (Exception e) {
                throw new BasicException(e);
            }
        } else {
            try {
                EXTRA_HOLDER.get().putAll(objectMapper.readValue(objectMapper.writeValueAsBytes(obj), Map.class));
            } catch (Exception e) {
                throw new BasicException(e);
            }
        }
    }

    /**
     * 设置不保存
     */
    public static void unSave() {
        setSave(false);
    }

    /**
     * 设置是否保存
     *
     * @param save save
     */
    public static void setSave(boolean save) {
        if (save) {
            EXTRA_HOLDER.get().remove(UN_SAVE_FLAG);
        } else {
            EXTRA_HOLDER.get().put(UN_SAVE_FLAG, UN_SAVE_FLAG);
        }
    }

    /**
     * 设置是否保存
     *
     * @return save
     */
    public static boolean isSave() {
        return !UN_SAVE_FLAG.equals(EXTRA_HOLDER.get().get(UN_SAVE_FLAG));
    }

    /**
     * 获取参数
     *
     * @return map
     */
    public static Map<String, Object> get() {
        return EXTRA_HOLDER.get();
    }

    /**
     * 设置用户信息
     *
     * @param user user
     */
    public static void setUser(IUserInfo user) {
        USER_HOLDER.set(user);
    }

    /**
     * 获取用户
     *
     * @return user
     */
    public static IUserInfo getUser() {
        return USER_HOLDER.get();
    }

    /**
     * 清空
     */
    public static void clear() {
        EXTRA_HOLDER.remove();
        USER_HOLDER.remove();
    }

    public static void setObjectMapper(ObjectMapper objectMapper) {
        OperatorLogs.objectMapper = objectMapper;
    }

}
