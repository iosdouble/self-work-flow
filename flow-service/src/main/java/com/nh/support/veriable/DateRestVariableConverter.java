package com.nh.support.veriable;

import org.apache.commons.lang3.time.FastDateFormat;
import org.flowable.engine.common.api.FlowableIllegalArgumentException;

import java.text.ParseException;
import java.util.Date;

/**
 * @Classname DateRestVariableConverter
 * @Description TODO
 * @Date 2020/4/2 6:36 PM
 * @Created by nihui
 */
public class DateRestVariableConverter implements RestVariableConverter {

    private static final FastDateFormat longDateFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    @Override
    public String getRestTypeName() {
        return "date";
    }

    @Override
    public Class<?> getVariableType() {
        return Date.class;
    }

    @Override
    public Object getVariableValue(RestVariable result) {
        if (result.getValue() != null) {
            try {
                return longDateFormat.parse(String.valueOf(result.getValue()));
            } catch (ParseException e) {
                throw new FlowableIllegalArgumentException("The given variable value is not a date: '" + result.getValue() + "'", e);
            }
        }
        return null;
    }

    @Override
    public void convertVariableValue(Object variableValue, RestVariable result) {
        if (variableValue != null) {
            if (!(variableValue instanceof Date)) {
                throw new FlowableIllegalArgumentException("Converter can only convert booleans");
            }
            result.setValue(longDateFormat.format(variableValue));
        } else {
            result.setValue(null);
        }
    }
}
