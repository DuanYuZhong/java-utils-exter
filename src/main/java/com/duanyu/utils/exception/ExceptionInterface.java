package com.duanyu.utils.exception;

public interface ExceptionInterface {

    /**
     * 获取异常状态码
     * @return 异常状态码
     */
    String getCode();

    /**
     * 获取异常信息
     * @return 异常信息
     */
    String getMsg();
}
