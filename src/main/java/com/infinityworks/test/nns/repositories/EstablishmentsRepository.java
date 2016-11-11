package com.infinityworks.test.nns.repositories;

import com.infinityworks.test.nns.domain.Establishments;

public interface EstablishmentsRepository {

    Establishments getEstablishmentsByLocalAuthorityId(Integer localAuthorityId, Integer establishmentCount);
}
