package com.infinityworks.test.nns.controllers;

import java.util.List;

public class TestAuthorities {

    private List<TestAuthority> authorities;

    private TestAuthorities() {
    }

    public TestAuthorities(List<TestAuthority> authorities) {
        this.authorities = authorities;
    }

    public List<TestAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String toString() {
        return "TestAuthorities{" +
                "authorities=" + authorities +
                '}';
    }
}
