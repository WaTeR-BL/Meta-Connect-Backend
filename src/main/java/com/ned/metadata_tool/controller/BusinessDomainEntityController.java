package com.ned.metadata_tool.controller;


import com.ned.metadata_tool.common.BaseRestMapper;
import com.ned.metadata_tool.dto.BusinessDomainEntityDto;
import com.ned.metadata_tool.dto.DBConfigDto;
import com.ned.metadata_tool.enums.Flag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ned.metadata_tool.service.BusinessDomainEntityService;

import java.lang.reflect.InvocationTargetException;

@RestController
public class BusinessDomainEntityController implements BaseRestMapper {

    @Autowired
    private BusinessDomainEntityService businessDomainEntityService;


    @PutMapping("business-domain-entity/{id}")
    ResponseEntity<BusinessDomainEntityDto> update(@PathVariable Long id, @RequestBody BusinessDomainEntityDto Dto) {
        BusinessDomainEntityDto updatedDto;
        try {
            Dto.setId(id);
            updatedDto = businessDomainEntityService.update(Dto);
            if (updatedDto == null) {
                throw new RuntimeException();
            }
        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                 InstantiationException e) {

            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(Dto, HttpStatus.OK);
    }

    @DeleteMapping("business-domain-entity/{id}")
    ResponseEntity<String> deleteEntity(@PathVariable long id) {
        if (businessDomainEntityService.canDelete(id) == Boolean.TRUE) {
            return new ResponseEntity<>( "Entity can't be deleted since used in catalogMapping", HttpStatus.BAD_REQUEST);
        } else {
            try {
            businessDomainEntityService.delete(id);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        }
    }

    @PostMapping("business-domain-entity")
    ResponseEntity<BusinessDomainEntityDto> create(@RequestBody BusinessDomainEntityDto dto) {
        BusinessDomainEntityDto created;
        try {
            created = businessDomainEntityService.add(dto);
        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("business-domain-entities")
    ResponseEntity<Page<BusinessDomainEntityDto>> find(Pageable pageable){
        Page<BusinessDomainEntityDto> dtos;
        try {
            dtos = businessDomainEntityService.find(pageable, Flag.ACTIVE);
        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

}
