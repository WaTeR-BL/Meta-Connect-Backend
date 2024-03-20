package com.ned.metadata_tool.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="business_domain_entities")
public class BusinessDomainEntity extends BaseEntity {
    private String name;
    private String description;
    @ManyToMany
    @JoinTable(name = "column_business_domain_entity_map",
            joinColumns=@JoinColumn(name = "business_domain_entity_id"),
            inverseJoinColumns =@JoinColumn(name = "db_metadata_column_id")
    )
    private List<MetadataColumn> mappedDBMetaDataColumn;


    public BusinessDomainEntity() {
    }

    public BusinessDomainEntity(String name, String description, List<MetadataColumn> mappedDBMetaDataColumn) {
        this.name = name;
        this.description = description;
        this.mappedDBMetaDataColumn = mappedDBMetaDataColumn;
    }

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

    public List<MetadataColumn> getMappedDBMetaDataColumn() {
        return mappedDBMetaDataColumn;
    }

    public void setMappedDBMetaDataColumn(List<MetadataColumn> mappedDBMetaDataColumn) {
        this.mappedDBMetaDataColumn = mappedDBMetaDataColumn;
    }
}