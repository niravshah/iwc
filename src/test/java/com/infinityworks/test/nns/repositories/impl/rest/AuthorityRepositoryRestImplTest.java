package com.infinityworks.test.nns.repositories.impl.rest;

import com.infinityworks.test.nns.domain.Authorities;
import com.infinityworks.test.nns.domain.Authority;
import com.infinityworks.test.nns.repositories.AuthorityRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.client.ExpectedCount.manyTimes;
import static org.springframework.test.web.client.ExpectedCount.times;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AuthorityRepositoryRestImplTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private static final String AUTHORITIES_REQUEST_URI = "http://api.ratings.food.gov.uk/Authorities/basic";
    private static final String AUTHORITY_REQUEST_URI = "http://api.ratings.food.gov.uk/Authorities/197";

    private static final String AUTHORITIES_REQUEST_VALID_RESPONSE = "{\n  \"authorities\": [\n    {\n      \"LocalAuthorityId\": 197,\n      \"LocalAuthorityIdCode\": \"760\",\n      \"Name\": \"Aberdeen City\",\n      \"EstablishmentCount\": 1762,\n      \"SchemeType\": 2,\n      \"links\": [\n        {\n          \"rel\": \"self\",\n          \"href\": \"http://api.ratings.food.gov.uk/authorities/197\"\n        }\n      ]\n    }\n]}";
    private static final String AUTHORITIES_REQUEST_INVALID_RESPONSE = "{\n  \"authorities\": [\n    {\n      \n      \"links\": [\n        {\n          \"rel\": \"self\",\n          \"href\": \"http://api.ratings.food.gov.uk/authorities/197\"\n        }\n      ]\n    }\n]}";

    private static final String AUTHORITY_REQUEST_VALID_RESPONSE = "{\n  \"LocalAuthorityId\": 197,\n  \"LocalAuthorityIdCode\": \"760\",\n  \"Name\": \"Aberdeen City\",\n  \"FriendlyName\": \"aberdeen-city\",\n  \"Url\": \"http://www.aberdeencity.gov.uk\",\n  \"SchemeUrl\": \"\",\n  \"Email\": \"commercial@aberdeencity.gov.uk\",\n  \"RegionName\": \"Scotland\",\n  \"FileName\": \"http://ratings.food.gov.uk/OpenDataFiles/FHRS760en-GB.xml\",\n  \"FileNameWelsh\": null,\n  \"EstablishmentCount\": 1762,\n  \"CreationDate\": \"2010-08-17T15:30:24.87\",\n  \"LastPublishedDate\": \"2016-10-12T00:37:35.363\",\n  \"SchemeType\": 2,\n  \"links\": [\n    {\n      \"rel\": \"self\",\n      \"href\": \"http://api.ratings.food.gov.uk/authorities/197\"\n    }\n  ]\n}";
    private static final String AUTHORITY_REQUEST_INVALID_RESPONSE = "{\n  \"authorities\": [\n    {\n      \n      \"links\": [\n        {\n          \"rel\": \"self\",\n          \"href\": \"http://api.ratings.food.gov.uk/authorities/197\"\n        }\n      ]\n    }\n]}";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    AuthorityRepository authorityRepository;

    MockRestServiceServer server;

    @Before
    public void setUp() {
        server = MockRestServiceServer.bindTo(restTemplate).build();
    }

    @Test
    public void shouldReturnAuthoritiesForSuccessfulAuthoritiesRequest() {
        //given
        server.expect(times(1), requestTo(AUTHORITIES_REQUEST_URI)).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(AUTHORITIES_REQUEST_VALID_RESPONSE, MediaType.APPLICATION_JSON));

        //when
        final Authorities authorities = authorityRepository.getAuthorities();

        //then
        server.verify();

        final List<Authority> authorityList = authorities.getAuthorities();
        assertThat(authorityList.size()).isEqualTo(1);

        final Authority authority = authorityList.get(0);
        assertThat(authority.getLocalAuthorityId()).isEqualTo(197);
        assertThat(authority.getEstablishmentCount()).isEqualTo(1762);
        assertThat(authority.getName()).isEqualTo("Aberdeen City");
    }

    @Test
    public void shouldThrowApiExceptionForInvalidResponseForAuthoritiesRequest() {
        //given
        expectedException.expect(ApiException.class);
        server.expect(times(1), requestTo(AUTHORITIES_REQUEST_URI)).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(AUTHORITIES_REQUEST_INVALID_RESPONSE, MediaType.APPLICATION_JSON));

        //when
        authorityRepository.getAuthorities();

        //then
        server.verify();
    }

    @Test
    public void shouldThrowApiExceptionForUncaughtErrorsForAuthoritiesRequest() {
        //given
        expectedException.expect(ApiException.class);
        server.expect(times(1), requestTo(AUTHORITIES_REQUEST_URI)).andExpect(method(HttpMethod.GET))
                .andRespond(withServerError());

        //when
        authorityRepository.getAuthorities();

        //then
        server.verify();
    }

    @Test
    public void shouldReturnAuthorityForSuccessfulAuthorityRequest() {
        //given
        server.expect(times(1), requestTo(AUTHORITY_REQUEST_URI)).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(AUTHORITY_REQUEST_VALID_RESPONSE, MediaType.APPLICATION_JSON));

        //when
        final Authority authority = authorityRepository.getAuthorityById(197);

        //then
        server.verify();

        assertThat(authority.getLocalAuthorityId()).isEqualTo(197);
        assertThat(authority.getEstablishmentCount()).isEqualTo(1762);
        assertThat(authority.getName()).isEqualTo("Aberdeen City");
    }

    @Test
    public void shouldThrowApiExceptionForInvalidResponseForAuthorityRequest() {
        //given
        expectedException.expect(ApiException.class);
        server.expect(times(1), requestTo(AUTHORITY_REQUEST_URI)).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(AUTHORITY_REQUEST_INVALID_RESPONSE, MediaType.APPLICATION_JSON));

        //when
        authorityRepository.getAuthorityById(197);

        //then
        server.verify();
    }

    @Test
    public void shouldThrowApiExceptionForUncaughtErrorsForAuthorityRequest() {
        //given
        expectedException.expect(ApiException.class);
        server.expect(times(1), requestTo(AUTHORITY_REQUEST_URI)).andExpect(method(HttpMethod.GET))
                .andRespond(withServerError());

        //when
        authorityRepository.getAuthorityById(197);

        //then
        server.verify();
    }

}