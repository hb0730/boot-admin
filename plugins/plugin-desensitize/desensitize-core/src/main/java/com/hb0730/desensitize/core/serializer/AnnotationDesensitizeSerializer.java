package com.hb0730.desensitize.core.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.hb0730.base.exception.BasicException;
import com.hb0730.base.utils.DesensitizeUtil;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.desensitize.core.annotation.Desensitize;
import com.hb0730.desensitize.core.desensitization.Desensitization;
import com.hb0730.desensitize.core.desensitization.DesensitizationFactory;
import com.hb0730.desensitize.core.enums.DesensitizationType;
import lombok.Getter;

import java.io.IOException;

/**
 * 脱敏序列化器
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/10
 */
@Getter
public class AnnotationDesensitizeSerializer extends StdSerializer<Object> implements ContextualSerializer {
    private transient Desensitization<Object> desensitization;

    public AnnotationDesensitizeSerializer() {
        super(Object.class);
    }

    public void setDesensitization(Desensitization<Object> desensitization) {
        this.desensitization = desensitization;
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        Desensitize propertyAnnotation = property.getAnnotation(Desensitize.class);
        if (propertyAnnotation != null) {
            String typeValue = propertyAnnotation.value();
            if (StrUtil.isNotBlank(typeValue)) {
                return createContextual(typeValue);
            }
            DesensitizationType type = propertyAnnotation.type();
            if (null != type) {
                return createContextual(type.name());
            }
            throw new BasicException("Desensitize annotation must have value or type");
        }
        return prov.findValueSerializer(property.getType(), property);
    }

    @SuppressWarnings("unchecked")
    public JsonSerializer<Object> createContextual(String value) {
        AnnotationDesensitizeSerializer serializer = new AnnotationDesensitizeSerializer();
        serializer.setDesensitization(
                (Desensitization<Object>) DesensitizationFactory.getDesensitization(value)
        );
        return serializer;
    }

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        Desensitization<Object> objectDesensitization = getDesensitization();
        if (null != objectDesensitization) {
            try {
                gen.writeObject(objectDesensitization.desensitize(value));
            } catch (Exception e) {
                gen.writeObject(value);
            }
            return;
        } else if (value instanceof String) {
            gen.writeString(DesensitizeUtil.maskRight((String) value, ((String) value).length() - 1));
            return;
        }
        gen.writeObject(value);
    }
}
