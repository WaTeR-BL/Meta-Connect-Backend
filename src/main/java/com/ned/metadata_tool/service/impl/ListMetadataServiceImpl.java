package com.ned.metadata_tool.service.impl;

import com.ned.metadata_tool.dto.MetadataTableDto;
import com.ned.metadata_tool.helper.ModelDtoConverter;
import com.ned.metadata_tool.model.DBConfig;
import com.ned.metadata_tool.model.MetadataTable;
import com.ned.metadata_tool.repo.DBConfigRepository;
import com.ned.metadata_tool.repo.MetadataColumnRepository;
import com.ned.metadata_tool.repo.MetadataTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ned.metadata_tool.service.ListMetadataService;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ListMetadataServiceImpl implements ListMetadataService {

    @Autowired
    private MetadataTableRepository dbMetadataTableRepository;
    @Autowired
    private MetadataColumnRepository dbMetadataColumnRepository;
    @Autowired
    private DBConfigRepository dbConfigRepository;


    @Override
    public List<MetadataTableDto> findMetadataValuesByConfig(Long configId) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
            DBConfig dbConfig = new DBConfig();
            dbConfig.setId(configId);
            List<MetadataTable> metadataTables = dbMetadataTableRepository.findByDbConfig(dbConfig);
            List<MetadataTableDto> dbMetadataTableDtoList = new ArrayList<>();
            for (MetadataTable table : metadataTables) {
                MetadataTableDto dbMetadataTableDto = ModelDtoConverter.METADATA_TABLE.modelToDto(table, MetadataTableDto.class);
                dbMetadataTableDtoList.add(dbMetadataTableDto);
            }
            return dbMetadataTableDtoList;
    }
}






