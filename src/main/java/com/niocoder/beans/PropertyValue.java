package com.niocoder.beans;

/**
 * 描述bean的property属性 如  <property name ="accountDao" ref="accountDao"></property>
 * @author zhenglongfei
 */
public class PropertyValue {

    /**
     * property name ="accountDao"
     */
    private final String name;

    /**
     * property ref="accountDao"
     */
    private final Object value;

    /**
     * 表示是否转换
     */
    private Boolean converted = false;

    /**
     * 转换后的实体类 ref="accountDao" 对应的 new AccountDao();
     */
    private Object convertedValue;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public synchronized boolean isConverted() {
        return this.converted;
    }

    public synchronized Object getConvertedValue() {
        return convertedValue;
    }

    public synchronized void setConvertedValue(Object convertedValue) {
        this.convertedValue = convertedValue;
    }
}
