package com.ned.metadata_tool.controller;

import com.ned.metadata_tool.common.BaseRestMapper;
import com.ned.metadata_tool.dto.DBConfigDto;
import com.ned.metadata_tool.enums.Flag;
import com.ned.metadata_tool.service.DBConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.lang.reflect.InvocationTargetException;

@RestController
public class DBConfigController implements BaseRestMapper {

    @Autowired
    DBConfigService dbConfigService;

    @GetMapping("db-configs")
    public ResponseEntity<Page<DBConfigDto>> findDBConfig(Pageable pageable) {
        Page<DBConfigDto> dbConfigDto;
        try {
            dbConfigDto = dbConfigService.find(pageable, Flag.ACTIVE);
        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(dbConfigDto, HttpStatus.OK);
    }


    @PostMapping("db-config")
    ResponseEntity<DBConfigDto> create(@RequestBody DBConfigDto dto) {
        DBConfigDto created ;
        try {
            created = dbConfigService.add(dto);
        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @DeleteMapping("db-config/{id}")
    ResponseEntity<String> delete(@PathVariable Long id){
        try{
        dbConfigService.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>( "Deleted successfully", HttpStatus.OK);
    }

    @PutMapping("db-config/{id}")
    ResponseEntity<DBConfigDto> update(@PathVariable Long id, @RequestBody DBConfigDto Dto) {
        DBConfigDto updatedDto;
        try {
            Dto.setId(id);
            updatedDto = dbConfigService.update(Dto);
            if (updatedDto == null) {
                throw new RuntimeException();
            }
        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(Dto, HttpStatus.OK);
    }

}
