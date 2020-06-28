package com.hb0730.boot.admin.oss.configuration.factory;

import com.hb0730.boot.admin.commons.constant.enums.AttachmentTypeEnum;
import com.hb0730.boot.admin.oss.configuration.OssConfiguration;
import com.hb0730.boot.admin.oss.configuration.registry.DefaultOssRegistry;
import com.hb0730.boot.admin.oss.handler.OssHandler;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * oss
 *
 * @author bing_huang
 * @date 2020/06/28 16:50
 * @see OssConfiguration#ossHandler()
 * @since V2.0
 */
@Component
public class OssFactory {
    /**
     * Map中的Value是 ServiceBean
     */
    private final Map<AttachmentTypeEnum, OssHandler> handlers = new HashMap<>();

    /**
     * 获取对应的handler
     *
     * @param type 类型
     * @return ossHandler
     * @see OssConfiguration#ossHandler()
     */
    public OssHandler getHandler(AttachmentTypeEnum type) {
        return handlers.get(type);
    }

    /**
     * 添加 实现类
     *
     * @param handlers handler
     * @return this
     * @see DefaultOssRegistry#registry()
     */
    @SuppressWarnings("UnusedReturnValue")
    public OssFactory addHandler(@Nullable Map<AttachmentTypeEnum, OssHandler> handlers) {
        if (!CollectionUtils.isEmpty(handlers)) {
            this.handlers.putAll(handlers);
        }
        return this;
    }

}
