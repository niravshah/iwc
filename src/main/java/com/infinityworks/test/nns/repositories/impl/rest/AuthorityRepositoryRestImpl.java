package com.infinityworks.test.nns.repositories.impl.rest;

import com.infinityworks.test.nns.domain.Authorities;
import com.infinityworks.test.nns.domain.Authority;
import com.infinityworks.test.nns.repositories.AuthorityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class AuthorityRepositoryRestImpl extends BaseRestRespository implements AuthorityRepository {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.authorities.basic}")
    private String authoritiesUrl;

    @Value("${api.authority}")
    private String authorityUrl;

    @Override
    public Authorities getAuthorities() {

        try {
            ResponseEntity<Authorities> authorities = restTemplate.getForEntity(getAuthoritiesUrl(), Authorities.class);
            return authorities.getBody();
        } catch (HttpMessageNotReadableException ex) {
            throw new ApiException(ex.getMessage(), ex.getCause());
        }

    }

    @Override
    public Authority getAuthorityById(Integer authorityId) {
        try {
            final ResponseEntity<Authority> authority = restTemplate.getForEntity(getAuthorityUrl(), Authority.class, authorityId);
            return authority.getBody();
        } catch (HttpMessageNotReadableException ex) {
            throw new ApiException(ex.getMessage(), ex.getCause());
        }
    }

    private String getAuthorityUrl() {
        return getRootUrl() + authorityUrl;
    }

    private String getAuthoritiesUrl() {
        return getRootUrl() + authoritiesUrl;
    }
}
