package com.hb0730.boot.admin.oss.configuration.properties.impl;

import com.hb0730.boot.admin.commons.constant.enums.AttachmentTypeEnum;
import com.hb0730.boot.admin.oss.configuration.properties.OssProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.hb0730.boot.admin.commons.constant.SystemConstants.FILE_SEPARATOR;
import static com.hb0730.boot.admin.commons.constant.SystemConstants.USER_HOME;
import static com.hb0730.boot.admin.commons.utils.commons.StringUtils.ensureSuffix;

/**
 * <p>
 * 本地
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
@ConfigurationProperties(prefix = "boot.admin.oss.local")
@Configuration
public class LocalOssProperties implements OssProperties {
    /**
     * 上传路径
     */
    private String profile = ensureSuffix(USER_HOME, FILE_SEPARATOR) + ".bootAdmin" + FILE_SEPARATOR;

    public LocalOssProperties() throws IOException {
        super();
        // Create work directory if not exist
        Files.createDirectories(Paths.get(profile));
    }

    @Override
    public boolean supportType(AttachmentTypeEnum type) {
        return AttachmentTypeEnum.LOCAL.equals(type);
    }
}
