package com.hb0730.boot.admin.oss.configuration.registry;

import com.hb0730.boot.admin.commons.constant.enums.AttachmentTypeEnum;
import com.hb0730.boot.admin.oss.configuration.OssPropertiesHelper;
import com.hb0730.boot.admin.oss.configuration.factory.OssFactory;
import com.hb0730.boot.admin.oss.configuration.properties.OssProperties;
import com.hb0730.boot.admin.oss.handler.OssHandler;
import com.hb0730.boot.admin.oss.handler.impl.AliOssFileHandler;
import com.hb0730.boot.admin.oss.handler.impl.LocalOssHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bing_huang
 * @date 2020/06/28 17:38
 * @since V1.0
 */
@AllArgsConstructor
@Component
public class DefaultOssRegistry implements OssRegistry {
    private final OssFactory factory;
    private final OssPropertiesHelper helper;
    Map<AttachmentTypeEnum, OssHandler> handlers = new HashMap<>(2);

    @Override
    public void registry() {
        OssProperties properties = helper.getProperties(AttachmentTypeEnum.ALIOSS);
        handlers.put(AttachmentTypeEnum.ALIOSS, new AliOssFileHandler(properties));
        properties = helper.getProperties(AttachmentTypeEnum.LOCAL);
        handlers.put(AttachmentTypeEnum.LOCAL, new LocalOssHandler(properties));
        factory.addHandler(handlers);
    }
}
