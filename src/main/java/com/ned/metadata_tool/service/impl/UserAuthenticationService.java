package com.ned.metadata_tool.service.impl;

import com.ned.metadata_tool.model.UserAccount;
import com.ned.metadata_tool.repo.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserAuthenticationService implements UserDetailsService {

    @Autowired
    UserAccountRepository userAccountRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserAccount user = userAccountRepository.findByUserName(s);
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), new ArrayList<>());
    }
}
