package com.nh.support.veriable;

import org.flowable.engine.common.api.FlowableIllegalArgumentException;

/**
 * @Classname IntegerRestVariableConverter
 * @Description TODO
 * @Date 2020/4/2 6:38 PM
 * @Created by nihui
 */
public class IntegerRestVariableConverter implements  RestVariableConverter {
    @Override
    public String getRestTypeName() {
        return "integer";
    }

    @Override
    public Class<?> getVariableType() {
        return Integer.class;
    }

    @Override
    public Object getVariableValue(RestVariable result) {
        if (result.getValue() != null) {
            try {
                return Integer.valueOf(String.valueOf(result.getValue()));
            } catch (Exception e) {
                throw new FlowableIllegalArgumentException("Converter can only convert integers");
            }
        }
        return null;
    }

    @Override
    public void convertVariableValue(Object variableValue, RestVariable result) {
        if (variableValue != null) {
            if (!(variableValue instanceof Integer)) {
                throw new FlowableIllegalArgumentException("Converter can only convert integers");
            }
            result.setValue(variableValue);
        } else {
            result.setValue(null);
        }
    }

}
