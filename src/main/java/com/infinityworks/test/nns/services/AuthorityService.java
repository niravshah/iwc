package com.infinityworks.test.nns.services;

import com.infinityworks.test.nns.domain.Authorities;
import com.infinityworks.test.nns.domain.Authority;

import org.springframework.cache.annotation.Cacheable;

public interface AuthorityService {

    @Cacheable("authorities")
    Authorities getAuthorities();

    @Cacheable("authority")
    Authority getAuthority(Integer authorityId);
}
