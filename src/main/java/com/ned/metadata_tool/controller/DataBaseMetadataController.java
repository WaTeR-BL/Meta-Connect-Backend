package com.ned.metadata_tool.controller;

import com.ned.metadata_tool.common.BaseRestMapper;
import com.ned.metadata_tool.dto.MetadataTableDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ned.metadata_tool.service.ListMetadataService;
import com.ned.metadata_tool.service.MetadataService;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
public class DataBaseMetadataController implements BaseRestMapper {

    @Autowired
    MetadataService metadataService;

    @Autowired
    ListMetadataService listMetadataService;


    @PostMapping("extract/{dbConfigId}")
    public ResponseEntity<String> extractAndSaveMetadata(@PathVariable Long dbConfigId) {
        try {
            metadataService.extractAndSaveMetadata(dbConfigId);
            return new ResponseEntity<>("Metadata extracted and saved successfully.", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Error in extracting Metadata; " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("db-metadata-values/{configId}")
    public ResponseEntity<List<MetadataTableDto>> findOne(@PathVariable Long configId) {

        List<MetadataTableDto> dbMetadataTableDto;
        try {
            dbMetadataTableDto = listMetadataService.findMetadataValuesByConfig(configId);
            if (dbMetadataTableDto == null) {
                throw new RuntimeException();
            }
        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                 InstantiationException e) {

            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(dbMetadataTableDto, HttpStatus.OK);
    }

}


