package com.infinityworks.test.nns.controllers;

import com.infinityworks.test.nns.domain.Establishments;
import com.infinityworks.test.nns.domain.Stats;
import com.infinityworks.test.nns.services.EstablishmentService;
import com.infinityworks.test.nns.services.impl.EstablishmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class EstablishmentsController {

    @Autowired
    private EstablishmentService establishmentService;

    @RequestMapping(value = "/api/authority/{id}/stats")
    public ResponseEntity<Stats> getAuthorities(@PathVariable Integer id) {
        final Establishments establishmentsByLocalAuthorityId = establishmentService.getEstablishmentsByLocalAuthorityId(id);
        final Stats establishmentStats = establishmentService.getEstablishmentStats(establishmentsByLocalAuthorityId.getEstablishments());
        return ok(establishmentStats);
    }

}
