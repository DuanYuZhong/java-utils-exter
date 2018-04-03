package com.duanyu.utils.json;

/**
 * Json适配器接口
 */
public interface JsonConverter {

    /**
     * 序列化
     * @param object 对象
     * @return 转换后对字符串
     */
    String serialize(Object object);

    /**
     * 反序列化
     * @param json json字符串
     * @param clazz 转换后对类型
     * @return 转换后的对象
     */
    <T> T deserialize(String json, Class<T> clazz);
}
