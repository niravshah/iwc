package com.infinityworks.test.nns.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthoritiesControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void authoritiesControllerEndToEndTest() throws Exception {
        final ResponseEntity<TestAuthorities> authorities = restTemplate.getForEntity("/api/authorities", TestAuthorities.class);
        assertThat(authorities.getStatusCode()).isEqualTo(HttpStatus.OK);
        final TestAuthorities testAuthorities = authorities.getBody();
        assertThat(testAuthorities.getAuthorities().size()).isGreaterThanOrEqualTo(392);
    }

}