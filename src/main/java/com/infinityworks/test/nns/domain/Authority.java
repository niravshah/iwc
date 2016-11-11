package com.infinityworks.test.nns.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Authority {

    @JsonProperty("LocalAuthorityId")
    private int localAuthorityId;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("EstablishmentCount")
    private String establishmentCount;

    public Authority() {
    }

    public int getLocalAuthorityId() {
        return localAuthorityId;
    }

    public void setLocalAuthorityId(int localAuthorityId) {
        this.localAuthorityId = localAuthorityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEstablishmentCount() {
        return establishmentCount;
    }

    public void setEstablishmentCount(String establishmentCount) {
        this.establishmentCount = establishmentCount;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "localAuthorityId=" + localAuthorityId +
                ", name='" + name + '\'' +
                ", establishmentCount='" + establishmentCount + '\'' +
                '}';
    }
}
