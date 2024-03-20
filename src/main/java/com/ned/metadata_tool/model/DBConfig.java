package com.ned.metadata_tool.model;

import javax.persistence.*;

@Entity
@Table(name = "db_configs")
public class DBConfig extends BaseEntity{
    private String url;
    private String username;
    private String password;
    private String type;
    private String catalogName;
    private String schemaName;
    private Boolean loadStatus;

    public DBConfig() {
    }

    public DBConfig(String url, String username, String password, String type, String catalogName, String schemaName, Boolean loadStatus) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.type = type;
        this.catalogName = catalogName;
        this.schemaName = schemaName;
        this.loadStatus = loadStatus;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public Boolean getLoadStatus() {
        return loadStatus;
    }

    public void setLoadStatus(Boolean loadStatus) {
        this.loadStatus = loadStatus;
    }
}
