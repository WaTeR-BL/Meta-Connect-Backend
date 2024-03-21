package com.ned.metadata_tool.service.impl;

import com.ned.metadata_tool.dto.UserAccountDto;
import com.ned.metadata_tool.helper.ModelDtoConverter;
import com.ned.metadata_tool.model.DBConfig;
import com.ned.metadata_tool.model.UserAccount;
import com.ned.metadata_tool.model.UserId;
import com.ned.metadata_tool.repo.UserAccountRepository;
import com.ned.metadata_tool.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    UserAccountRepository userAccountRepository;

    @Override
    public UserAccountDto register(UserAccountDto dto) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        UserAccount o = ModelDtoConverter.USER_ACCOUNT.dtoToModel(dto, UserAccount.class);
        userAccountRepository.save(o);
        return dto;
    }

    @Override
    public Boolean login(String email, String username, String password) {
        UserAccount userAccount = userAccountRepository.findByUserIdAndPassword(new UserId(username, email),password);
        if (userAccount != null) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
