package com.infinityworks.test.nns.controllers;

import com.infinityworks.test.nns.domain.Authorities;
import com.infinityworks.test.nns.services.AuthorityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class AuthoritiesController {

    @Autowired
    private AuthorityService authorityServiceImpl;

    @RequestMapping(value = "/api/authorities",method = RequestMethod.GET)
    public ResponseEntity<Authorities> getAuthorities() {
        final Authorities authorities = authorityServiceImpl.getAuthorities();
        return ok(authorities);
    }

}
