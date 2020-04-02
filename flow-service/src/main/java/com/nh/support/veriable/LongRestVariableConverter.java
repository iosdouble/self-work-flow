package com.nh.support.veriable;

import org.flowable.engine.common.api.FlowableIllegalArgumentException;

/**
 * @Classname LongRestVariableConverter
 * @Description TODO
 * @Date 2020/4/2 6:39 PM
 * @Created by nihui
 */
public class LongRestVariableConverter implements RestVariableConverter{
    @Override
    public String getRestTypeName() {
        return "long";
    }

    @Override
    public Class<?> getVariableType() {
        return Long.class;
    }

    @Override
    public Object getVariableValue(RestVariable result) {
        if (result.getValue() != null) {
            try {
                return Long.valueOf(String.valueOf(result.getValue()));
            } catch (Exception e) {
                throw new FlowableIllegalArgumentException("Converter can only convert longs");
            }
        }
        return null;
    }

    @Override
    public void convertVariableValue(Object variableValue, RestVariable result) {
        if (variableValue != null) {
            if (!(variableValue instanceof Long)) {
                throw new FlowableIllegalArgumentException("Converter can only convert integers");
            }
            result.setValue(variableValue);
        } else {
            result.setValue(null);
        }
    }
}
