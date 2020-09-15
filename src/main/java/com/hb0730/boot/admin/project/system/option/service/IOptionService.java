package com.hb0730.boot.admin.project.system.option.service;

import com.hb0730.boot.admin.commons.enums.PropertyEnum;
import com.hb0730.boot.admin.domain.service.ISuperBaseService;
import com.hb0730.boot.admin.project.system.option.model.dto.OptionDTO;
import com.hb0730.boot.admin.project.system.option.model.entity.OptionEntity;
import com.hb0730.boot.admin.project.system.option.model.query.OptionParams;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * options选项  服务类
 *
 * @author bing_huang
 * @since 3.0.0
 */
public interface IOptionService extends ISuperBaseService<Long, OptionParams, OptionDTO, OptionEntity> {
    /**
     * 保存
     *
     * @param key   key
     * @param value value
     */
    void save(@NonNull String key, @Nullable String value);

    /**
     * 保存
     *
     * @param options options
     */
    void save(@Nullable Map<String, Object> options);

    /**
     * 获取option选项
     *
     * @return Map
     */
    @NonNull
    @Transactional
    Map<String, Object> listOptions();

    /**
     * 根据keys获取options
     *
     * @param keys key list
     * @return a map of option
     */
    @NonNull
    Map<String, Object> listOptions(@Nullable List<String> keys);

    /**
     * 获取选项获取属性值
     *
     * @param property     属性 不为空
     * @param propertyType 属性类型
     * @param defaultValue 默认值
     * @param <T>          属性类型
     * @return 属性值
     */
    <T> T getByPropertyOrDefault(@NonNull PropertyEnum property, @NonNull Class<T> propertyType, T defaultValue);

    /**
     * 获取选项属性值
     *
     * @param property     选项属性
     * @param propertyType 属性类型 不为空
     * @param <T>          属性类型
     * @return 属性值
     */
    <T> T getByPropertyOrDefault(@NonNull PropertyEnum property, @NonNull Class<T> propertyType);


    /**
     * 根据key获取值
     *
     * @param key          key不为空
     * @param valueType    value 类型
     * @param defaultValue 默认值
     * @param <T>          value类型
     * @return value
     */
    <T> T getByKeyOrDefault(String key, Class<T> valueType, T defaultValue);


    /**
     * 根据选项获取属性值
     *
     * @param property     选项属性不为空
     * @param propertyType 选项属性类型
     * @param <T>          属性类型
     * @return 属性值
     */
    <T> Optional<T> getByProperty(@NonNull PropertyEnum property, @NonNull Class<T> propertyType);

    /**
     * 获取选项属性值
     *
     * @param property 选项属性 不为空
     * @return 可选值
     */
    Optional<Object> getByProperty(@NonNull PropertyEnum property);

    /**
     * 根据key获取值
     *
     * @param key       key 不为空
     * @param valueType value 类型 不为空
     * @param <T>       value 类型
     * @return value
     */
    <T> Optional<T> getByKey(@NonNull String key, @NonNull Class<T> valueType);

    /**
     * 根据key获取
     *
     * @param key 可以 不为空
     * @return 可选值
     */
    @NonNull
    Optional<Object> getByKey(@NonNull String key);
}
