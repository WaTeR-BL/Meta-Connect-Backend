package com.ned.metadata_tool.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="metadata_tables")
public class MetadataTable extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "db_config_id")
    private DBConfig dbConfig;
    private String tableName;
    private String tableType;
    private String schemaName;
    private String tableCatalog;
    private Integer objectId;
    private Boolean isDeletedFromSourceDB;
    @OneToMany(mappedBy="dbMetadataTable", cascade = CascadeType.ALL)
    private List<MetadataColumn> columnList;

    public MetadataTable(DBConfig dbConfig, String tableName, String tableType, String schemaName, String tableCatalog, Integer objectId, Boolean isDeletedFromSourceDB, List<MetadataColumn> columnList) {
        this.dbConfig = dbConfig;
        this.tableName = tableName;
        this.tableType = tableType;
        this.schemaName = schemaName;
        this.tableCatalog = tableCatalog;
        this.objectId = objectId;
        this.isDeletedFromSourceDB = isDeletedFromSourceDB;
        this.columnList = columnList;
    }

    public MetadataTable() {
    }

    public DBConfig getDbConfig() {
        return dbConfig;
    }

    public void setDbConfig(DBConfig dbConfig) {
        this.dbConfig = dbConfig;
    }

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

    public List<MetadataColumn> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<MetadataColumn> columnList) {
        this.columnList = columnList;
    }
}
