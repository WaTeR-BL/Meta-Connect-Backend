package com.ned.metadata_tool.service;

import com.ned.metadata_tool.dto.MetadataTableDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface ListMetadataService {
    List<MetadataTableDto> findMetadataValuesByConfig(Long configId) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException;

}
