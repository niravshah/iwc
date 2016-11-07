package com.infinityworks.test.nns.services;

import com.infinityworks.test.nns.repositories.AuthoritiesRepository;
import com.infinityworks.test.nns.domain.Authorities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthoritiesService {

    @Autowired
    public AuthoritiesRepository authoritiesRepository;

    public Authorities getAuthorities() {
        return authoritiesRepository.getAuthorities();
    }

}
