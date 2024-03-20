package com.ned.metadata_tool.metadatafactory;

import com.ned.metadata_tool.model.DBConfig;
import com.ned.metadata_tool.model.MetadataColumn;
import com.ned.metadata_tool.model.MetadataTable;


import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

public interface Metadata {
    List<MetadataTable> extractMetadata(DBConfig dbConfig) throws SQLException;
    List<MetadataColumn> extractColumnMetadata (DBConfig dbConfig, DatabaseMetaData metaData, String tableName) throws SQLException;

}
