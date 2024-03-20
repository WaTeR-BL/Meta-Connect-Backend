package com.ned.metadata_tool.controller;

import com.ned.metadata_tool.common.BaseRestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ned.metadata_tool.service.CatalogMappingService;

import java.util.List;

@RestController
public class CatalogMappingController implements BaseRestMapper {

    @Autowired
    private CatalogMappingService catalogMappingService;

    @PostMapping("{columnId}/catalog-mapping-column")
    public ResponseEntity<String> mapEntitiesColumn(@PathVariable Long columnId, @RequestBody List<Long> businessDomainEntityIds) {
        try {
            String s = catalogMappingService.mapEntitiesColumn(columnId, businessDomainEntityIds);
            return new ResponseEntity<>( s, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Mapping unsuccessful" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("delete/{columnId}/catalog-mapping-column")
    ResponseEntity<String> DeleteColumnEntityMap(@PathVariable Long columnId, @RequestBody Long businessDomainEntityId) {
        try {
            catalogMappingService.deleteColumnMapping(columnId, businessDomainEntityId);
            return new ResponseEntity<>( "Deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
