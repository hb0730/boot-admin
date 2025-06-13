package com.hb0730.base.utils;

import java.util.Map;

/**
 * map工具类
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/9
 */
public class MapUtil extends cn.hutool.core.map.MapUtil {

    /**
     * 合并两个map
     *
     * @param map1 map1
     * @param map2 map2
     * @return 合并后的map
     */
    public static Map<String, Object> mergeMap(Map<String, Object> map1,
                                               Map<String, Object> map2) {
        if (isEmpty(map1)) {
            return map2;
        }
        if (isEmpty(map2)) {
            return map1;
        }
        map1.putAll(map2);
        return map1;
    }
}
