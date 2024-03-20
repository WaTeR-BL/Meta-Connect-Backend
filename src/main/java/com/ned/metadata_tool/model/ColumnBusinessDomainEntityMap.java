package com.ned.metadata_tool.model;

import javax.persistence.*;

@Entity
@Table(name = "column_business_domain_entity_map")
public class ColumnBusinessDomainEntityMap extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "business_domain_entity_id")
    private BusinessDomainEntity businessDomainEntity;

    @ManyToOne
    @JoinColumn(name = "db_metadata_column_id")
    private MetadataColumn dbMetadataColumn;

    public ColumnBusinessDomainEntityMap(BusinessDomainEntity businessDomainEntity, MetadataColumn dbMetadataColumn) {
        this.businessDomainEntity = businessDomainEntity;
        this.dbMetadataColumn = dbMetadataColumn;
    }

    public ColumnBusinessDomainEntityMap() {
    }

    public BusinessDomainEntity getBusinessDomainEntity() {
        return businessDomainEntity;
    }

    public void setBusinessDomainEntity(BusinessDomainEntity businessDomainEntity) {
        this.businessDomainEntity = businessDomainEntity;
    }

    public MetadataColumn getDbMetadataColumn() {
        return dbMetadataColumn;
    }

    public void setDbMetadataColumn(MetadataColumn dbMetadataColumn) {
        this.dbMetadataColumn = dbMetadataColumn;
    }
}