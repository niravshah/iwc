package com.infinityworks.test.nns.repositories.impl.rest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AbstractRespositoryRestImplTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    protected MockRestServiceServer server;

    @Autowired
    protected RestTemplate restTemplate;

    @Before
    public void setUp() {
        server = MockRestServiceServer.bindTo(restTemplate).build();
    }
}
