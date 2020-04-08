package com.hb0730.boot.admin.file.configuration;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
@Component
public class FileProperties {
    /**
     * 上传路径
     */
    private  String profile = "E:\\IdeaWork\\customer-work\\boot-admin";

    public FileProperties() throws IOException {
        // Create work directory if not exist
        Files.createDirectories(Paths.get(profile));
    }
}
