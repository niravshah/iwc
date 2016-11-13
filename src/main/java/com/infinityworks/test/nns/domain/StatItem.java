package com.infinityworks.test.nns.domain;

/**
 * Created by niravshah on 13/11/2016.
 */
public class StatItem {

    private String name;
    private Long y;

    public StatItem(String name, Long y) {
        this.name = name;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }
}
