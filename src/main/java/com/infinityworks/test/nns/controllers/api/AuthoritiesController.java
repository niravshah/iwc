package com.infinityworks.test.nns.controllers.api;

import com.infinityworks.test.nns.domain.Authorities;
import com.infinityworks.test.nns.services.AuthoritiesService;
import com.infinityworks.test.nns.services.impl.AuthoritiesServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class AuthoritiesController {

    @Autowired
    private AuthoritiesService authoritiesServiceImpl;

    @RequestMapping(value = "/api/authorities")
    public ResponseEntity<Authorities> getAuthorities() {
        return ok(authoritiesServiceImpl.getAuthorities());
    }

}
