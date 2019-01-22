package com.niocoder.beans;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 描述bean的constructor-arg的所有属性 集合 如  <constructor-arg ref="accountDao"/>
 *
 * @author zhenglongfei
 */
public class ConstructorArgument {

    private final List<ValueHolder> argumentValues = new LinkedList<ValueHolder>();

    public ConstructorArgument() {
    }

    public void addArgumentValue(ValueHolder valueHolder) {
        this.argumentValues.add(valueHolder);
    }

    public List<ValueHolder> getArgumentValues() {
        return Collections.unmodifiableList(this.argumentValues);
    }

    public boolean isEmpty() {
        return this.argumentValues.isEmpty();
    }

    public int getArgumentCount() {
        return this.argumentValues.size();
    }

    public void clear() {
        this.argumentValues.clear();
    }

    /**
     * 对应每一个<constructor-arg ref="accountDao"/>标签内容
     */
    public static class ValueHolder {

        private Object value;

        private String type;

        private String name;

        public ValueHolder(Object value) {
            this.value = value;
        }

        public ValueHolder(Object value, String type) {
            this.value = value;
            this.type = type;
        }


        public ValueHolder(Object value, String type, String name) {
            this.value = value;
            this.type = type;
            this.name = name;
        }


        public void setValue(Object value) {
            this.value = value;
        }


        public Object getValue() {
            return this.value;
        }


        public void setType(String type) {
            this.type = type;
        }


        public String getType() {
            return this.type;
        }

        public void setName(String name) {
            this.name = name;
        }


        public String getName() {
            return this.name;
        }
    }
}
