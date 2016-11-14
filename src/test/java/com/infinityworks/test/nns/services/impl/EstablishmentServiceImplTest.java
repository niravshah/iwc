package com.infinityworks.test.nns.services.impl;

import com.infinityworks.test.nns.domain.Authority;
import com.infinityworks.test.nns.domain.Establishment;
import com.infinityworks.test.nns.domain.Establishments;
import com.infinityworks.test.nns.domain.StatItem;
import com.infinityworks.test.nns.domain.Stats;
import com.infinityworks.test.nns.repositories.AuthorityRepository;
import com.infinityworks.test.nns.repositories.EstablishmentRepository;
import com.infinityworks.test.nns.services.AuthorityService;
import com.infinityworks.test.nns.services.EstablishmentService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class EstablishmentServiceImplTest {

    @Mock
    EstablishmentRepository repository;

    @Mock
    AuthorityRepository authorityRepository;

    @Mock
    AuthorityService authorityService;

    @InjectMocks
    EstablishmentService establishmentService = new EstablishmentServiceImpl();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    List<Establishment> establishmentList;

    @Before
    public void setUp() {

        ReflectionTestUtils.setField(establishmentService, "MAX_PAGE_SIZE", 100);

        Establishment one = new Establishment("1", "A", "5");
        Establishment two = new Establishment("2", "B", "5");
        Establishment three = new Establishment("3", "C", "1");
        Establishment four = new Establishment("4", "D", "Exempt");

        establishmentList = new ArrayList<>();
        establishmentList.add(one);
        establishmentList.add(two);
        establishmentList.add(three);
        establishmentList.add(four);
    }

    @Test
    public void shouldReturnAllEstablishmentsForGivenAuthority() {

        // given
        final int localAuthorityId = 120;
        Authority authority = new Authority(localAuthorityId, "Test Auth", 4);
        given(authorityService.getAuthority(localAuthorityId)).willReturn(authority);
        final Establishments establishments = new Establishments(establishmentList);
        given(repository.getEstablishmentsByLocalAuthorityId(localAuthorityId, 100, 1)).willReturn(establishments);

        final Establishments establishmentsByLocalAuthorityId = establishmentService.getEstablishmentsByLocalAuthorityId(localAuthorityId);

        assertThat(establishmentsByLocalAuthorityId.getEstablishments().size()).isEqualTo(4);
    }

    @Test
    public void shouldReturnStatsForEstablishmentList() {

        // given establishmentList

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