package com.hb0730.boot.admin.domain.model;

import com.hb0730.commons.lang.collection.CollectionUtils;
import com.hb0730.commons.spring.BeanUtils;
import org.apache.commons.lang3.reflect.TypeUtils;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Collection;
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
        if (CollectionUtils.isEmpty(typeArguments)) {
            return null;
        }
        Collection<Type> values = typeArguments.values();
        Class<DOMAIN> clazz = (Class<DOMAIN>) CollectionUtils.toArrayList(values).get(0);
        return BeanUtils.transformFrom(this, clazz);
    }

    /**
     * 值更新
     *
     * @param domain 更新目标
     */
    default void update(DOMAIN domain) {
        BeanUtils.updateProperties(this, domain);
    }
}
