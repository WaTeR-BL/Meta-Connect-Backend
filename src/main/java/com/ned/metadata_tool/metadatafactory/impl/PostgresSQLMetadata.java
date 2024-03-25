package com.ned.metadata_tool.metadatafactory.impl;


import com.ned.metadata_tool.metadatafactory.Metadata;
import com.ned.metadata_tool.model.DBConfig;
import com.ned.metadata_tool.model.MetadataColumn;
import com.ned.metadata_tool.model.MetadataTable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresSQLMetadata implements Metadata {
    @Override
    public List<MetadataTable> extractMetadata(DBConfig dbConfig) throws SQLException {
        List<MetadataTable> dbMetadataTables = null;
        Connection con = null;
        MetadataTable dbMetadataTable = null;
        try {
            Class.forName("org.postgresql.Driver");
            String url = dbConfig.getUrl() + dbConfig.getCatalogName() + "?currentSchema=" + dbConfig.getSchemaName();
            con = DriverManager.getConnection(url, dbConfig.getUsername(), dbConfig.getPassword());

            DatabaseMetaData metaData = con.getMetaData();

            ResultSet tables = metaData.getTables(dbConfig.getCatalogName(), dbConfig.getSchemaName(), null, new String[]{"TABLE"});
            dbMetadataTables = new ArrayList<>();
            while (tables.next()) {
                dbMetadataTable = new MetadataTable();
                String tableName = tables.getString("TABLE_NAME");
                String sql = "SELECT oid FROM pg_class WHERE relname = ?";
                try (PreparedStatement statement = con.prepareStatement(sql)) {
                    statement.setString(1, tableName);
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        dbMetadataTable.setObjectId(resultSet.getInt("oid"));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                dbMetadataTable.setTableName(tableName);
                dbMetadataTable.setTableType(tables.getString("TABLE_TYPE"));
                dbMetadataTable.setSchemaName(dbConfig.getSchemaName());
                dbMetadataTable.setTableCatalog(dbConfig.getCatalogName());
                dbMetadataTable.setDeletedFromSourceDB(Boolean.FALSE);

                List<MetadataColumn> columnList = extractColumnMetadata(dbConfig, metaData, tableName);
                for (MetadataColumn column : columnList) {
                    column.setDbMetadataTable(dbMetadataTable);
                    column.setObjectId(dbMetadataTable.getObjectId());
                }
                dbMetadataTable.setDbConfig(dbConfig);
                dbMetadataTable.setColumnList(columnList);
                dbMetadataTables.add(dbMetadataTable);
            }
            tables.close();


        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return dbMetadataTables;
    }

    @Override
    public List<MetadataColumn> extractColumnMetadata(DBConfig dbConfig, DatabaseMetaData metaData, String tableName) throws SQLException {
        ResultSet columns = metaData.getColumns(dbConfig.getCatalogName(), dbConfig.getSchemaName(), tableName, null);

        List<MetadataColumn> columnList = new ArrayList<>();
        while (columns.next()) {
            MetadataColumn column = new MetadataColumn();
            column.setColumnName(columns.getString("COLUMN_NAME"));
            column.setDatatype(columns.getString("TYPE_NAME"));
            column.setMaximumCharacterLength(columns.getInt("COLUMN_SIZE"));
            column.setIsNullable(columns.getString("IS_NULLABLE"));
            column.setOrdinalPosition(columns.getInt("ORDINAL_POSITION"));
            column.setDeletedFromSourceDB(Boolean.FALSE);
            columnList.add(column);
        }
        columns.close();
        return columnList;
    }


}
