package com.infinityworks.test.nns.domain;

import java.util.List;

public class Stats {

    private List<StatItem> statItems;

    private Stats() {
    }

    public Stats(List<StatItem> statItems) {
        this.statItems = statItems;
    }

    public List<StatItem> getStatItems() {
        return statItems;
    }

    public void setStatItems(List<StatItem> statItems) {
        this.statItems = statItems;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "statItems=" + statItems +
                '}';
    }
}
