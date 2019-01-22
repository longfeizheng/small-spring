package com.niocoder.beans;

import com.niocoder.beans.propertyeditors.CustomBooleanEditor;
import com.niocoder.beans.propertyeditors.CustomDateEditor;
import com.niocoder.beans.propertyeditors.CustomNumberEditor;
import com.sun.corba.se.impl.io.TypeMismatchException;

/**
 * 封装类型转换的接口
 *
 * @author zhenglongfei
 * @see CustomBooleanEditor
 * @see CustomDateEditor
 * @see CustomNumberEditor
 */
public interface TypeConverter {

    /**
     * 用于类型转换
     *
     * @param value        bean.xml中value或者ref
     * @param requiredType 需要转换的类型
     * @param <T>
     * @return
     * @throws TypeMismatchException
     */
    <T> T convertIfNecessary(Object value, Class<T> requiredType) throws TypeMismatchException;
}
