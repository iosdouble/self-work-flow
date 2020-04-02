package com.nh.support.veriable;

import org.flowable.engine.common.api.FlowableIllegalArgumentException;

/**
 * @Classname DoubleRestVariableConverter
 * @Description TODO
 * @Date 2020/4/2 6:37 PM
 * @Created by nihui
 */
public class DoubleRestVariableConverter implements RestVariableConverter {
    @Override
    public String getRestTypeName() {
        return "double";
    }

    @Override
    public Class<?> getVariableType() {
        return Double.class;
    }

    @Override
    public Object getVariableValue(RestVariable result) {
        if (result.getValue() != null) {
            try {
                return Double.valueOf(String.valueOf(result.getValue()));
            } catch (Exception e) {
                throw new FlowableIllegalArgumentException("Converter can only convert doubles");
            }
        }
        return null;
    }

    @Override
    public void convertVariableValue(Object variableValue, RestVariable result) {
        if (variableValue != null) {
            if (!(variableValue instanceof Double)) {
                throw new FlowableIllegalArgumentException("Converter can only convert doubles");
            }
            result.setValue(variableValue);
        } else {
            result.setValue(null);
        }
    }
}
