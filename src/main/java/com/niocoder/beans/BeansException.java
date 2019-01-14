package com.niocoder.beans;

/**
 * bean的异常类
 *
 * @author zhenglongfei
 */
public class BeansException extends RuntimeException {

    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}