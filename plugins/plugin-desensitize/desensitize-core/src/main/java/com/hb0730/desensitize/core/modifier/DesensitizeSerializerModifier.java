package com.hb0730.desensitize.core.modifier;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.hb0730.desensitize.core.serializer.FiledDesensitizeSerializer;

import java.util.List;

/**
 * 针对Bean的序列化器修改器
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/11
 */
public class DesensitizeSerializerModifier extends BeanSerializerModifier {
    private final List<String> desensitizeFields;
    private final List<String> ignoreFields;

    public DesensitizeSerializerModifier(List<String> desensitizeFields, List<String> ignoreFields) {
        this.desensitizeFields = desensitizeFields;
        this.ignoreFields = ignoreFields;
    }

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
        beanProperties.removeIf(beanPropertyWriter -> ignoreFields.contains(beanPropertyWriter.getName()));
        beanProperties.forEach(beanPropertyWriter -> {
            if (desensitizeFields.contains(beanPropertyWriter.getName())) {
                beanPropertyWriter.assignNullSerializer(new FiledDesensitizeSerializer());
            }
        });
        return beanProperties;
    }
}
