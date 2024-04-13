package com.hb0730.base.oss.core.aliyun;


import cn.hutool.core.io.FileUtil;
import com.hb0730.base.oss.core.BeforeTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

@Slf4j
class AliyunOssStorageTest extends BeforeTest {

    private AliyunOssStorage storage;

    @BeforeEach
    void beforeInit() {
        storage = new AliyunOssStorage(getAliyunOssProperties());
        storage.init();
    }

    @Test
    @DisplayName("上传文件")
    void uploadFile() {
        String accessUrl = storage.uploadFile(FileUtil.file("./test.txt"));
        log.info("accessUrl:{}", accessUrl);
    }

    @Test
    @DisplayName("上传文件")
    void testUploadFile() {
        String accessUrl = storage.uploadFile("test_1712982382376.txt", FileUtil.file("./test.txt"));
        log.info("accessUrl:{}", accessUrl);
    }

    @Test
    @DisplayName("上传文件")
    void testUploadFile1() {
        String accessUrl = storage.uploadFile("test_1712982382376.txt", "hb0730-s0", FileUtil.file("./test.txt"));
        log.info("accessUrl:{}", accessUrl);
    }

    @Test
    @DisplayName("上传文件")
    void upload() {
        InputStream is = FileUtil.getInputStream("./test.txt");
        String accessUrl = storage.upload("test_1712982382376.txt", is);
        log.info("accessUrl:{}", accessUrl);
    }

    @Test
    @DisplayName("上传文件")
    void testUpload() {
        String accessUrl = storage.upload("test_1712982382376.txt", "hb0730-s0", FileUtil.getInputStream("./test.txt"));
        log.info("accessUrl:{}", accessUrl);
    }

    @Test
    @DisplayName("获取文件")
    void getFile() {
        InputStream file = storage.getFile("test_1712982382376.txt");
        log.info("file:{}", file);
        Assertions.assertNotNull(file);
    }

    @Test
    @DisplayName("获取文件")
    void getShareUrl() {
        String shareUrl = storage.getShareUrl("test_1712982382376.txt");
        log.info("shareUrl:{}", shareUrl);
    }

    @Test
    @DisplayName("根据accessUrl删除文件")
    void deleteUrl() {
        storage.deleteUrl("https://hk-oss.hb0730.me/test_1712982382376.txt");
    }

    @Test
    @DisplayName("删除文件")
    void removeObject() {
        storage.removeObject("test_1712982382376.txt", "hb0730-s0");
    }


}