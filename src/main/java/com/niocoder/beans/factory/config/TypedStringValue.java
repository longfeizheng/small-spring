package com.niocoder.beans.factory.config;

/**
 * <property name ="url" value="http://niocoder.com/"></property>
 * value="http://niocoder.com/"
 * 表示 引用的字符串 无需转换
 *
 * @author zhenglongfei
 */
public class TypedStringValue {

    private final String value;

    public TypedStringValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
