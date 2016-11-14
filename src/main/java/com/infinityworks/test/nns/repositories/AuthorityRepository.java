package com.infinityworks.test.nns.repositories;

import com.infinityworks.test.nns.domain.Authorities;
import com.infinityworks.test.nns.domain.Authority;

import java.util.Optional;

public interface AuthorityRepository {

    Authorities getAuthorities();

    Authority getAuthorityById(Integer authorityId);
}
