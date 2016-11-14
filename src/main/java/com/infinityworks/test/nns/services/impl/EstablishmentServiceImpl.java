package com.infinityworks.test.nns.services.impl;

import com.infinityworks.test.nns.domain.Authority;
import com.infinityworks.test.nns.domain.Establishment;
import com.infinityworks.test.nns.domain.Establishments;
import com.infinityworks.test.nns.domain.StatItem;
import com.infinityworks.test.nns.domain.Stats;
import com.infinityworks.test.nns.repositories.EstablishmentRepository;
import com.infinityworks.test.nns.services.AuthoritiesService;
import com.infinityworks.test.nns.services.EstablishmentService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EstablishmentServiceImpl implements EstablishmentService {

    private static final Log logger = LogFactory.getLog(EstablishmentServiceImpl.class);

    @Autowired
    private EstablishmentRepository establishmentRepository;

    @Autowired
    private AuthoritiesService authoritiesServiceImpl;

    @Value("${service.establishments.page_size.max}")
    private Integer MAX_PAGE_SIZE;

    @Override
    @Cacheable("establishments")
    public Establishments getEstablishmentsByLocalAuthorityId(Integer localAuthorityId) {
        final Authority authority = authoritiesServiceImpl.getAuthority(localAuthorityId);
        final Integer establishmentCount = authority.getEstablishmentCount();
        int numberOfPages = ((establishmentCount - 1) / MAX_PAGE_SIZE) + 1;
        List<Establishment> establishments = new ArrayList<>();
        for (int i = 1; i <= numberOfPages; i++) {
            establishments.addAll(establishmentRepository.getEstablishmentsByLocalAuthorityId(localAuthorityId, MAX_PAGE_SIZE, i).getEstablishments());
        }
        return new Establishments(establishments);
    }

    @Override
    public Stats getEstablishmentStats(List<Establishment> establishments) {
        int size = establishments.size();
        final Map<String, Long> stats = establishments.stream().collect(Collectors.groupingBy(Establishment::getRatingValue, Collectors.counting()));
        List<StatItem> statItems = new ArrayList<>();
        stats.forEach((rating, total) -> statItems.add(new StatItem(rating, total, getPercentage(size, total))));
        return new Stats(statItems);
    }

    private float getPercentage(int size, Long aLong) {
        return (aLong * 100.0f) / size;
    }

}
