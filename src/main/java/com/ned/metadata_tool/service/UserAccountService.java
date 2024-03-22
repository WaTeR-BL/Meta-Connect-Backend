package com.ned.metadata_tool.service;

import com.ned.metadata_tool.dto.UserAccountDto;
import com.ned.metadata_tool.model.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface UserAccountService {
    UserAccountDto register(UserAccountDto dto) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException;
    Boolean login(String email ,String username, String password);

    Page<UserAccountDto> find(Pageable pageable) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException;

}
