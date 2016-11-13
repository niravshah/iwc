package com.infinityworks.test.nns.services.impl;

import com.infinityworks.test.nns.domain.Authority;
import com.infinityworks.test.nns.domain.Establishment;
import com.infinityworks.test.nns.domain.Establishments;
import com.infinityworks.test.nns.domain.Stats;
import com.infinityworks.test.nns.repositories.EstablishmentRepository;

import com.infinityworks.test.nns.services.AuthoritiesService;
import com.infinityworks.test.nns.services.EstablishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EstablishmentServiceImpl implements EstablishmentService {

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
        if (establishmentCount > MAX_PAGE_SIZE) {
            int numberOfPages = ((establishmentCount - 1) / MAX_PAGE_SIZE) + 1;
            List<Establishment> establishments = new ArrayList<>();
            for (int i = 1; i <= numberOfPages; i++) {
                establishments.addAll(establishmentRepository.getEstablishmentsByLocalAuthorityId(localAuthorityId, MAX_PAGE_SIZE, i).getEstablishments());
            }
            Establishments establishments1 = new Establishments();
            establishments1.setEstablishments(establishments);
            return establishments1;
        } else {
            return establishmentRepository.getEstablishmentsByLocalAuthorityId(localAuthorityId, establishmentCount, 1);
        }
    }

    @Override
    public Stats getEstablishmentStats(List<Establishment> establishments) {
        final Map<String, Long> stats = establishments.stream().collect(Collectors.groupingBy(Establishment::getRatingValue, Collectors.counting()));
        return new Stats(stats);
    }

}
