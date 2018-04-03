package com.duanyu.utils;

import com.duanyu.utils.json.JacksonConverter;
import com.duanyu.utils.json.JsonConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Json工具类
 */
public class JsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    private static final JsonConverter DEFAULT_SERIALIZER = new JacksonConverter();

    /**
     * 序列化
     * @param object 待序列化的对象
     * @return json字符串对象
     */
    public static String serialize(Object object) {
        return DEFAULT_SERIALIZER.serialize(object);
    }

    /**
     * 反序列化
     * @param json json对象
     * @param clazz 类
     * @param <T> 反序列化后的对象
     * @return 反序列化后的对象
     */
    public static <T> T deserialize(String json, Class<T> clazz) {
        return DEFAULT_SERIALIZER.deserialize(json, clazz);
    }

}
