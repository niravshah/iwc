package com.infinityworks.test.nns.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class StatItem {

    private String rating;
    private Long totalEstablishments;
    @JsonSerialize(using = CustomFloatDeserializer.class)
    private Float percentage;

    private StatItem() {
    }

    public StatItem(String rating, Long totalEstablishments, Float percentage) {
        this.rating = rating;
        this.totalEstablishments = totalEstablishments;
        this.percentage = percentage;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @JsonProperty("y")
    public Long getTotalEstablishments() {
        return totalEstablishments;
    }

    public void setTotalEstablishments(Long totalEstablishments) {
        this.totalEstablishments = totalEstablishments;
    }

    public Float getPercentage() {
        return percentage;
    }

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "StatItem{" +
                "rating='" + rating + '\'' +
                ", totalEstablishments=" + totalEstablishments +
                ", percentage=" + percentage +
                '}';
    }
}
