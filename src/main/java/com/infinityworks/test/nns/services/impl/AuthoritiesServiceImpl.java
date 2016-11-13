package com.infinityworks.test.nns.services.impl;

import com.infinityworks.test.nns.domain.Authorities;
import com.infinityworks.test.nns.domain.Authority;
import com.infinityworks.test.nns.repositories.AuthorityRepository;
import com.infinityworks.test.nns.services.AuthoritiesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthoritiesServiceImpl implements AuthoritiesService {

    @Autowired
    public AuthorityRepository authorityRepository;

    @Cacheable("authorities")
    @Override
    public Authorities getAuthorities() {
        Optional<Authorities> authorities = authorityRepository.getAuthorities();
        return authorities.orElseThrow(() -> new RuntimeException());
    }

    @Override
    public Authority getAuthority(Integer authorityId) {
        Optional<Authority> authorityById = authorityRepository.getAuthorityById(authorityId);
        return authorityById.orElseThrow(() -> new RuntimeException());
    }

}
