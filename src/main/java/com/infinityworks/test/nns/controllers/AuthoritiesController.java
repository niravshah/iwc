package com.infinityworks.test.nns.controllers;

import com.infinityworks.test.nns.domain.Authorities;
import com.infinityworks.test.nns.services.AuthoritiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthoritiesController {

    @Autowired
    private AuthoritiesService authoritiesService;

    @RequestMapping(value = "/api/authorities")
    public Authorities getAuthorities() {
        return authoritiesService.getAuthorities();
    }

}
