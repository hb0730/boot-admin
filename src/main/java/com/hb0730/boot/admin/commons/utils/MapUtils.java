package com.hb0730.boot.admin.commons.utils;

import lombok.experimental.UtilityClass;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * map util
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2022/1/10
 * @since 3.1.0
 */
@UtilityClass
public class MapUtils {
    /**
     * list to map
     *
     * @param list            data list
     * @param mappingFunction id
     * @param <ID>            id 类型
     * @param <D>             数据类型
     * @return map
     */
    @NonNull
    public <ID, D> Map<ID, D> convertToMap(Collection<D> list, @NonNull Function<D, ID> mappingFunction) {
        Assert.notNull(mappingFunction, "mapping function must not be null");
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyMap();
        }
        Map<ID, D> maps = new HashMap<>(16);
        list.forEach(data -> maps.putIfAbsent(mappingFunction.apply(data), data));
        return maps;
    }

    /**
     * 转换为映射（列表数据中的键）
     *
     * @param list          data list
     * @param keyFunction   key mapping function
     * @param valueFunction value mapping function
     * @param <ID>          id type
     * @param <D>           data type
     * @param <V>           value type
     * @return a map which key from list data and value is data
     */
    @NonNull
    public <ID, D, V> Map<ID, V> convertToMap(@Nullable Collection<D> list, @NonNull Function<D, ID> keyFunction,
                                              @NonNull Function<D, V> valueFunction) {
        Assert.notNull(keyFunction, "Key function must not be null");
        Assert.notNull(valueFunction, "Value function must not be null");

        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyMap();
        }

        Map<ID, V> resultMap = new HashMap<>(16);

        list.forEach(data -> resultMap.putIfAbsent(keyFunction.apply(data), valueFunction.apply(data)));

        return resultMap;
    }
}
