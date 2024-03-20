package com.ned.metadata_tool.repo;


import com.ned.metadata_tool.enums.Flag;
import com.ned.metadata_tool.model.DBConfig;
import com.ned.metadata_tool.model.MetadataTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface MetadataTableRepository extends JpaRepository<MetadataTable, Long> {

    Page<MetadataTable> findAllByFlag(Pageable pageable, Flag flag);
    List<MetadataTable> findByDbConfig(DBConfig dbConfig);

}
