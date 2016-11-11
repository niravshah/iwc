package com.infinityworks.test.nns.services;

import com.infinityworks.test.nns.domain.Establishment;
import com.infinityworks.test.nns.domain.Establishments;
import com.infinityworks.test.nns.domain.Stats;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface EstablishmentService {

    @Cacheable("establishments")
    Establishments getEstablishmentsByLocalAuthorityId(Integer localAuthorityId);

    Stats getEstablishmentStats(List<Establishment> establishments);
}
