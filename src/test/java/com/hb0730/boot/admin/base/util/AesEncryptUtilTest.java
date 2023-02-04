package com.hb0730.boot.admin.base.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class AesEncryptUtilTest {

    @Test
    @DisplayName("测试aes加密与解密是否一致")
    void testAesEncrypt() {
        String content = "qwe123!@#";
        String data = AesEncryptUtil.encrypt(content);
        String _data = AesEncryptUtil.desEncrypt(data);
        Assertions.assertEquals(content, _data, "加密失败，加密前数据与解密结果不一致");
    }
}
