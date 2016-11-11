package com.infinityworks.test.nns.repositories.impl.rest;

import org.springframework.beans.factory.annotation.Value;

public abstract class BaseRestRespository {

    @Value("${api.root}")
    private String rootUrl;

    public String getRootUrl() {
        return rootUrl;
    }
}
