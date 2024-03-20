package com.ned.metadata_tool.common;

import org.apache.commons.beanutils.BeanUtilsBean;

import java.lang.reflect.InvocationTargetException;

public class NullAwareBeanUtilsBean extends BeanUtilsBean {

    private static final NullAwareBeanUtilsBean bean = new NullAwareBeanUtilsBean();

    @Override
    public void copyProperty(Object dest, String name, Object value)
            throws IllegalAccessException, InvocationTargetException {
        if(value == null)
            return;
        if(value instanceof String && ((String) value).isEmpty())
            return;
        super.copyProperty(dest, name, value);
    }

    private NullAwareBeanUtilsBean(){}

    public static NullAwareBeanUtilsBean get(){
        return bean;
    }
}