package com.hb0730.boot.admin.oss.configuration.registry;

/**
 * oss 注册
 *
 * @author bing_huang
 * @date 2020/06/28 17:33
 * @see com.hb0730.boot.admin.oss.configuration.factory.OssFactory
 * @see DefaultOssRegistry
 * @since V2.0
 */
@FunctionalInterface
public interface OssRegistry {

    /**
     * 注册 oss
     */
    void registry();
}
