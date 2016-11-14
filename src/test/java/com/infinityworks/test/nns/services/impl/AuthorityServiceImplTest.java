package com.infinityworks.test.nns.services.impl;

import com.infinityworks.test.nns.domain.Authorities;
import com.infinityworks.test.nns.domain.Authority;
import com.infinityworks.test.nns.exceptions.AuthorityNotFoundException;
import com.infinityworks.test.nns.exceptions.NoAuthoritiesFoundException;
import com.infinityworks.test.nns.repositories.AuthorityRepository;
import com.infinityworks.test.nns.services.AuthorityService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AuthorityServiceImplTest {

    @Mock
    AuthorityRepository repository;

    @InjectMocks
    AuthorityService authorityService = new AuthorityServiceImpl();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldReturnAuthoritiesWhenAtleastOneAuthorityFound() {

        // given
        Authority authority = new Authority(120, "Test Auth", 100);
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authority);
        Authorities authorities1 = new Authorities(authorities);
        given(repository.getAuthorities()).willReturn(authorities1);

        //when
        final Authorities authorities2 = authorityService.getAuthorities();

        //then
        assertThat(authorities2.getAuthorities().size()).isEqualTo(1);
    }

    @Test
    public void shouldThrowExceptionWhenNullAuthoritiesReturned() {
        // given
        expectedException.expect(NoAuthoritiesFoundException.class);
        given(repository.getAuthorities()).willReturn(null);

        //when
        authorityService.getAuthorities();

    }
    @Test
    public void shouldThrowExceptionWhenNoAuthoritiesReturned() {
        // given
        Authorities authorities = new Authorities(null);
        expectedException.expect(NoAuthoritiesFoundException.class);
        given(repository.getAuthorities()).willReturn(authorities);

        //when
        authorityService.getAuthorities();

    }

    @Test
    public void shouldGetAuthorityById() {
        // given
        Authority authority = new Authority(120, "Test Auth", 100);
        given(repository.getAuthorityById(120)).willReturn(authority);

        //when
        final Authority authority1 = authorityService.getAuthority(120);

        //then
        assertThat(authority1.getLocalAuthorityId()).isEqualTo(120);
        assertThat(authority1.getName()).isEqualTo("Test Auth");
        assertThat(authority1.getEstablishmentCount()).isEqualTo(100);
    }

    @Test
    public void shouldThrowExceptionWhenNullAuthorityReturned() {
        // given
        expectedException.expect(AuthorityNotFoundException.class);
        given(repository.getAuthorityById(120)).willReturn(null);

        //when
        authorityService.getAuthority(120);
    }

}