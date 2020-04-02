package com.nh.support.veriable;

/**
 * @Classname RestVariableConverter
 * @Description TODO 流程变量转换接口
 * @Date 2020/4/2 6:33 PM
 * @Created by nihui
 */
public interface RestVariableConverter {
    /**
     * Simple type-name used by this converter.
     */
    String getRestTypeName();

    /**
     * Type of variables this converter is able to convert.
     */
    Class<?> getVariableType();

    /**
     * Extract the variable value to be used in the engine from the given {@link RestVariable}.
     */
    Object getVariableValue(RestVariable result);

    /**
     * Converts the given value and sets the converted value in the given {@link RestVariable}.
     */
    void convertVariableValue(Object variableValue, RestVariable result);
}
