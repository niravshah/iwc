package com.infinityworks.test.nns.domain;

import java.util.List;

public class Stats {

    private List<StatItem> statItems;

    public Stats(List<StatItem> statItems) {
        this.statItems = statItems;
    }

    public List<StatItem> getStatItems() {
        return statItems;
    }

    public void setStatItems(List<StatItem> statItems) {
        this.statItems = statItems;
    }
}
