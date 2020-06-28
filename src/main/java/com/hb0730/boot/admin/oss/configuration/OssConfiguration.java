package com.hb0730.boot.admin.oss.configuration;

import com.google.common.collect.Maps;
import com.hb0730.boot.admin.commons.constant.enums.AttachmentTypeEnum;
import com.hb0730.boot.admin.configuration.properties.BootAdminProperties;
import com.hb0730.boot.admin.oss.configuration.factory.OssFactory;
import com.hb0730.boot.admin.oss.configuration.properties.OssProperties;
import com.hb0730.boot.admin.oss.configuration.registry.OssRegistry;
import com.hb0730.boot.admin.oss.handler.OssHandler;
import com.hb0730.boot.admin.oss.handler.impl.AliOssFileHandler;
import com.hb0730.boot.admin.oss.handler.impl.LocalOssHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author bing_huang
 * @date 2020/06/27 13:03
 * @since V2.0
 */
@Configuration
@AllArgsConstructor
public class OssConfiguration {
    private final OssPropertiesHelper helper;
    private final BootAdminProperties properties;
    private final ApplicationContext context;

    /**
     * 创建唯一的oss handler
     *
     * @return {@link OssHandler}
     */
    @Bean
    public OssHandler ossHandler() {
        AttachmentTypeEnum attachmentType = properties.getAttachmentType();
        OssFactory ossFactory = ossFactory();
        // 调用
        Map<String, OssRegistry> beans = context.getBeansOfType(OssRegistry.class);
        for (OssRegistry value : beans.values()) {
            value.registry();
        }
        return ossFactory.getHandler(attachmentType);
    }

    /**
     * 创建oss factory <br>
     * 默认只实例化{@link AliOssFileHandler}和{@link LocalOssHandler}
     *
     * @return {@link OssFactory}
     */
    @Bean
    public OssFactory ossFactory() {
        OssFactory factory = new OssFactory();
        Map<AttachmentTypeEnum, OssHandler> handlers = Maps.newHashMap();
        OssProperties properties = helper.getProperties(AttachmentTypeEnum.ALIOSS);
        handlers.put(AttachmentTypeEnum.ALIOSS, new AliOssFileHandler(properties));
        properties = helper.getProperties(AttachmentTypeEnum.LOCAL);
        handlers.put(AttachmentTypeEnum.LOCAL, new LocalOssHandler(properties));
        return factory.addHandler(handlers);
    }
}
