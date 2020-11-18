package com.hb0730.boot.admin.commons.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hb0730.commons.json.exceptions.JsonException;
import com.hb0730.commons.json.jackson.JacksonImpl;
import com.hb0730.commons.json.utils.Jsons;
import com.hb0730.commons.spring.SpringContextUtils;

/**
 * json工具类
 *
 * @author bing_huang
 */
public class JsonUtils {
    /**
     * 设置jackson的Mapper
     *
     * @return {@link Jsons}
     */
    public static Jsons getJson() {
        JacksonImpl jackson = new JacksonImpl();
        try {

            jackson.setMapper(SpringContextUtils.getBean(ObjectMapper.class));
        } catch (JsonException e) {
            e.printStackTrace();
        }
        return Jsons.JSONS.setJson(jackson);
    }
}
