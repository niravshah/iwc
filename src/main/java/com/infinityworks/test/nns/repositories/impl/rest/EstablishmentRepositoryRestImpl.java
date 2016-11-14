package com.infinityworks.test.nns.repositories.impl.rest;

import com.infinityworks.test.nns.domain.Establishments;
import com.infinityworks.test.nns.exceptions.ApiException;
import com.infinityworks.test.nns.repositories.EstablishmentRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static com.sun.javafx.binding.StringFormatter.format;

@Component
public class EstablishmentRepositoryRestImpl extends BaseRestRespository implements EstablishmentRepository {

    private static final Log logger = LogFactory.getLog(EstablishmentRepositoryRestImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.establishments.search}")
    private String establishmentByAuthorityUrl;

    @Override
    public Establishments getEstablishmentsByLocalAuthorityId(Integer localAuthorityId, Integer pageSize, Integer pageNumber) {
        try {
            logger.info(format("Sending request to FSA Api to get Establishments for Authority %d, Page Size %d, Page Number %d", localAuthorityId, pageSize, pageNumber));
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getUrl())
                    .queryParam("localAuthorityId", localAuthorityId)
                    .queryParam("pageSize", pageSize)
                    .queryParam("pageNumber", pageNumber);
            final ResponseEntity<Establishments> establishmentsResponseEntity = restTemplate.getForEntity(builder.build().toUriString(), Establishments.class);
            logger.info(format("Received response from FSA Api for Establishments request for Authority %d, Page Size %d, Page Number %d", localAuthorityId, pageSize, pageNumber));
            return establishmentsResponseEntity.getBody();
        } catch (HttpMessageNotReadableException ex) {
            logger.error(format("Encountered Exception from FSA Api for Establishments request for Authority %d, Page Size %d, Page Number %d", localAuthorityId, pageNumber, pageNumber), ex);
            throw new ApiException(ex.getMessage(), ex.getCause());
        }
    }

    private String getUrl() {
        return getRootUrl() + establishmentByAuthorityUrl;
    }
}
