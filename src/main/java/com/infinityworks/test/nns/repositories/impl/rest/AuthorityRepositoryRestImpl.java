package com.infinityworks.test.nns.repositories.impl.rest;

import com.infinityworks.test.nns.domain.Authorities;
import com.infinityworks.test.nns.domain.Authority;
import com.infinityworks.test.nns.repositories.AuthorityRepository;
import com.sun.javafx.binding.StringFormatter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static com.sun.javafx.binding.StringFormatter.*;

@Component
public class AuthorityRepositoryRestImpl extends BaseRestRespository implements AuthorityRepository {

    private static final Log logger = LogFactory.getLog(AuthorityRepositoryRestImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.authorities.basic}")
    private String authoritiesUrl;

    @Value("${api.authority}")
    private String authorityUrl;

    @Override
    public Authorities getAuthorities() {
        try {
            logger.info("Sending request to FSA Api to get basic information for Authorities");
            ResponseEntity<Authorities> authorities = restTemplate.getForEntity(getAuthoritiesUrl(), Authorities.class);
            logger.info(format("Received response from FSA Api for basic information for Authorities. Authorities Found %d", authorities.getBody().getAuthorities().size()));
            return authorities.getBody();
        } catch (HttpMessageNotReadableException ex) {
            logger.error("Encountered Exception from FSA Api for basic information for Authorities", ex);
            throw new ApiException(ex.getMessage(), ex.getCause());
        }

    }

    @Override
    public Authority getAuthorityById(Integer authorityId) {
        try {
            logger.info(format("Sending request to FSA Api to get detailed information for Authority %d", authorityId));
            final ResponseEntity<Authority> authority = restTemplate.getForEntity(getAuthorityUrl(), Authority.class, authorityId);
            logger.info(format("Received response from FSA Api for get detailed information for Authority %s", authority.getBody()));
            return authority.getBody();
        } catch (HttpMessageNotReadableException ex) {
            logger.error(format("Encountered Exception from FSA Api for detailed information request for Authority %d", authorityId), ex);
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
