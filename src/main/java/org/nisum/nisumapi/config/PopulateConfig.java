package org.nisum.nisumapi.config;

import org.nisum.nisumapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PopulateConfig {

    final UserService userService;

    @Autowired
    public PopulateConfig(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void populateUser() {
        //productService.init();
    }
 }
