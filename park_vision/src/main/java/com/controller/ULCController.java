package com.controller;

import com.model.UsersLoginCreds;
import com.service.UserLoginCredsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/login")
public class ULCController {
    @Autowired
    UserLoginCredsService userLoginCredsService;
    private static final Logger logger = LoggerFactory.getLogger(ULCController.class);

    @GetMapping
    public List<UsersLoginCreds> getUsers(){
        logger.info("User record");
        return userLoginCredsService.getUsers();
    }

    @GetMapping("/{id}")
    public UsersLoginCreds getUserById(@PathVariable("id") String id){
        logger.info("getUserById is invoked with user Id: {}",id);
        return userLoginCredsService.getUserById(id);
    }
}
