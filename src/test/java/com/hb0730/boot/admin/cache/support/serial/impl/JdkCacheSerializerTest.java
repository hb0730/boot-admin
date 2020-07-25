package com.hb0730.boot.admin.cache.support.serial.impl;

import org.junit.Test;

public class JdkCacheSerializerTest {
    JdkCacheSerializer jdkSerializer = new JdkCacheSerializer(true);

    @Test
    public void serialize() throws Exception {
        byte[] bytes = jdkSerializer.serialize("test");
        Object o = jdkSerializer.deserialize(bytes);
        System.out.println(o);
    }
}
