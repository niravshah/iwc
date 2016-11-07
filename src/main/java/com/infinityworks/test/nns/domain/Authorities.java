package com.infinityworks.test.nns.domain;

import java.util.List;

public class Authorities {

    private List<Authority> authorities;

    public Authorities() {
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "Authorities{" +
                "authorities=" + authorities +
                '}';
    }
}
