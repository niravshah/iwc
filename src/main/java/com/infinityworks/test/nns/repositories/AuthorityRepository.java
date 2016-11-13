package com.infinityworks.test.nns.repositories;

import com.infinityworks.test.nns.domain.Authorities;
import com.infinityworks.test.nns.domain.Authority;

import java.util.Optional;

public interface AuthorityRepository {

    Optional<Authorities> getAuthorities();
    Optional<Authority> getAuthorityById(Integer authorityId);
}
