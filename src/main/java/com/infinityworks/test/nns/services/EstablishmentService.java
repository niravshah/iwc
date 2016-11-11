package com.infinityworks.test.nns.services;

import com.infinityworks.test.nns.domain.Establishments;
import com.infinityworks.test.nns.repositories.EstablishmentsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EstablishmentService {

    @Autowired
    private EstablishmentsRepository establishmentsRepository;

    @Value("${service.establishments.page_size.max}")
    private static Integer MAX_PAGE_SIZE;

    public Establishments getEstablishmentsByLocalAuthorityId(Integer localAuthorityId) {
        return establishmentsRepository.getEstablishmentsByLocalAuthorityId(localAuthorityId, MAX_PAGE_SIZE,0);
    }

}
