package com.hb0730.oss.core;

import com.hb0730.base.exception.OssException;
import com.hb0730.base.utils.StrUtil;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/13
 */
@Setter
@Getter
public abstract class AbstractOssStorage implements OssStorage {
    protected OssProperties ossProperties;

    public AbstractOssStorage() {
    }

    public AbstractOssStorage(OssProperties ossProperties) {
        this.ossProperties = ossProperties;
    }

    /**
     * 获取客户端
     *
     * @param <T> 客户端
     * @return 客户端
     */
    @Nullable
    public abstract <T> T getClient();


    /**
     * 值是否存在
     *
     * @param val     值
     * @param message 提示信息
     * @return this
     */
    public AbstractOssStorage has(String val, String message) {
        if (StrUtil.isBlank(val)) {
            throw new OssException(message);
        }
        return this;
    }

    /**
     * 值是否存在
     *
     * @param val     值
     * @param message 提示信息
     * @return this
     */
    public AbstractOssStorage has(Object val, String message) {
        if (null == val) {
            throw new OssException(message);
        }
        return this;
    }

}