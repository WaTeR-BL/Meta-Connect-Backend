package com.ned.metadata_tool.dto;

import java.util.List;

public class BusinessDomainEntityDto extends BaseDto{
    private String name;
    private String description;
    private List<MetadataColumnDto> mappedDBMetaDataColumnDto;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MetadataColumnDto> getMappedDBMetaDataColumnDto() {
        return mappedDBMetaDataColumnDto;
    }

    public void setMappedDBMetaDataColumnDto(List<MetadataColumnDto> mappedDBMetaDataColumnDto) {
        this.mappedDBMetaDataColumnDto = mappedDBMetaDataColumnDto;
    }
}
