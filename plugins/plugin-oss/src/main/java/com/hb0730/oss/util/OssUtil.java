package com.hb0730.oss.util;


import com.hb0730.base.utils.StrUtil;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/24
 */
public class OssUtil {

    /**
     * 获取 前缀+objectKey
     *
     * @param objectKey objectKey
     * @return zoom/{objectKey}
     */
    public static String getObjectKey(String objectKey) {
        return "zoom/" + objectKey;
    }

    /**
     * 重命名文件
     *
     * @param fileName 文件名
     * @param path     路径
     * @return 文件名
     */
    public static String renameObjectKey(String fileName, String path) {
        String bizPath = path;
        if (StrUtil.isNotBlank(path) && !bizPath.endsWith("/")) {
            bizPath = bizPath.concat("/");
        }

        String orgName = fileName;
        if (StrUtil.isBlank(orgName)) {
            orgName = fileName;
        }
        // 文件名= 文件名+时间戳+后缀
        String objectName = orgName.substring(0, orgName.lastIndexOf(".")) + "_" + System.currentTimeMillis() + orgName.substring(orgName.indexOf("."));

        if (StrUtil.isBlank(bizPath)) {
            return objectName;
        }
        return bizPath + objectName;
    }

    /**
     * 正规化objectKey
     * <p>
     * 1. 去掉前后的/
     * 2. 去掉中间的//
     * 3. 返回
     *
     * @param objectKey objectKey
     */
    public static String normalize(String objectKey) {
        if (StrUtil.isBlank(objectKey)) {
            return objectKey;
        }
        // 去掉前面的/
        if (objectKey.startsWith("/")) {
            objectKey = objectKey.substring(1);
        }
        // 去掉后面的/
        if (objectKey.endsWith("/")) {
            objectKey = objectKey.substring(0, objectKey.length() - 1);
        }
        // 去掉中间的//
        objectKey = objectKey.replaceAll("//", "/");
        return objectKey;
    }
}
