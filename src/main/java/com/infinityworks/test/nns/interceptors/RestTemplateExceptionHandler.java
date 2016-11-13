package com.infinityworks.test.nns.interceptors;

import com.infinityworks.test.nns.repositories.impl.rest.ApiException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

public class RestTemplateExceptionHandler extends DefaultResponseErrorHandler {

    private static final Log logger = LogFactory.getLog(RestTemplateExceptionHandler.class);

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        logger.error(response.getStatusCode() + response.getStatusText());
        throw new ApiException(response.getStatusText(), null);
    }

}