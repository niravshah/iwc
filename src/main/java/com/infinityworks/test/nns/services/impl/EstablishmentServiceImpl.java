package com.infinityworks.test.nns.services.impl;

import com.infinityworks.test.nns.domain.Authority;
import com.infinityworks.test.nns.domain.Establishment;
import com.infinityworks.test.nns.domain.Establishments;
import com.infinityworks.test.nns.domain.StatItem;
import com.infinityworks.test.nns.domain.Stats;
import com.infinityworks.test.nns.exceptions.IncorrectEstablishmentDetailsException;
import com.infinityworks.test.nns.repositories.EstablishmentRepository;
import com.infinityworks.test.nns.services.AuthorityService;
import com.infinityworks.test.nns.services.EstablishmentService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
public class EstablishmentServiceImpl implements EstablishmentService {

    private static final Log logger = LogFactory.getLog(EstablishmentServiceImpl.class);

    @Autowired
    private EstablishmentRepository establishmentRepository;

    @Autowired
    private AuthorityService authorityServiceImpl;

    @Value("${service.establishments.page_size.max}")
    private Integer MAX_PAGE_SIZE;

    @Override
    @Cacheable("establishments")
    public Establishments getEstablishmentsByLocalAuthorityId(Integer localAuthorityId) {
        logger.info(format("Getting Establishments for Authority with id %d ", localAuthorityId));
        final Authority authority = authorityServiceImpl.getAuthority(localAuthorityId);
        final Integer establishmentCount = authority.getEstablishmentCount();
        logger.info(format("Found %d Establishments for Authority with id %d ", establishmentCount, localAuthorityId));
        int numberOfPages = ((establishmentCount - 1) / MAX_PAGE_SIZE) + 1;
        logger.info(format("Getting Establishment details. Total Page size %d", numberOfPages));
        List<Establishment> establishments = new ArrayList<>();
        for (int i = 1; i <= numberOfPages; i++) {
            logger.info(format("Getting Establishment details. Current Page %d", i));
            final Establishments establishmentsByLocalAuthorityId = establishmentRepository.getEstablishmentsByLocalAuthorityId(localAuthorityId, MAX_PAGE_SIZE, i);
            if (establishmentsByLocalAuthorityId != null) {
                establishments.addAll(establishmentsByLocalAuthorityId.getEstablishments());
            }
        }

        if (establishments.size() == establishmentCount) {
            logger.info("Returning Establishment details");
            return new Establishments(establishments);
        } else {
            throw new IncorrectEstablishmentDetailsException(format("Expected to get details for %d establishments. Acutally got details for %d establishments", establishmentCount, establishments.size()));
        }
    }

    @Override
    public Stats getEstablishmentStats(List<Establishment> establishments) {
        int size = establishments.size();
        logger.info(format("Getting Stats for Establishment list of %d establishments", size));
        final Map<String, Long> stats = establishments.stream().collect(Collectors.groupingBy(Establishment::getRatingValue, Collectors.counting()));
        List<StatItem> statItems = new ArrayList<>();
        stats.forEach((rating, total) -> statItems.add(new StatItem(rating, total, getPercentage(size, total))));
        logger.info(format("Returning Stats for Establishment list %s", statItems));
        return new Stats(statItems);
    }

    private float getPercentage(int size, Long aLong) {
        return (aLong * 100.0f) / size;
    }

}
