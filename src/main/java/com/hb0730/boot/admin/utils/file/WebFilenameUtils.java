package com.hb0730.boot.admin.utils.file;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * <p>
 * 根据RFC 5987规范生成disposition值, 解决浏览器兼容以及中文乱码问题
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class WebFilenameUtils {


    private static final Logger LOGGER = LoggerFactory.getLogger(WebFilenameUtils.class);

    private static final String DISPOSITION_FORMAT = "attachment; filename=\"%s\"; filename*=utf-8''%s";

    /**
     * 未编码文件名转Content-Disposition值
     *
     * @param filename 未编码的文件名(包含文件后缀)
     * @return Content-Disposition值
     */
    public static String disposition(String filename) {
        String codedFilename = filename;
        if (StringUtils.isNotBlank(filename)) {
            codedFilename = java.net.URLEncoder.encode(filename, StandardCharsets.UTF_8);
        }
        return String.format(DISPOSITION_FORMAT, codedFilename, codedFilename);

    }
}
