package com.hb0730.base.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 配置工具类
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/9
 */
public class ConfigUtil {

    /**
     * 解析配置
     *
     * @param value 配置
     * @return 配置
     */
    public static List<String> parseStringList(List<String> value) {
        return parseStringList(value, Function.identity());
    }


    /**
     * 解析配置
     *
     * @param values   配置
     * @param function 函数
     * @return 配置
     */
    public static List<String> parseStringList(List<String> values, Function<String, String> function) {
        return Optional.ofNullable(values)
                .stream()
                .flatMap(Collection::stream)
                .map(s -> s.split(","))
                .flatMap(Arrays::stream)
                .map(String::trim)
                .map(function)
                .collect(Collectors.toList());
    }
}
