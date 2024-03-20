package com.ned.metadata_tool.service;

import com.ned.metadata_tool.dto.BaseDto;
import com.ned.metadata_tool.enums.Flag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface BaseService<T extends BaseDto> {

    T add(T dto) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException;

    Page<T> find(Pageable pageable,Flag flag) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException;

    T findOne(long id) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException;

    void delete(long id);

    T update(T dto) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException;

}