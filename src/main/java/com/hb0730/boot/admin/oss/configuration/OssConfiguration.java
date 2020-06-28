package com.hb0730.boot.admin.oss.configuration;

import com.hb0730.boot.admin.commons.constant.enums.AttachmentTypeEnum;
import com.hb0730.boot.admin.configuration.properties.BootAdminProperties;
import com.hb0730.boot.admin.oss.configuration.factory.OssFactory;
import com.hb0730.boot.admin.oss.configuration.registry.OssRegistry;
import com.hb0730.boot.admin.oss.handler.OssHandler;
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
    private final OssFactory factory;

    /**
     * 创建唯一的oss handler
     *
     * @return {@link OssHandler}
     */
    @Bean
    public OssHandler ossHandler() {
        AttachmentTypeEnum attachmentType = properties.getAttachmentType();
        // 调用
        Map<String, OssRegistry> beans = context.getBeansOfType(OssRegistry.class);
        for (OssRegistry value : beans.values()) {
            value.registry();
        }
        return factory.getHandler(attachmentType);
    }
}
