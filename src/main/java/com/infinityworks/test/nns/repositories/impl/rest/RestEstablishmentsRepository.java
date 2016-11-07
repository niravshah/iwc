package com.infinityworks.test.nns.repositories.impl.rest;

import com.infinityworks.test.nns.domain.Authorities;
import com.infinityworks.test.nns.domain.Establishments;
import com.infinityworks.test.nns.repositories.EstablishmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class RestEstablishmentsRepository implements EstablishmentsRepository {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Establishments getEstablishmentsByLocalAuthorityId(Integer localAuthorityId) {

        final ResponseEntity<Establishments> establishmentsResponseEntity = restTemplate.getForEntity("http://api.ratings.food.gov.uk/Establishments?localAuthorityId='" + localAuthorityId + "'", Establishments.class);
        return establishmentsResponseEntity.getBody();
    }
}
