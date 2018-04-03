package com.duanyu.utils.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonConverter implements JsonConverter {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        // 有对象不存在的字段的适合不抛出异常
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 忽略null值
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 解决序列化时属性aBc变abc的问题
        MAPPER.setPropertyNamingStrategy(new NameFieldStrategy());
    }

    /**
     * 序列化
     * @param object 待序列化的对象
     * @return
     */
    @Override
    public String serialize(Object object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException("序列化失败");
        }

    }

    /**
     * 反序列化
     * @param json json对象
     * @param clazz 类
     * @param <T> 反序列化后的对象
     * @return 反序列化后的对象
     */
    @Override
    public <T> T deserialize(String json, Class<T> clazz) {
        try {
            return MAPPER.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("反序列化失败");
        }
    }
}
