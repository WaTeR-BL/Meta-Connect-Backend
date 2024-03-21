package com.ned.metadata_tool.service;

import com.ned.metadata_tool.dto.UserAccountDto;
import com.ned.metadata_tool.model.UserAccount;

import java.lang.reflect.InvocationTargetException;

public interface UserAccountService {
    UserAccountDto register(UserAccountDto dto) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException;
    Boolean login(String email ,String username, String password);

}
