package com.ned.metadata_tool.controller;

import com.ned.metadata_tool.common.BaseRestMapper;
import com.ned.metadata_tool.dto.UserAccountDto;
import com.ned.metadata_tool.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

@RestController
public class UserAccountController implements BaseRestMapper {
    @Autowired
    UserAccountService userAccountService;

    @PostMapping("registration")
    ResponseEntity<UserAccountDto> register(@RequestBody UserAccountDto dto) {
        UserAccountDto created ;
        try {
            created = userAccountService.register(dto);
        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("login")
    ResponseEntity<Boolean> authenticate(@RequestParam String username, @RequestParam String email, @RequestParam String password){
        Boolean status;
        try {
            status = userAccountService.login(email,username,password);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

}
