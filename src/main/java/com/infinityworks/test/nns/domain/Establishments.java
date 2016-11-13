package com.infinityworks.test.nns.domain;

import java.util.List;

public class Establishments {

    private List<Establishment> establishments;

    public Establishments() {
    }

    public Establishments(List<Establishment> establishments) {
        this.establishments = establishments;
    }

    public List<Establishment> getEstablishments() {
        return establishments;
    }

    public void setEstablishments(List<Establishment> establishments) {
        this.establishments = establishments;
    }

    @Override
    public String toString() {
        return "Establishments{" +
                "establishments=" + establishments +
                '}';
    }
}
