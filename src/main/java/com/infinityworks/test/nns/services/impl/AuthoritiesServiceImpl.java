package com.infinityworks.test.nns.services.impl;

import com.infinityworks.test.nns.domain.Authorities;
import com.infinityworks.test.nns.domain.Authority;
import com.infinityworks.test.nns.repositories.AuthorityRepository;
import com.infinityworks.test.nns.services.AuthoritiesService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class AuthoritiesServiceImpl implements AuthoritiesService {

    private static final Log logger = LogFactory.getLog(AuthoritiesService.class);

    @Autowired
    public AuthorityRepository authorityRepository;

    @Cacheable("authorities")
    @Override
    public Authorities getAuthorities() {
        Authorities authorities = authorityRepository.getAuthorities();
        return authorities;
    }

    @Override
    public Authority getAuthority(Integer authorityId) {
        Authority authorityById = authorityRepository.getAuthorityById(authorityId);
        return authorityById;
    }

}
