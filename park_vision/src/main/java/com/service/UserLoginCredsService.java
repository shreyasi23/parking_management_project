package com.service;

import com.model.UsersLoginCreds;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserLoginCredsService {
    List<UsersLoginCreds> getUsers();
    UsersLoginCreds getUserById(String id);
}
