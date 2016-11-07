package com.infinityworks.test.nns.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Establishment {

    @JsonProperty("LocalAuthorityBusinessID")
    private String LocalAuthorityBusinessID;

    @JsonProperty("BusinessName")
    private String BusinessName;

    @JsonProperty("RatingValue")
    private String RatingValue;

    public Establishment() {
    }

    public String getLocalAuthorityBusinessID() {
        return LocalAuthorityBusinessID;
    }

    public void setLocalAuthorityBusinessID(String localAuthorityBusinessID) {
        LocalAuthorityBusinessID = localAuthorityBusinessID;
    }

    public String getBusinessName() {
        return BusinessName;
    }

    public void setBusinessName(String businessName) {
        BusinessName = businessName;
    }

    public String getRatingValue() {
        return RatingValue;
    }

    public void setRatingValue(String ratingValue) {
        RatingValue = ratingValue;
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
