package com.hb0730.desensitize.core.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.hb0730.base.exception.BasicException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * map序列化(脱敏,过滤)
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/11
 */
public class MapDesensitizeSerializer extends StdSerializer<Map<String, Object>> {
    private final List<String> desensitizeFields;
    private final List<String> ignoreFields;

    public MapDesensitizeSerializer(List<String> desensitizeFields, List<String> ignoreFields) {
        super(Map.class, false);
        this.desensitizeFields = desensitizeFields;
        this.ignoreFields = ignoreFields;
    }

    @Override
    public void serialize(Map<String, Object> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        value.forEach((k, v) -> {
            try {
                if (ignoreFields.contains(k)) {
                    gen.writeObjectField(k, null);
                } else {
                    if (desensitizeFields.contains(k)) {
                        gen.writeObjectField(k, "******");
                    } else {
                        gen.writeObjectField(k, v);
                    }
                }
            } catch (IOException e) {
                throw new BasicException(e);
            }
        });
        gen.writeEndObject();
    }
}
