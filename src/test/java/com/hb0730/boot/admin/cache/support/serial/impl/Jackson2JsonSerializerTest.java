package com.hb0730.boot.admin.cache.support.serial.impl;

import org.junit.Test;

public class Jackson2JsonSerializerTest {
    Jackson2JsonStringSerializer serializer = new Jackson2JsonStringSerializer(true);

    @Test
    public void serialize() throws Exception {
        byte[] bytes = serializer.serialize("test");
        Object s = serializer.deserialize(bytes);
        System.out.println(s);
    }
}
