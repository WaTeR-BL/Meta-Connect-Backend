package com.ned.metadata_tool.service.impl;

import com.ned.metadata_tool.enums.Flag;
import com.ned.metadata_tool.model.BusinessDomainEntity;
import com.ned.metadata_tool.model.ColumnBusinessDomainEntityMap;
import com.ned.metadata_tool.model.MetadataColumn;
import com.ned.metadata_tool.repo.BusinessDomainEntityRepository;
import com.ned.metadata_tool.repo.ColumnBusinessDomainEntityMapRepository;
import com.ned.metadata_tool.repo.MetadataColumnRepository;
import com.ned.metadata_tool.service.CatalogMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogMappingServiceImpl implements CatalogMappingService {
    @Autowired
    private BusinessDomainEntityRepository businessDomainEntityRepository;
    @Autowired
    private MetadataColumnRepository dbMetadataColumnRepository;
    @Autowired
    private ColumnBusinessDomainEntityMapRepository columnBusinessDomainEntityMapRepository;


    @Override
    public String mapEntitiesColumn(Long dbMetadataColumnId, List<Long> businessDomainEntityIds) {
        MetadataColumn dbMetadataColumn = dbMetadataColumnRepository.findById(dbMetadataColumnId)
                .orElseThrow(() -> new EntityNotFoundException("DBMetadataColumn not found with Id: " + dbMetadataColumnId));

        List<ColumnBusinessDomainEntityMap> objects = columnBusinessDomainEntityMapRepository.findAllByDbMetadataColumnIdAndBusinessDomainEntityIdInAndFlag(dbMetadataColumnId, businessDomainEntityIds, Flag.ACTIVE);
        List<BusinessDomainEntity> businessDomainEntities = businessDomainEntityRepository.findAllById(businessDomainEntityIds);
        List<ColumnBusinessDomainEntityMap> columnBusinessDomainEntityMaps = new ArrayList<>();
        String result = null;
        List<String> entityName = new ArrayList<>();
        if (!objects.isEmpty()) {
            for (ColumnBusinessDomainEntityMap entity : objects) {
                businessDomainEntities.remove(entity.getBusinessDomainEntity());
            }
            ColumnBusinessDomainEntityMap columnBusinessDomainEntityMap = null;
            for (BusinessDomainEntity businessDomainEntity : businessDomainEntities) {
                columnBusinessDomainEntityMap = new ColumnBusinessDomainEntityMap();
                columnBusinessDomainEntityMap.setBusinessDomainEntity(businessDomainEntity);
                columnBusinessDomainEntityMap.setDbMetadataColumn(dbMetadataColumn);
                columnBusinessDomainEntityMaps.add(columnBusinessDomainEntityMap);
                entityName.add(businessDomainEntity.getName());
            }
            if (entityName.isEmpty()) {
                result = "This entity is already mapped to the column";
            } else {
                String names = String.join(",", entityName);
                result = "Mapping successful with entities: " + names + " whereas remaining entities are already mapped to the table";
            }
        } else {
            ColumnBusinessDomainEntityMap columnBusinessDomainEntityMap = null;
            for (BusinessDomainEntity businessDomainEntity : businessDomainEntities) {
                columnBusinessDomainEntityMap = new ColumnBusinessDomainEntityMap();
                columnBusinessDomainEntityMap.setBusinessDomainEntity(businessDomainEntity);
                columnBusinessDomainEntityMap.setDbMetadataColumn(dbMetadataColumn);
                columnBusinessDomainEntityMaps.add(columnBusinessDomainEntityMap);
                entityName.add(businessDomainEntity.getName());
            }
            String names = String.join(",", entityName);
            result = "Mapping successful with entities: " + names;
        }
        dbMetadataColumn.setColumnEntityMap(columnBusinessDomainEntityMaps);
        dbMetadataColumnRepository.save(dbMetadataColumn);
        return result;
    }


    @Override
    public void deleteColumnMapping(Long dbMetadataColumnId, Long businessDomainEntityId) {
        List<ColumnBusinessDomainEntityMap> byIds = columnBusinessDomainEntityMapRepository.findAllByDbMetadataColumnIdAndBusinessDomainEntityId(dbMetadataColumnId, businessDomainEntityId);
        if (!byIds.isEmpty()) {
            for (ColumnBusinessDomainEntityMap byId : byIds) {
                byId.setFlag(Flag.DELETED);
            }
            columnBusinessDomainEntityMapRepository.saveAll(byIds);
        }
    }


}
