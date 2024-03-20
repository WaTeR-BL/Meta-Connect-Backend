package com.ned.metadata_tool.helper;

import com.ned.metadata_tool.dto.BusinessDomainEntityDto;
import com.ned.metadata_tool.dto.DBConfigDto;
import com.ned.metadata_tool.dto.MetadataColumnDto;
import com.ned.metadata_tool.dto.MetadataTableDto;
import com.ned.metadata_tool.enums.Flag;
import com.ned.metadata_tool.model.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public enum ModelDtoConverter implements Converter {


    METADATA_TABLE {
        @Override
        public <T, U> U requestToDto(T req, Class clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
            return super.requestToDto(req, clazz);
        }

        @Override
        public <T, U> U modelToDto(T model, Class clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
            MetadataTable dbMetadataTable = (MetadataTable) model;
            MetadataTableDto dbMetadataTableDto = super.modelToDto(model, clazz);
            List<MetadataColumnDto> dbMetadataColumnDtoList = new ArrayList<>();
            for (MetadataColumn column : dbMetadataTable.getColumnList()) {
                column.setDbMetadataTable(null);
                MetadataColumnDto dbMetadataColumnDto = DB_METADATA_COLUMN.modelToDto(column, MetadataColumnDto.class);
                dbMetadataColumnDtoList.add(dbMetadataColumnDto);
            }
            dbMetadataTableDto.setColumnList(dbMetadataColumnDtoList);

            return (U) dbMetadataTableDto;
        }

        @Override
        public <T, U> U dtoToModel(T dto, Class clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
            return super.dtoToModel(dto, clazz);
        }

        @Override
        public <T, U> U dtoToResponse(T dto, Class clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
            return super.dtoToResponse(dto, clazz);
        }
    },

    DB_METADATA_COLUMN {
        @Override
        public <T, U> U requestToDto(T req, Class clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
            return super.requestToDto(req, clazz);
        }

        @Override
        public <T, U> U modelToDto(T model, Class clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
            MetadataColumn dbMetadataColumn = (MetadataColumn) model;
            MetadataColumnDto dbMetadataColumnDto = super.modelToDto(model, clazz);
            List<BusinessDomainEntityDto> businessDomainEntitiesDtoList = new ArrayList<>();
            for (ColumnBusinessDomainEntityMap entity : dbMetadataColumn.getColumnEntityMap()) {
                if (entity.getFlag().equals(Flag.ACTIVE)) {
                    entity.setDbMetadataColumn(null);
                    BusinessDomainEntityDto businessDomainEntitiesDto = BUSINESS_DOMAIN_ENTITY.modelToDto(entity.getBusinessDomainEntity(), BusinessDomainEntityDto.class);
                    businessDomainEntitiesDtoList.add(businessDomainEntitiesDto);
                }
            }
            dbMetadataColumnDto.setColumnEntityMapDto(businessDomainEntitiesDtoList);

            return (U) dbMetadataColumnDto;

        }

        @Override
        public <T, U> U dtoToModel(T dto, Class clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
            return super.dtoToModel(dto, clazz);
        }

        @Override
        public <T, U> U dtoToResponse(T dto, Class clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
            return super.dtoToResponse(dto, clazz);
        }
    },

    DB_CONFIG {
        @Override
        public <T, U> U requestToDto(T req, Class clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
            return super.requestToDto(req, clazz);
        }

        @Override
        public <T, U> U modelToDto(T model, Class clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
            DBConfig dbConfig = (DBConfig) model;
            DBConfigDto dbConfigDto = super.modelToDto(model, clazz);
            return (U) dbConfigDto;
        }

        @Override
        public <T, U> U dtoToModel(T dto, Class clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
            DBConfigDto dbConfigDto = (DBConfigDto) dto;
            DBConfig dbConfig = super.dtoToModel(dto, clazz);
            return (U) dbConfig;
        }

        @Override
        public <T, U> U dtoToResponse(T dto, Class clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
            return super.dtoToResponse(dto, clazz);
        }
    },

    BUSINESS_DOMAIN_ENTITY {
        @Override
        public <T, U> U requestToDto(T req, Class clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
            return super.requestToDto(req, clazz);
        }

        @Override
        public <T, U> U modelToDto(T model, Class clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
            BusinessDomainEntity businessDomainEntity = (BusinessDomainEntity) model;
            BusinessDomainEntityDto businessDomainEntitiesDto = super.modelToDto(businessDomainEntity, clazz);
            return (U) businessDomainEntitiesDto;
        }

        @Override
        public <T, U> U dtoToModel(T dto, Class clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
            BusinessDomainEntityDto businessDomainEntitiesDto = (BusinessDomainEntityDto) dto;
            BusinessDomainEntity businessDomainEntity = super.dtoToModel(dto, clazz);
            return (U) businessDomainEntity;
        }

        @Override
        public <T, U> U dtoToResponse(T dto, Class clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
            return super.dtoToResponse(dto, clazz);
        }
    }

};



