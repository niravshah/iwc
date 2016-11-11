package com.infinityworks.test.nns.repositories.impl.rest;

import com.infinityworks.test.nns.domain.Authorities;
import com.infinityworks.test.nns.domain.Authority;
import com.infinityworks.test.nns.repositories.AuthoritiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestAuthoritiesRepository extends BaseRestRespository implements AuthoritiesRepository {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.authorities.basic}")
    private String authoritiesUrl;

    @Value("${api.authority}")
    private String authorityUrl;

    @Override
    public Authorities getAuthorities() {
        final ResponseEntity<Authorities> authoritiesResponseEntity = restTemplate.getForEntity(getAuthoritiesUrl(), Authorities.class);
        final Authorities authorities = authoritiesResponseEntity.getBody();
        return authorities;
    }

    @Override
    public Authority getAuthorityById(Integer authorityId) {
        final ResponseEntity<Authority> authorityResponseEntity = restTemplate.getForEntity(getAuthorityUrl(authorityId), Authority.class);
        return authorityResponseEntity.getBody();
    }

    private String getAuthorityUrl(Integer authorityId) {
        return getRootUrl() + authorityUrl + authorityId;
    }

    private String getAuthoritiesUrl() {
        return getRootUrl() + authoritiesUrl;
    }
}
