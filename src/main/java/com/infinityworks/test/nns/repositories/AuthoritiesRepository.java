package com.infinityworks.test.nns.repositories;

import com.infinityworks.test.nns.domain.Authorities;
import com.infinityworks.test.nns.domain.Authority;

public interface AuthoritiesRepository {

    Authorities getAuthorities();
    Authority getAuthorityById(Integer authorityId);
}
