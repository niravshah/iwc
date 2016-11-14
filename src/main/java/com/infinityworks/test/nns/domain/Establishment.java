package com.infinityworks.test.nns.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Establishment {

    private String LocalAuthorityBusinessID;
    private String BusinessName;
    private String RatingValue;

    @JsonCreator
    public Establishment(@JsonProperty(value = "LocalAuthorityBusinessID", required = true) String localAuthorityBusinessID,
                         @JsonProperty(value = "BusinessName", required = true) String businessName,
                         @JsonProperty(value = "RatingValue", required = true) String ratingValue) {
        LocalAuthorityBusinessID = localAuthorityBusinessID;
        BusinessName = businessName;
        RatingValue = ratingValue;
    }

    public String getLocalAuthorityBusinessID() {
        return LocalAuthorityBusinessID;
    }

    public String getBusinessName() {
        return BusinessName;
    }

    public String getRatingValue() {
        return RatingValue;
    }

    @Override
    public String toString() {
        return "Establishment{" +
                "LocalAuthorityBusinessID='" + LocalAuthorityBusinessID + '\'' +
                ", BusinessName='" + BusinessName + '\'' +
                ", RatingValue='" + RatingValue + '\'' +
                '}';
    }
}
