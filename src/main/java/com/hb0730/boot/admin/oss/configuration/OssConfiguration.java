package com.hb0730.boot.admin.oss.configuration;

import com.hb0730.boot.admin.commons.constant.enums.AttachmentTypeEnum;
import com.hb0730.boot.admin.configuration.properties.BootAdminProperties;
import com.hb0730.boot.admin.oss.configuration.properties.OssProperties;
import com.hb0730.boot.admin.oss.handler.OssHandler;
import com.hb0730.boot.admin.oss.handler.impl.AliOssFileHandler;
import com.hb0730.boot.admin.oss.handler.impl.LocalOssHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author bing_huang
 * @date 2020/06/27 13:03
 * @since V2.0
 */
@Configuration
@AllArgsConstructor
public class OssConfiguration {
    private final OssPropertiesHelper heper;
    private final BootAdminProperties properties;

    @Bean
    public OssHandler ossHandler() {
        AttachmentTypeEnum attachmentType = properties.getAttachmentType();
        OssProperties properties = heper.getProperties(attachmentType);
        OssHandler handler = null;
        switch (attachmentType) {
            case LOCAL:
                handler = new LocalOssHandler(properties);
                break;
            case ALIOSS:
                handler = new AliOssFileHandler(properties);
                break;
            default:
                handler = new LocalOssHandler(properties);
        }
        return handler;
    }
}
