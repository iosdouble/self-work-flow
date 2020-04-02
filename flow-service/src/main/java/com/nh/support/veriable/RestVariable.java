package com.nh.support.veriable;

/**
 * @Classname RestVariable
 * @Description TODO 流程变量
 * @Date 2020/4/2 6:34 PM
 * @Created by nihui
 */
public class RestVariable {
    private String name;
    private String type;
    private Object value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
