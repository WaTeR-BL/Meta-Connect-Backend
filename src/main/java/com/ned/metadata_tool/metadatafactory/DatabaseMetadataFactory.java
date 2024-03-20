package com.ned.metadata_tool.metadatafactory;

import com.ned.metadata_tool.metadatafactory.impl.MySQLMetadata;
import com.ned.metadata_tool.metadatafactory.impl.SQLServerMetadata;
import com.ned.metadata_tool.model.DBConfig;
import org.springframework.stereotype.Component;

@Component
public class DatabaseMetadataFactory {
    public Metadata createDatabaseMetadata(DBConfig dbConfig) {
        if ("sqlserver".equalsIgnoreCase(dbConfig.getType())) {
            return new SQLServerMetadata();
        } else if ("mysql".equalsIgnoreCase(dbConfig.getType())) {
            return new MySQLMetadata();
        }
        throw new IllegalArgumentException("Invalid database type: " + dbConfig.getType() );
    }
}
