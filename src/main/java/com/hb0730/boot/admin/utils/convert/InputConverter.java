package com.hb0730.boot.admin.utils.convert;

import com.hb0730.boot.admin.utils.ReflectionUtils;
import com.hb0730.boot.admin.utils.bean.BeanUtils;
import org.springframework.lang.Nullable;

import java.lang.reflect.ParameterizedType;
import java.util.Objects;

/**
 * @author bing_huang
 * @date 2020/07/14 8:37
 * @since V1.0
 */
public interface InputConverter<DOMAIN> {
    /**
     * 转换 domain
     *
     * @return 具有相同值的新域（非空）
     */
    @SuppressWarnings("unchecked")
    default DOMAIN convertTo() {
        // Get parameterized type
        ParameterizedType currentType = parameterizedType();

        // Assert not equal
        Objects.requireNonNull(currentType, "Cannot fetch actual type because parameterized type is null");

        Class<DOMAIN> domainClass = (Class<DOMAIN>) currentType.getActualTypeArguments()[0];

        return BeanUtils.transformFrom(this, domainClass);

    }

    /**
     * Update a domain by dto.(shallow)
     *
     * @param domain updated domain
     */
    default void update(DOMAIN domain) {
        BeanUtils.updateProperties(this, domain);
    }

    /**
     * 获取泛化类型
     *
     * @return 泛化类型获取为空
     */
    @Nullable
    default ParameterizedType parameterizedType() {
        return ReflectionUtils.getParameterizedType(InputConverter.class, this.getClass());
    }

}
