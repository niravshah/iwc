package com.infinityworks.test.nns.services;

import com.infinityworks.test.nns.domain.Establishments;
import com.infinityworks.test.nns.repositories.EstablishmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EstablishmentService {

    @Autowired
    private EstablishmentsRepository establishmentsRepository;

    public Establishments getEstablishmentsByLocalAuthorityId(Integer localAuthorityId) {
        return establishmentsRepository.getEstablishmentsByLocalAuthorityId(localAuthorityId, null);
    }

}
