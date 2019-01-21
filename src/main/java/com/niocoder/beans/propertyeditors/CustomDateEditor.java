package com.niocoder.beans.propertyeditors;

import com.niocoder.util.DateUtil;
import com.niocoder.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.time.format.DateTimeFormatter;

/**
 * 将字符串转换成date类型
 * <property name="birthday" value="2019-01-21"></property>
 *
 * @author zhenglongfei
 */
public class CustomDateEditor extends PropertyEditorSupport {

    private final DateTimeFormatter formatter;

    private final boolean allowEmpty;

    public CustomDateEditor(boolean allowEmpty) {
        this(allowEmpty, null);
    }

    public CustomDateEditor(boolean allowEmpty, DateTimeFormatter dateTimeFormatter) {
        this.allowEmpty = allowEmpty;
        this.formatter = dateTimeFormatter;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String input = (text != null ? text.trim() : null);
        if (this.allowEmpty && !StringUtils.hasLength(input)) {
            setValue(null);
        } else if (this.formatter != null) {
            setValue(DateUtil.getDateBySr(input, formatter));
        } else {
            setValue(DateUtil.getDateBySr(input));
        }
    }

    @Override
    public String getAsText() {
        return getValue().toString();
    }
}
