package com.infinityworks.test.nns.repositories.impl.rest;

import com.infinityworks.test.nns.domain.Authorities;
import com.infinityworks.test.nns.repositories.AuthoritiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestAuthoritiesRepository implements AuthoritiesRepository {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Authorities getAuthorities() {
        final ResponseEntity<Authorities> authorities = restTemplate.getForEntity("http://api.ratings.food.gov.uk/Authorities/basic", Authorities.class);
        return authorities.getBody();
    }
}
