package com.ned.metadata_tool.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "metadata_columns")
public class MetadataColumn extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "metadata_table_id")
    private MetadataTable dbMetadataTable;
    @ManyToMany(mappedBy = "dbMetadataColumn", cascade = CascadeType.ALL)
    private List<ColumnBusinessDomainEntityMap> columnEntityMap;
    private Integer ordinalPosition;
    private String columnName;
    private String datatype;
    private Integer maximumCharacterLength;
    private String isNullable;
    private Integer objectId;
    private Boolean isDeletedFromSourceDB;

    public MetadataColumn(MetadataTable dbMetadataTable, List<ColumnBusinessDomainEntityMap> columnEntityMap, Integer ordinalPosition, String columnName, String datatype, Integer maximumCharacterLength, String isNullable, Integer objectId, Boolean isDeletedFromSourceDB) {
        this.dbMetadataTable = dbMetadataTable;
        this.columnEntityMap = columnEntityMap;
        this.ordinalPosition = ordinalPosition;
        this.columnName = columnName;
        this.datatype = datatype;
        this.maximumCharacterLength = maximumCharacterLength;
        this.isNullable = isNullable;
        this.objectId = objectId;
        this.isDeletedFromSourceDB = isDeletedFromSourceDB;
    }

    public MetadataColumn() {
    }

    public MetadataTable getDbMetadataTable() {
        return dbMetadataTable;
    }

    public void setDbMetadataTable(MetadataTable dbMetadataTable) {
        this.dbMetadataTable = dbMetadataTable;
    }

    public List<ColumnBusinessDomainEntityMap> getColumnEntityMap() {
        return columnEntityMap;
    }

    public void setColumnEntityMap(List<ColumnBusinessDomainEntityMap> columnEntityMap) {
        this.columnEntityMap = columnEntityMap;
    }

    public Integer getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(Integer ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
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

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Boolean getDeletedFromSourceDB() {
        return isDeletedFromSourceDB;
    }

    public void setDeletedFromSourceDB(Boolean deletedFromSourceDB) {
        isDeletedFromSourceDB = deletedFromSourceDB;
    }
}
