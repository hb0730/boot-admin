package com.hb0730.boot.admin.oss.configuration;

import com.hb0730.boot.admin.commons.constant.enums.AttachmentTypeEnum;
import com.hb0730.boot.admin.oss.configuration.properties.OssProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @author bing_huang
 * @date 2020/06/27 12:59
 * @since V2.0
 */
@Configuration
public class OssPropertiesHelper {

    /**
     * File handler container.
     */
    private final Collection<OssProperties> fileHandlers = new LinkedList<>();

    public OssPropertiesHelper(ApplicationContext applicationContext) {
        // Add all file handler
        addFileHandlers(applicationContext.getBeansOfType(OssProperties.class).values());
    }

    public OssProperties getProperties(AttachmentTypeEnum type) {
        Assert.notNull(type, "Attachment type must not be null");
        for (OssProperties fileHandler : fileHandlers) {
            if (fileHandler.supportType(type)) {
                return fileHandler;
            }
        }
        return null;
    }

    /**
     * Adds handlers.
     *
     * @param fileHandlers handler collection
     * @return current  handlers
     */
    @NonNull
    @SuppressWarnings("UnusedReturnValue")
    public OssPropertiesHelper addFileHandlers(@Nullable Collection<OssProperties> fileHandlers) {
        if (!CollectionUtils.isEmpty(fileHandlers)) {
            this.fileHandlers.addAll(fileHandlers);
        }
        return this;
    }
}
