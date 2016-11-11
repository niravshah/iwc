package com.infinityworks.test.nns.services.impl;

import com.infinityworks.test.nns.domain.Authorities;
import com.infinityworks.test.nns.domain.Authority;
import com.infinityworks.test.nns.repositories.AuthoritiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class AuthoritiesService {

    @Autowired
    public AuthoritiesRepository authoritiesRepository;

    @Cacheable("authorities")
    public Authorities getAuthorities() {
        return authoritiesRepository.getAuthorities();
    }

    public Authority getAuthority(Integer authorityId){
        return authoritiesRepository.getAuthorityById(authorityId);
    }

}
