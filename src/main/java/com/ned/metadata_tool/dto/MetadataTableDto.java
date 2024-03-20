package com.ned.metadata_tool.dto;

import java.util.List;

public class MetadataTableDto extends BaseDto{
    private String tableName;
    private String tableType;
    private String schemaName;
    private String tableCatalog;
    private Boolean isDeletedFromSourceDB;
    private List<MetadataColumnDto> columnList;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getTableCatalog() {
        return tableCatalog;
    }

    public void setTableCatalog(String tableCatalog) {
        this.tableCatalog = tableCatalog;
    }

    public Boolean getDeletedFromSourceDB() {
        return isDeletedFromSourceDB;
    }

    public void setDeletedFromSourceDB(Boolean deletedFromSourceDB) {
        isDeletedFromSourceDB = deletedFromSourceDB;
    }

    public List<MetadataColumnDto> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<MetadataColumnDto> columnList) {
        this.columnList = columnList;
    }
}
