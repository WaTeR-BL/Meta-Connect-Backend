package com.ned.metadata_tool.service;

import java.util.List;

public interface CatalogMappingService {

    String mapEntitiesColumn(Long dbMetadataColumnId, List<Long> businessDomainEntityIds);

    void deleteColumnMapping(Long dbMetadataColumnId, Long businessDomainEntityId);


}

