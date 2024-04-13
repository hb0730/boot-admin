package com.hb0730.base.oss.core.s3;

import cn.hutool.core.io.FileUtil;
import com.hb0730.base.oss.core.BeforeTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;


@Slf4j
class S3OssStorageTest extends BeforeTest {

    protected S3OssStorage storage;

    @BeforeEach
    void beforeInit() {
        storage = new S3OssStorage(getS3OssProperties());
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
        String accessUrl = storage.uploadFile("test_1712996256951.txt", FileUtil.file("./test.txt"));
        log.info("accessUrl:{}", accessUrl);
    }

    @Test
    @DisplayName("上传文件")
    void testUploadFile1() {
        String accessUrl = storage.uploadFile("test_1712996256951.txt", "hb0730-s0", FileUtil.file("./test.txt"));
        log.info("accessUrl:{}", accessUrl);
    }

    @Test
    @DisplayName("文件流上传")
    void upload() {
        InputStream is = FileUtil.getInputStream("./test.txt");
        String accessUrl = storage.upload("test_1712996256951.txt", is);
        log.info("accessUrl:{}", accessUrl);
    }

    @Test
    @DisplayName("文件流上传")
    void testUpload() {
        InputStream is = FileUtil.getInputStream("./test.txt");
        String accessUrl = storage.upload("test_1712996256951.txt", "hb0730-s0", is);
        log.info("accessUrl:{}", accessUrl);
    }

    @Test
    @DisplayName("获取文件流")
    void getFile() {
        InputStream file = storage.getFile("test_1712996256951.txt", "hb0730-s0");
        Assertions.assertNotNull(file, "文件不存在");
    }

    @Test
    @DisplayName("获取文件流")
    void testGetFile() {
        InputStream file = storage.getFile("test_1712996256951.txt");
        Assertions.assertNotNull(file, "文件不存在");
    }

    @Test
    @DisplayName("获取分享accessUrl")
    void getShareUrl() {
        String shareUrl = storage.getShareUrl("test_1712996256951.txt");
        log.info("shareUrl:{}", shareUrl);

    }

    @Test
    @DisplayName("根据accessUrl删除")
    void deleteUrl() {
        storage.deleteUrl("https://hk-oss.hb0730.me/test_1712996256951.txt");
    }

    @Test
    @DisplayName("删除文件")
    void removeObject() {
        storage.removeObject("test_1712996256951.txt", "hb0730-s0");
    }
}