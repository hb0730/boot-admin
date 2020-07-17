package com.hb0730.boot.admin.utils.file;

import com.hb0730.boot.admin.commons.constant.SystemConstants;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

/**
 * @author bing_huang
 * @date 2020/06/30 8:58
 * @since V1.0
 */
public class FileUtils {
    /**
     * 将文件分隔符更改为url分隔符。
     *
     * @param pathname 完整路径名
     * @return 带url分隔符的文本
     */
    public static String changeFileSeparatorToUrlSeparator(@NonNull String pathname) {
        Assert.hasText(pathname, "Path name must not be blank");
        return pathname.replace(SystemConstants.FILE_SEPARATOR, "/");
    }
}
