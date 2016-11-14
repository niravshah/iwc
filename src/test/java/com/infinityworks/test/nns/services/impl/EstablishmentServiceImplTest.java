package com.infinityworks.test.nns.services.impl;

import com.infinityworks.test.nns.domain.Establishment;
import com.infinityworks.test.nns.domain.StatItem;
import com.infinityworks.test.nns.domain.Stats;
import com.infinityworks.test.nns.repositories.EstablishmentRepository;
import com.infinityworks.test.nns.services.EstablishmentService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class EstablishmentServiceImplTest {

    @Mock
    EstablishmentRepository repository;

    @InjectMocks
    EstablishmentService establishmentService = new EstablishmentServiceImpl();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldReturnStatsForEstablishmentList() {

        // given
        Establishment one = new Establishment("1","A","5");
        Establishment two = new Establishment("2","B","5");
        Establishment three = new Establishment("3","C","1");
        Establishment four = new Establishment("4","D","Exempt");

        List<Establishment> establishmentList = new ArrayList<>();
        establishmentList.add(one);
        establishmentList.add(two);
        establishmentList.add(three);
        establishmentList.add(four);

        // when
        final Stats establishmentStats = establishmentService.getEstablishmentStats(establishmentList);

        // then
        final List<StatItem> statItems = establishmentStats.getStatItems();
        assertThat(statItems.size()).isEqualTo(3);

        assertThat(statItems.get(0).getRating()).isEqualTo("1");
        assertThat(statItems.get(0).getTotalEstablishments()).isEqualTo(1);
        assertThat(statItems.get(0).getPercentage()).isEqualTo(25.0f);

        assertThat(statItems.get(1).getRating()).isEqualTo("5");
        assertThat(statItems.get(1).getTotalEstablishments()).isEqualTo(2);
        assertThat(statItems.get(1).getPercentage()).isEqualTo(50.0f);

        assertThat(statItems.get(2).getRating()).isEqualTo("Exempt");
        assertThat(statItems.get(2).getTotalEstablishments()).isEqualTo(1);
        assertThat(statItems.get(2).getPercentage()).isEqualTo(25.0f);


    }

}