package com.hb0730.boot.admin.domain.model;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import org.apache.commons.lang3.reflect.TypeUtils;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 类型转换
 *
 * @author bing_huang
 * @since 3.0.0
 */
public interface InputConverter<DOMAIN> {
    /**
     * 转换 domain
     *
     * @return 具有相同值的新域（非空）
     */
    @SuppressWarnings("unchecked")
    default DOMAIN convertTo() {
        Map<TypeVariable<?>, Type> typeArguments = TypeUtils.getTypeArguments(getClass(), InputConverter.class);
        if (CollectionUtil.isEmpty(typeArguments)) {
            return null;
        }
        Collection<Type> values = typeArguments.values();
        List<Object> list = CollectionUtil.list(false);
        list.addAll(values);
        list.get(0);
        Class<DOMAIN> clazz = (Class<DOMAIN>) list.get(0);
        return BeanUtil.toBean(this, (Class<DOMAIN>) clazz);
    }

    /**
     * 值更新
     *
     * @param domain 更新目标
     */
    default void update(DOMAIN domain) {
        BeanUtil.copyProperties(this, domain);
    }
}
