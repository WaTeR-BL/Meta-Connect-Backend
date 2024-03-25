package com.ned.metadata_tool.metadatafactory;

import com.ned.metadata_tool.metadatafactory.impl.MySQLMetadata;
import com.ned.metadata_tool.metadatafactory.impl.PostgresSQLMetadata;
import com.ned.metadata_tool.model.DBConfig;
import org.springframework.stereotype.Component;

@Component
public class DatabaseMetadataFactory {
    public Metadata createDatabaseMetadata(DBConfig dbConfig) {
        if ("postgres".equalsIgnoreCase(dbConfig.getType())) {
            return new PostgresSQLMetadata();
        } else if ("mysql".equalsIgnoreCase(dbConfig.getType())) {
            return new MySQLMetadata();
        }
        throw new IllegalArgumentException("Invalid database type: " + dbConfig.getType() );
    }
}
