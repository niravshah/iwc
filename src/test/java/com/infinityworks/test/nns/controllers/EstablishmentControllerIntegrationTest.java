package com.infinityworks.test.nns.controllers;

import com.infinityworks.test.nns.domain.StatItem;
import com.infinityworks.test.nns.domain.Stats;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EstablishmentControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void establishmentControllerEndToEndTest() throws Exception {
        final ResponseEntity<Stats> stats = restTemplate.getForEntity("/api/authority/197/stats", Stats.class);
        assertThat(stats.getStatusCode()).isEqualTo(HttpStatus.OK);
        final Stats statsBody = stats.getBody();
        final List<StatItem> statItems = statsBody.getStatItems();
        assertThat(statItems.size()).isGreaterThan(0);
    }

}
