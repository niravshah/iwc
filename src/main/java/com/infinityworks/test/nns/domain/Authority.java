package com.infinityworks.test.nns.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties()
public class Authority {


    private Integer localAuthorityId;
    private String name;
    private Integer establishmentCount;

    @JsonCreator
    public Authority(@JsonProperty(value = "LocalAuthorityId",required = true) Integer LocalAuthorityId,
                     @JsonProperty(value = "Name",required = true) String Name,
                     @JsonProperty(value = "EstablishmentCount",required = true) Integer EstablishmentCount) {

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
}
