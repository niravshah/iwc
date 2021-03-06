package com.infinityworks.test.nns.repositories;

import com.infinityworks.test.nns.domain.Establishments;

public interface EstablishmentRepository {

    Establishments getEstablishmentsByLocalAuthorityId(Integer localAuthorityId, Integer pageSize, Integer pageNumber);
}
