package com.infinityworks.test.nns.repositories.impl.rest;

import com.infinityworks.test.nns.domain.Establishments;
import com.infinityworks.test.nns.repositories.EstablishmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class RestEstablishmentsRepository extends BaseRestRespository implements EstablishmentsRepository {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.establishments.search}")
    private String establishmentByAuthorityUrl;

    @Override
    public Establishments getEstablishmentsByLocalAuthorityId(Integer localAuthorityId, Integer pageSize, Integer pageNumber) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getUrl())
                .queryParam("localAuthorityId", localAuthorityId)
                .queryParam("pageSize", pageSize)
                .queryParam("pageNumber", pageNumber);
        final ResponseEntity<Establishments> establishmentsResponseEntity = restTemplate.getForEntity(builder.build().toUriString(), Establishments.class);
        return establishmentsResponseEntity.getBody();
    }

    private String getUrl() {
        return getRootUrl() + establishmentByAuthorityUrl;
    }
}
