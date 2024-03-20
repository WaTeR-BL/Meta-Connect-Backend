package com.ned.metadata_tool.service.impl;

import com.ned.metadata_tool.dto.DBConfigDto;
import com.ned.metadata_tool.enums.Flag;
import com.ned.metadata_tool.helper.ModelDtoConverter;
import com.ned.metadata_tool.model.DBConfig;
import com.ned.metadata_tool.repo.DBConfigRepository;
import com.ned.metadata_tool.service.DBConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DBConfigServiceImpl implements DBConfigService {
    @Autowired
    DBConfigRepository dbConfigRepository;

    @Override
    public DBConfigDto add(DBConfigDto dto) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        DBConfig o = ModelDtoConverter.DB_CONFIG.dtoToModel(dto, DBConfig.class);
        dbConfigRepository.save(o);
        return dto;
    }

    @Override
    public Page<DBConfigDto> find(Pageable pageable, Flag flag) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Page<DBConfig> dbConfigs = dbConfigRepository.findAllByFlag(pageable, flag);
        List<DBConfigDto> list = new ArrayList<>();
        for (DBConfig dbConfig : dbConfigs) {
            if (dbConfig.getFlag().equals(Flag.ACTIVE)) {
                DBConfigDto dbConfigDto = ModelDtoConverter.DB_CONFIG.modelToDto(dbConfig, DBConfigDto.class);
                list.add(dbConfigDto);
            }
        }
        return new PageImpl<>(list, pageable, dbConfigs.getTotalElements());
    }

    @Override
    public DBConfigDto findOne(long id) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        return null;
    }

    @Override
    public void delete(long id) {
        Optional<DBConfig> byId = dbConfigRepository.findById(id);
        if (byId.isPresent()) {
            byId.get().setFlag(Flag.DELETED);
            dbConfigRepository.save(byId.get());
        }
    }

    @Override
    public DBConfigDto update(DBConfigDto dto) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Optional<DBConfig> dbOpt = dbConfigRepository.findById(dto.getId());
        if (!dbOpt.isPresent()) {
            return null;
        } else {
            dbOpt.get().setUrl(dto.getUrl());
            dbOpt.get().setPassword(dto.getPassword());
            dbOpt.get().setUsername(dto.getUsername());
            dbOpt.get().setCatalogName(dto.getCatalogName());
            dbOpt.get().setSchemaName(dto.getSchemaName());
            dbConfigRepository.save(dbOpt.get());
            return ModelDtoConverter.DB_CONFIG.modelToDto(dbOpt.get(), DBConfigDto.class);

        }
    }
}
