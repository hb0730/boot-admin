package com.hb0730.base.oss.core;

import cn.hutool.core.io.FileUtil;
import com.hb0730.base.oss.core.aliyun.AliyunOssProperties;
import com.hb0730.base.oss.core.s3.S3OssProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/13
 */
public class BeforeTest {

    protected AliyunOssProperties getAliyunOssProperties() {
        return AliyunOssProperties.builder()
                .accessKey("")
                .secretKey("")
                .region("Auto")
                .endpoint("")
                .bucketName("")
                .customDomain("")
                .build();
    }

    protected S3OssProperties getS3OssProperties() {
        return S3OssProperties.builder()
                .accessKey("")
                .secretKey("")
                .region("Auto")
                .endpoint("")
                .bucketName("hb0730-s0")
                .customDomain("")
                .endpointProtocol(S3OssProperties.Protocol.HTTPS)
                .pathStyleAccess(false)
                .build();
    }

    @Test
    @DisplayName("写入文件")
    void writeFiles() {
        String content = "xxx,xxx,xxx";

        FileUtil.writeString(content, "./test.txt", "utf-8");
    }
}
