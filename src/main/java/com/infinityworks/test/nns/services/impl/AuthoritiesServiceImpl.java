package com.infinityworks.test.nns.services.impl;

import com.infinityworks.test.nns.domain.Authorities;
import com.infinityworks.test.nns.domain.Authority;
import com.infinityworks.test.nns.exceptions.AuthorityNotFoundException;
import com.infinityworks.test.nns.exceptions.NoAuthoritiesFoundException;
import com.infinityworks.test.nns.repositories.AuthorityRepository;
import com.infinityworks.test.nns.services.AuthoritiesService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
public class AuthoritiesServiceImpl implements AuthoritiesService {

    private static final Log logger = LogFactory.getLog(AuthoritiesService.class);

    @Autowired
    public AuthorityRepository authorityRepository;

    @Cacheable("authorities")
    @Override
    public Authorities getAuthorities() {
        logger.info("Calling repository to get basic information for all authorities");
        Authorities authorities = authorityRepository.getAuthorities();
        if (authorities.getAuthorities() != null && authorities.getAuthorities().size() > 0) {
            logger.info(format("Returning basic information for %d authorities", authorities.getAuthorities().size()));
            return authorities;
        } else {
            logger.error("Returning Exception as basic information could not be found for any authorities");
            throw new NoAuthoritiesFoundException("No Authorities were returned from the FSA API");
        }
    }

    @Override
    public Authority getAuthority(Integer authorityId) {
        logger.info(format("Calling repository to get detailed information for Authority with id %d", authorityId));
        Authority authorityById = authorityRepository.getAuthorityById(authorityId);
        if (authorityById != null) {
            logger.info(format("Returning detailed information for Authority with id %d", authorityId));
            return authorityById;
        } else {
            logger.error(format("Detailed information for Authority with id %d could not be found. Returning exception", authorityId));
            throw new AuthorityNotFoundException(format("Authority with Id %d was not found", authorityById));
        }
    }

}
