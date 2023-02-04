package com.hb0730.boot.admin.base.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hb0730.jsons.support.jackson.JacksonImpl;

/**
 * json util
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/1/11
 */
public class JsonUtil extends JacksonImpl {
    public static JsonUtil DEFAULT = new JsonUtil();

    public JsonUtil() {
        ObjectMapper mapper = new ObjectMapper() {
            {
                //序列化时候，只序列化非空字段
                //this.setSerializationInclusion(Inclusion.NON_NULL);
                //or this.setSerializationConfig(this.getSerializationConfig().withSerializationInclusion(Inclusion.NON_NULL));

                // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
                this.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                this.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

                //this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
            }
        };
        setClient(mapper);
    }
}
