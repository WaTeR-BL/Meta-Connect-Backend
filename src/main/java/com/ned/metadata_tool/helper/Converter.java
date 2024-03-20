package com.ned.metadata_tool.helper;

import com.ned.metadata_tool.common.NullAwareBeanUtilsBean;

import java.lang.reflect.InvocationTargetException;

public interface Converter {
    default <T, U> U requestToDto(T req, Class clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Object o = Class.forName(clazz.getName()).newInstance();
        NullAwareBeanUtilsBean.get().copyProperties(o, req);
        return (U) o;
    }

    default <T, U> U modelToDto(T model, Class clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Object o = Class.forName(clazz.getName()).newInstance();
        NullAwareBeanUtilsBean.get().copyProperties(o, model);
        return (U) o;
    }


    default <T, U> U dtoToModel(T dto, Class clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Object o = Class.forName(clazz.getName()).newInstance();
        NullAwareBeanUtilsBean.get().copyProperties(o, dto);
        return (U) o;
    }

    default <T, U> U dtoToResponse(T dto, Class clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Object o = Class.forName(clazz.getName()).newInstance();
        NullAwareBeanUtilsBean.get().copyProperties(o, dto);
        return (U) o;
    }
}
