package com.ned.metadata_tool.service.impl;

import com.ned.metadata_tool.metadatafactory.DatabaseMetadataFactory;
import com.ned.metadata_tool.metadatafactory.Metadata;
import com.ned.metadata_tool.model.DBConfig;
import com.ned.metadata_tool.model.MetadataColumn;
import com.ned.metadata_tool.model.MetadataTable;
import com.ned.metadata_tool.repo.DBConfigRepository;
import com.ned.metadata_tool.repo.MetadataTableRepository;
import com.ned.metadata_tool.service.MetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.Boolean.TRUE;


@Service
public class MetadataServiceImpl implements MetadataService {

    @Autowired
    DBConfigRepository dbConfigRepository;
    @Autowired
    MetadataTableRepository dbMetadataTableRepository;
    @Autowired
    DatabaseMetadataFactory databaseMetadataFactory;

    @Override
    public void extractAndSaveMetadata(Long dbConfigId) {
        DBConfig dbConfig = dbConfigRepository.findById(dbConfigId)
                .orElseThrow(() -> new RuntimeException("DBConfig not found"));

        Metadata databaseMetadata = databaseMetadataFactory.createDatabaseMetadata(dbConfig);

        try {

            Connection con = DriverManager.getConnection(dbConfig.getUrl(), dbConfig.getUsername(), dbConfig.getPassword());
            DatabaseMetaData metaData = con.getMetaData();
            List<MetadataTable> dbMetadataTables = new ArrayList<>();
            List<MetadataColumn> dbMetadataColumns = null;

            //below is snapshot of actual db metadata
            List<MetadataTable> metadataSnapshot = databaseMetadata.extractMetadata(dbConfig);

            //is the case for refresh metadata?
            if (dbConfig.getLoadStatus() == TRUE) {
                //if loaded then this block
                List<MetadataTable> savedMetaData = dbMetadataTableRepository.findByDbConfig(dbConfig);

                Map<Integer, MetadataTable> tableMapSnapshot = new HashMap<>();
                List<MetadataColumn> columnList = new ArrayList<>();

                for (MetadataTable dbMetadataTable : metadataSnapshot) {
                    tableMapSnapshot.put(dbMetadataTable.getObjectId(), dbMetadataTable);
                    columnList.addAll(dbMetadataTable.getColumnList());
                }

                Map<String, MetadataColumn> columnMapSnapshot = new HashMap<>();
                for (MetadataColumn dbMetadataColumn : columnList) {
                    String uniqueId = dbMetadataColumn.getObjectId() + ":" + dbMetadataColumn.getOrdinalPosition();
                    columnMapSnapshot.put(uniqueId, dbMetadataColumn);
                }
                List<MetadataColumn> savedColumns = new ArrayList<>();
                for (MetadataTable dbMetadataTable : savedMetaData) {
                    savedColumns.addAll(dbMetadataTable.getColumnList());
                }

                Map<Integer, MetadataTable> savedTablesMap = savedMetaData.stream().collect(Collectors.toMap(MetadataTable::getObjectId, Function.identity()));
                Map<String, MetadataColumn> savedColumnsMap = savedColumns.stream().collect(Collectors.toMap(column -> column.getObjectId() + ":" + column.getOrdinalPosition(), Function.identity()));

                for (MetadataTable savedDBMetadataTable : savedMetaData) {
                    dbMetadataColumns = new ArrayList<>();
                    if (tableMapSnapshot.containsKey(savedDBMetadataTable.getObjectId())) {
                        savedDBMetadataTable.setTableName(tableMapSnapshot.get(savedDBMetadataTable.getObjectId()).getTableName());
                        dbMetadataTables.add(savedDBMetadataTable);
                        for (MetadataColumn savedDBMetadataColumn : savedDBMetadataTable.getColumnList()) {
                            String Id = savedDBMetadataColumn.getObjectId() + ":" + savedDBMetadataColumn.getOrdinalPosition();
                            MetadataColumn dbMetadataColumnFromMap = null;
                            if (columnMapSnapshot.containsKey(Id)) {
                                dbMetadataColumnFromMap = columnMapSnapshot.get(Id);
                                savedDBMetadataColumn.setDatatype(dbMetadataColumnFromMap.getDatatype());
                                savedDBMetadataColumn.setColumnName(dbMetadataColumnFromMap.getColumnName());
                                savedDBMetadataColumn.setIsNullable(dbMetadataColumnFromMap.getIsNullable());
                                savedDBMetadataColumn.setMaximumCharacterLength(dbMetadataColumnFromMap.getMaximumCharacterLength());
                            } else {
                                //delete if not available
                                savedDBMetadataColumn.setDeletedFromSourceDB(TRUE);
                                dbMetadataColumns.add(savedDBMetadataColumn);
                            }
                        }
                    } else {
                        //delete if not available
                        savedDBMetadataTable.setDeletedFromSourceDB(TRUE);
                        dbMetadataTables.add(savedDBMetadataTable);
                    }
                    for (MetadataColumn dbMetadataColumn : tableMapSnapshot.get(savedDBMetadataTable.getObjectId()).getColumnList()) {
                        String columnId = savedDBMetadataTable.getObjectId() + ":" + dbMetadataColumn.getOrdinalPosition();
                        if (!(savedColumnsMap.containsKey(columnId))) {
                            dbMetadataColumn.setDbMetadataTable(savedTablesMap.get(dbMetadataColumn.getObjectId()));
                            dbMetadataColumns.add(dbMetadataColumn);
                            savedColumnsMap.put(columnId, dbMetadataColumn);
                        }
                    }
                    savedDBMetadataTable.setColumnList(dbMetadataColumns);
                }

                for (MetadataTable dbMetadataTableSnapshot : metadataSnapshot) {
                    if (!(savedTablesMap.containsKey(dbMetadataTableSnapshot.getObjectId()))) {
                        dbMetadataTableSnapshot.setDbConfig(dbConfig);
                        dbMetadataTables.add(dbMetadataTableSnapshot);
                        savedTablesMap.put(dbMetadataTableSnapshot.getObjectId(), dbMetadataTableSnapshot);
                    }
                }

                dbMetadataTableRepository.saveAll(dbMetadataTables);
            } else {
                dbMetadataTableRepository.saveAll(metadataSnapshot);
                dbConfig.setLoadStatus(Boolean.TRUE);
                dbConfigRepository.save(dbConfig);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error extracting and saving metadata: " + e.getMessage(), e);
        }
    }
}
