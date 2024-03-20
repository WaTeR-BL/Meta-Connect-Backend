package com.ned.metadata_tool.repo;

import com.ned.metadata_tool.enums.Flag;
import com.ned.metadata_tool.model.ColumnBusinessDomainEntityMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColumnBusinessDomainEntityMapRepository extends JpaRepository<ColumnBusinessDomainEntityMap, Long> {
    List<ColumnBusinessDomainEntityMap> findAllByDbMetadataColumnIdAndBusinessDomainEntityId(Long dbMetadataColumnId, Long BusinessDomainEntityId);
    List<ColumnBusinessDomainEntityMap> findAllByDbMetadataColumnIdAndBusinessDomainEntityIdInAndFlag(Long dbMetadataColumnId, List<Long> BusinessDomainEntityIds, Flag flag);
    List<ColumnBusinessDomainEntityMap> findAllByBusinessDomainEntityId(Long BusinessDomainEntityId);

}
