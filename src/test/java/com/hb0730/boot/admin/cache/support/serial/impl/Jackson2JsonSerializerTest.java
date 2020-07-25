package com.hb0730.boot.admin.cache.support.serial.impl;

import com.hb0730.boot.admin.cache.support.serial.GlobalSerializeMap;
import org.junit.Test;

public class Jackson2JsonSerializerTest {
    Jackson2JsonStringSerializer serializer = new Jackson2JsonStringSerializer(true);

    @Test
    public void serialize() throws Exception {
        GlobalSerializeMap.put(Jackson2JsonStringSerializer.IDENTITY_NUMBER, Jackson2JsonStringSerializer.JSON_STRING_SERIALIZER);
        byte[] bytes = serializer.serialize("test");
        Object s = serializer.deserialize(bytes);
        System.out.println(s);
    }
}
