package com.infinityworks.test.nns.controllers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TestAuthority {

    private Integer localAuthorityId;
    private String name;
    private Integer establishmentCount;

    @JsonCreator
    public TestAuthority(@JsonProperty(value = "localAuthorityId",required = true) Integer LocalAuthorityId,
                     @JsonProperty(value = "name",required = true) String Name,
                     @JsonProperty(value = "establishmentCount",required = true) Integer EstablishmentCount) {

        this.establishmentCount = EstablishmentCount;
        this.name = Name;
        this.localAuthorityId = LocalAuthorityId;
    }

    public Integer getLocalAuthorityId() {
        return localAuthorityId;
    }

    public String getName() {
        return name;
    }

    public Integer getEstablishmentCount() {
        return establishmentCount;
    }

    @Override
    public String toString() {
        return "TestAuthority{" +
                "localAuthorityId=" + localAuthorityId +
                ", name='" + name + '\'' +
                ", establishmentCount=" + establishmentCount +
                '}';
    }
}
