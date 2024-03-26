package com.ned.metadata_tool.controller;

import com.ned.metadata_tool.common.BaseRestMapper;
import com.ned.metadata_tool.dto.UserAccountDto;
import com.ned.metadata_tool.model.AuthRequest;
import com.ned.metadata_tool.service.UserAccountService;
import com.ned.metadata_tool.util.JwtUtil;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
public class UserAccountController implements BaseRestMapper {
    @Autowired
    UserAccountService userAccountService;

    @Autowired
    AuthenticationManager authenticate;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("registration")
    ResponseEntity<UserAccountDto> register(@RequestBody UserAccountDto dto) {
        UserAccountDto created;
        try {
            created = userAccountService.register(dto);
        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticate.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("invalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }


    @GetMapping("users")
    ResponseEntity<Page<UserAccountDto>> findAll(Pageable pageable) {
        Page<UserAccountDto> users = null;
        try {
            users = userAccountService.find(pageable);
        } catch (RuntimeException | ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


}
