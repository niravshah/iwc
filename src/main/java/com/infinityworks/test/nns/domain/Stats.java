package com.infinityworks.test.nns.domain;

import java.util.Map;

public class Stats {

    private Map<String, Long> stats;

    public Stats() {
    }

    public Stats(Map<String, Long> stats) {
        this.stats = stats;
    }

    public Map<String, Long> getStats() {
        return stats;
    }

    public void setStats(Map<String, Long> stats) {
        this.stats = stats;
    }
}
