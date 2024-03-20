package com.ned.metadata_tool.dto;

import com.ned.metadata_tool.model.MetadataTable;

import java.util.List;

public class MetadataColumnDto extends BaseDto{

    private MetadataTable dbMetadataTable;
    private String columnName;
    private String datatype;
    private Integer maximumCharacterLength;
    private String isNullable;
    private Integer ordinalPosition;
    private Boolean isDeletedFromSourceDB;
    private List<BusinessDomainEntityDto> columnEntityMapDto;

    public MetadataTable getDbMetadataTable() {
        return dbMetadataTable;
    }

    public void setDbMetadataTable(MetadataTable dbMetadataTable) {
        this.dbMetadataTable = dbMetadataTable;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public Integer getMaximumCharacterLength() {
        return maximumCharacterLength;
    }

    public void setMaximumCharacterLength(Integer maximumCharacterLength) {
        this.maximumCharacterLength = maximumCharacterLength;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    public Integer getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(Integer ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    public Boolean getDeletedFromSourceDB() {
        return isDeletedFromSourceDB;
    }

    public void setDeletedFromSourceDB(Boolean deletedFromSourceDB) {
        isDeletedFromSourceDB = deletedFromSourceDB;
    }

    public List<BusinessDomainEntityDto> getColumnEntityMapDto() {
        return columnEntityMapDto;
    }

    public void setColumnEntityMapDto(List<BusinessDomainEntityDto> columnEntityMapDto) {
        this.columnEntityMapDto = columnEntityMapDto;
    }
}
