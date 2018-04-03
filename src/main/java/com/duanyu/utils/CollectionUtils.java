package com.duanyu.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * 集合工具类
 */
public class CollectionUtils {   

    private CollectionUtils() {}

    /**
     * 判断是否为空.
     */
    public static boolean isEmpty(Collection collection) {
        return (collection == null) || collection.isEmpty();
    }

    /**
     * 判断是否为空.
     */
    public static boolean isEmpty(Map map) {
        return (map == null) || map.isEmpty();
    }

    /**
     * 判断是否为空.
     */
    public static boolean isNotEmpty(Collection collection) {
        return (collection != null) && !(collection.isEmpty());
    }

    /**
     * 校验是否为空
     * @param reference 传入对象
     * @param <T> 返回对象
     * @return t
     */
    private static <T> void checkNotNull (T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
    }

    /**
     * 校验是否为空
     * @param reference 对象
     * @param msg 打印信息
     * @param <T> 返回对象类型
     * @return t
     */
    private static <T> T checkNotNull(T reference, String msg) {
        if (reference == null) {
            throw new NullPointerException(msg);
        }
        return reference;
    }
    /**
     * 将列表转换为另一个列表
     * @param collections 列表
     * @param fc function函数
     * @param <R> 输入类型
     * @param <T> 返回类型
     * @return list
     */
    public static <T, R> List<T> collect(Collection<R> collections, Function<R, T> fc) {
        checkNotNull(collections);
        checkNotNull(fc);
        return collections.stream().filter(Objects::nonNull).map(fc).collect(toList());
    }

    /**
     * 将集合转换成map
     * @param collections 集合
     * @param fc function函数
     * @param <V> 值
     * @param <K> 键
     * @return map
     */
    public static <K, V> Map<K, V> collection2Map(Collection<V> collections, Function<V, K> fc) {
        checkNotNull(collections);
        checkNotNull(fc);
        return filter(collections).collect(Collectors.toMap(fc, Function.identity()));
    }

    /**
     * 将集合转换成map
     * @param collections 集合
     * @param fc function函数
     * @param <R> 集合类型
     * @param <K> 键
     * @param <V> 值
     * @return map
     */
    public static <K, V, R> Map<K, V> collection2Map(Collection<R> collections, Function<R, K> fc, Function<R, V> fc2) {
        checkNotNull(collections);
        checkNotNull(fc);
        return filter(collections).collect(Collectors.toMap(fc, fc2));
    }

    /**
     * 将集合转换成 Map<key, List>
     * @param collections 集合
     * @param fc 函数
     * @param <V> 键
     * @param <K> 值列表
     * @return map
     */
    public static <K, V> Map<K, List<V>> groupBy(Collection<V> collections, Function<V, K> fc) {
        checkNotNull(collections);
        checkNotNull(fc);
        return collections.stream().collect(Collectors.groupingBy(fc));
    }

    /**
     * 将集合转换成stream流并过滤null值
     * @param collections 集合
     * @param <E> 对象
     * @return stream
     */
    private static <E> Stream<E> filter(Collection<E> collections) {
        return collections.stream().filter(Objects::nonNull);
    }

    /**
     * 过滤集合返回列表
     * @param collections 输入集合
     * @param predicate 过滤条件
     * @param <E> 对象
     * @return list
     */
    public static <E> List<E> filter(Collection<E> collections, Predicate<E> predicate) {
        checkNotNull(collections);
        checkNotNull(predicate);
        return collections.stream().filter(predicate).collect(toList());
    }

    /**
     * 将列表转换成字符串 "，"分割的字符串
     * @param collections 集合
     * @param fc 将对象转换成字符串函数
     * @return
     */
    public static <E> String cancat(Collection<E> collections, Function<E, ? extends CharSequence> fc) {
        checkNotNull(collections);
        checkNotNull(fc);
        return filter(collections).map(fc).collect(Collectors.joining(","));
    }
}
