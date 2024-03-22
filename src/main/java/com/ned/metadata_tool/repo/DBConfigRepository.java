package com.ned.metadata_tool.repo;

import com.ned.metadata_tool.enums.Flag;
import com.ned.metadata_tool.model.DBConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DBConfigRepository extends JpaRepository<DBConfig, Long> {
    Page<DBConfig> findAllByFlag(Pageable pageable, Flag flag);
}
