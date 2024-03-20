package com.ned.metadata_tool.repo;

import com.ned.metadata_tool.model.MetadataColumn;
import com.ned.metadata_tool.model.MetadataTable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface MetadataColumnRepository extends JpaRepository<MetadataColumn, Long> {
    List<MetadataColumn> findBydbMetadataTable(MetadataTable dbMetadataTable);
    List<MetadataColumn> findByColumnName(MetadataColumn columnName);
}
