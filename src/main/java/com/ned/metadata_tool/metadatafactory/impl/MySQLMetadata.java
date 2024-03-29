package com.ned.metadata_tool.metadatafactory.impl;


import com.ned.metadata_tool.metadatafactory.Metadata;
import com.ned.metadata_tool.model.DBConfig;
import com.ned.metadata_tool.model.MetadataColumn;
import com.ned.metadata_tool.model.MetadataTable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MySQLMetadata implements Metadata {

    @Override
    public List<MetadataTable> extractMetadata(DBConfig dbConfig) {
        MetadataTable dbMetadataTable = null;
        Connection con = null;
        List<MetadataTable> dbMetadataTables = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(dbConfig.getUrl(), dbConfig.getUsername(), dbConfig.getPassword());
            DatabaseMetaData metaData = con.getMetaData();

            ResultSet tables = metaData.getTables(dbConfig.getCatalogName(), dbConfig.getSchemaName(), null, new String[]{"TABLE"});
            dbMetadataTables = new ArrayList<>();
            while (tables.next()) {
                dbMetadataTable = new MetadataTable();
                String tableName = tables.getString("TABLE_NAME");
                String sql = "SELECT TABLE_ID FROM INFORMATION_SCHEMA.INNODB_TABLES WHERE NAME = ?";
                try (PreparedStatement statement = con.prepareStatement(sql)) {
                    statement.setString(1, dbConfig.getCatalogName() + "/" + tableName);
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        dbMetadataTable.setObjectId(resultSet.getInt("TABLE_ID"));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                dbMetadataTable.setTableName(tableName);
                dbMetadataTable.setTableType(tables.getString("TABLE_TYPE"));
                dbMetadataTable.setSchemaName(tables.getString("TABLE_CAT"));
                dbMetadataTable.setTableCatalog(tables.getString("TABLE_CAT"));
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