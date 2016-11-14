package com.infinityworks.test.nns.repositories.impl.rest;

import com.infinityworks.test.nns.domain.Authorities;
import com.infinityworks.test.nns.domain.Authority;
import com.infinityworks.test.nns.domain.Establishment;
import com.infinityworks.test.nns.domain.Establishments;
import com.infinityworks.test.nns.repositories.EstablishmentRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.client.ExpectedCount.times;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class EstablishmentRepositoryRestImplTest extends AbstractRespositoryRestImplTest {

    private static final String ESTABLISHMENTS_REQUEST_URI = "http://api.ratings.food.gov.uk/Establishments?localAuthorityId=197&pageSize=1000&pageNumber=1";
    private static final String ESTABLISHMENTS_REQUEST_VALID_RESPONSE = "{\n  \"establishments\": [\n    {\n      \"FHRSID\": 906411,\n      \"LocalAuthorityBusinessID\": \"EHDC11985\",\n      \"BusinessName\": \"14FORTY @ STATOIL\",\n      \"BusinessType\": \"Restaurant/Cafe/Canteen\",\n      \"BusinessTypeID\": 1,\n      \"AddressLine1\": \"\",\n      \"AddressLine2\": \"Prime Four Business Park\",\n      \"AddressLine3\": \"Kingswells\",\n      \"AddressLine4\": \"Aberdeen\",\n      \"PostCode\": \"AB15 8PJ\",\n      \"Phone\": \"\",\n      \"RatingValue\": \"Pass\",\n      \"RatingKey\": \"fhis_pass_en-gb\",\n      \"RatingDate\": \"2016-06-30T00:00:00\",\n      \"LocalAuthorityCode\": \"760\",\n      \"LocalAuthorityName\": \"Aberdeen City\",\n      \"LocalAuthorityWebSite\": \"http://www.aberdeencity.gov.uk\",\n      \"LocalAuthorityEmailAddress\": \"commercial@aberdeencity.gov.uk\",\n      \"scores\": {\n        \"Hygiene\": null,\n        \"Structural\": null,\n        \"ConfidenceInManagement\": null\n      },\n      \"SchemeType\": \"FHIS\",\n      \"geocode\": {\n        \"longitude\": \"-2.229412\",\n        \"latitude\": \"57.148309\"\n      },\n      \"RightToReply\": \"\",\n      \"Distance\": null,\n      \"NewRatingPending\": false,\n      \"meta\": {\n        \"dataSource\": null,\n        \"extractDate\": \"0001-01-01T00:00:00\",\n        \"itemCount\": 0,\n        \"returncode\": null,\n        \"totalCount\": 0,\n        \"totalPages\": 0,\n        \"pageSize\": 0,\n        \"pageNumber\": 0\n      },\n      \"links\": []\n    },\n    {\n      \"FHRSID\": 592387,\n      \"LocalAuthorityBusinessID\": \"EHDC10723\",\n      \"BusinessName\": \"1906 RESTAURANT AT HMT\",\n      \"BusinessType\": \"Restaurant/Cafe/Canteen\",\n      \"BusinessTypeID\": 1,\n      \"AddressLine1\": \"\",\n      \"AddressLine2\": \"Rosemount Viaduct\",\n      \"AddressLine3\": \"Aberdeen\",\n      \"AddressLine4\": \"\",\n      \"PostCode\": \"AB25 1GL\",\n      \"Phone\": \"\",\n      \"RatingValue\": \"Pass\",\n      \"RatingKey\": \"fhis_pass_en-gb\",\n      \"RatingDate\": \"2015-09-10T00:00:00\",\n      \"LocalAuthorityCode\": \"760\",\n      \"LocalAuthorityName\": \"Aberdeen City\",\n      \"LocalAuthorityWebSite\": \"http://www.aberdeencity.gov.uk\",\n      \"LocalAuthorityEmailAddress\": \"commercial@aberdeencity.gov.uk\",\n      \"scores\": {\n        \"Hygiene\": null,\n        \"Structural\": null,\n        \"ConfidenceInManagement\": null\n      },\n      \"SchemeType\": \"FHIS\",\n      \"geocode\": {\n        \"longitude\": \"-2.104965\",\n        \"latitude\": \"57.148161\"\n      },\n      \"RightToReply\": \"\",\n      \"Distance\": null,\n      \"NewRatingPending\": false,\n      \"meta\": {\n        \"dataSource\": null,\n        \"extractDate\": \"0001-01-01T00:00:00\",\n        \"itemCount\": 0,\n        \"returncode\": null,\n        \"totalCount\": 0,\n        \"totalPages\": 0,\n        \"pageSize\": 0,\n        \"pageNumber\": 0\n      },\n      \"links\": []\n    },\n    {\n      \"FHRSID\": 593681,\n      \"LocalAuthorityBusinessID\": \"EHDC9793\",\n      \"BusinessName\": \"210 BISTRO\",\n      \"BusinessType\": \"Restaurant/Cafe/Canteen\",\n      \"BusinessTypeID\": 1,\n      \"AddressLine1\": \"\",\n      \"AddressLine2\": \"210 Market Street\",\n      \"AddressLine3\": \"Aberdeen\",\n      \"AddressLine4\": \"\",\n      \"PostCode\": \"AB11 5PQ\",\n      \"Phone\": \"\",\n      \"RatingValue\": \"Pass\",\n      \"RatingKey\": \"fhis_pass_en-gb\",\n      \"RatingDate\": \"2015-10-12T00:00:00\",\n      \"LocalAuthorityCode\": \"760\",\n      \"LocalAuthorityName\": \"Aberdeen City\",\n      \"LocalAuthorityWebSite\": \"http://www.aberdeencity.gov.uk\",\n      \"LocalAuthorityEmailAddress\": \"commercial@aberdeencity.gov.uk\",\n      \"scores\": {\n        \"Hygiene\": null,\n        \"Structural\": null,\n        \"ConfidenceInManagement\": null\n      },\n      \"SchemeType\": \"FHIS\",\n      \"geocode\": {\n        \"longitude\": \"-2.092258\",\n        \"latitude\": \"57.142278\"\n      },\n      \"RightToReply\": \"\",\n      \"Distance\": null,\n      \"NewRatingPending\": false,\n      \"meta\": {\n        \"dataSource\": null,\n        \"extractDate\": \"0001-01-01T00:00:00\",\n        \"itemCount\": 0,\n        \"returncode\": null,\n        \"totalCount\": 0,\n        \"totalPages\": 0,\n        \"pageSize\": 0,\n        \"pageNumber\": 0\n      },\n      \"links\": []\n    },\n    {\n      \"FHRSID\": 593037,\n      \"LocalAuthorityBusinessID\": \"EHDC4774\",\n      \"BusinessName\": \"22 CLUB\",\n      \"BusinessType\": \"Pub/bar/nightclub\",\n      \"BusinessTypeID\": 7843,\n      \"AddressLine1\": \"\",\n      \"AddressLine2\": \"55 Rose Street\",\n      \"AddressLine3\": \"Aberdeen\",\n      \"AddressLine4\": \"\",\n      \"PostCode\": \"AB10 1UB\",\n      \"Phone\": \"\",\n      \"RatingValue\": \"Pass\",\n      \"RatingKey\": \"fhis_pass_en-gb\",\n      \"RatingDate\": \"2010-08-30T00:00:00\",\n      \"LocalAuthorityCode\": \"760\",\n      \"LocalAuthorityName\": \"Aberdeen City\",\n      \"LocalAuthorityWebSite\": \"http://www.aberdeencity.gov.uk\",\n      \"LocalAuthorityEmailAddress\": \"commercial@aberdeencity.gov.uk\",\n      \"scores\": {\n        \"Hygiene\": null,\n        \"Structural\": null,\n        \"ConfidenceInManagement\": null\n      },\n      \"SchemeType\": \"FHIS\",\n      \"geocode\": {\n        \"longitude\": \"-2.111912\",\n        \"latitude\": \"57.144562\"\n      },\n      \"RightToReply\": \"\",\n      \"Distance\": null,\n      \"NewRatingPending\": false,\n      \"meta\": {\n        \"dataSource\": null,\n        \"extractDate\": \"0001-01-01T00:00:00\",\n        \"itemCount\": 0,\n        \"returncode\": null,\n        \"totalCount\": 0,\n        \"totalPages\": 0,\n        \"pageSize\": 0,\n        \"pageNumber\": 0\n      },\n      \"links\": []\n    },\n    {\n      \"FHRSID\": 593410,\n      \"LocalAuthorityBusinessID\": \"EHDC8497\",\n      \"BusinessName\": \"3rd HOUSE GUESTHOUSE\",\n      \"BusinessType\": \"Hotel/bed & breakfast/guest house\",\n      \"BusinessTypeID\": 7842,\n      \"AddressLine1\": \"\",\n      \"AddressLine2\": \"406 Great Western Road\",\n      \"AddressLine3\": \"Aberdeen\",\n      \"AddressLine4\": \"\",\n      \"PostCode\": \"AB10 6NR\",\n      \"Phone\": \"\",\n      \"RatingValue\": \"Pass\",\n      \"RatingKey\": \"fhis_pass_en-gb\",\n      \"RatingDate\": \"2012-08-02T00:00:00\",\n      \"LocalAuthorityCode\": \"760\",\n      \"LocalAuthorityName\": \"Aberdeen City\",\n      \"LocalAuthorityWebSite\": \"http://www.aberdeencity.gov.uk\",\n      \"LocalAuthorityEmailAddress\": \"commercial@aberdeencity.gov.uk\",\n      \"scores\": {\n        \"Hygiene\": null,\n        \"Structural\": null,\n        \"ConfidenceInManagement\": null\n      },\n      \"SchemeType\": \"FHIS\",\n      \"geocode\": {\n        \"longitude\": \"-2.130746\",\n        \"latitude\": \"57.133935\"\n      },\n      \"RightToReply\": \"\",\n      \"Distance\": null,\n      \"NewRatingPending\": false,\n      \"meta\": {\n        \"dataSource\": null,\n        \"extractDate\": \"0001-01-01T00:00:00\",\n        \"itemCount\": 0,\n        \"returncode\": null,\n        \"totalCount\": 0,\n        \"totalPages\": 0,\n        \"pageSize\": 0,\n        \"pageNumber\": 0\n      },\n      \"links\": []\n    }\n  ],\n  \"meta\": {\n    \"dataSource\": \"Lucene\",\n    \"extractDate\": \"0001-01-01T00:00:00\",\n    \"itemCount\": 0,\n    \"returncode\": \"OK\",\n    \"totalCount\": 1762,\n    \"totalPages\": 353,\n    \"pageSize\": 5,\n    \"pageNumber\": 1\n  },\n  \"links\": []\n}";
    private static final String ESTABLISHMENTS_REQUEST_INVALID_RESPONSE = "{\n  \"establishments\": [\n    {\n      \"FHRSID\": 906411,\n           \"BusinessType\": \"Restaurant/Cafe/Canteen\",\n      \"BusinessTypeID\": 1,\n      \"AddressLine1\": \"\",\n      \"AddressLine2\": \"Prime Four Business Park\",\n      \"AddressLine3\": \"Kingswells\",\n      \"AddressLine4\": \"Aberdeen\",\n      \"PostCode\": \"AB15 8PJ\",\n      \"Phone\": \"\",\n     \n      \"RatingKey\": \"fhis_pass_en-gb\",\n      \"RatingDate\": \"2016-06-30T00:00:00\",\n      \"LocalAuthorityCode\": \"760\",\n      \"LocalAuthorityName\": \"Aberdeen City\",\n      \"LocalAuthorityWebSite\": \"http://www.aberdeencity.gov.uk\",\n      \"LocalAuthorityEmailAddress\": \"commercial@aberdeencity.gov.uk\",\n      \"scores\": {\n        \"Hygiene\": null,\n        \"Structural\": null,\n        \"ConfidenceInManagement\": null\n      },\n      \"SchemeType\": \"FHIS\",\n      \"geocode\": {\n        \"longitude\": \"-2.229412\",\n        \"latitude\": \"57.148309\"\n      },\n      \"RightToReply\": \"\",\n      \"Distance\": null,\n      \"NewRatingPending\": false,\n      \"meta\": {\n        \"dataSource\": null,\n        \"extractDate\": \"0001-01-01T00:00:00\",\n        \"itemCount\": 0,\n        \"returncode\": null,\n        \"totalCount\": 0,\n        \"totalPages\": 0,\n        \"pageSize\": 0,\n        \"pageNumber\": 0\n      },\n      \"links\": []\n    },\n    {\n      \"FHRSID\": 592387,\n      \"LocalAuthorityBusinessID\": \"EHDC10723\",\n      \"BusinessName\": \"1906 RESTAURANT AT HMT\",\n      \"BusinessType\": \"Restaurant/Cafe/Canteen\",\n      \"BusinessTypeID\": 1,\n      \"AddressLine1\": \"\",\n      \"AddressLine2\": \"Rosemount Viaduct\",\n      \"AddressLine3\": \"Aberdeen\",\n      \"AddressLine4\": \"\",\n      \"PostCode\": \"AB25 1GL\",\n      \"Phone\": \"\",\n      \"RatingValue\": \"Pass\",\n      \"RatingKey\": \"fhis_pass_en-gb\",\n      \"RatingDate\": \"2015-09-10T00:00:00\",\n      \"LocalAuthorityCode\": \"760\",\n      \"LocalAuthorityName\": \"Aberdeen City\",\n      \"LocalAuthorityWebSite\": \"http://www.aberdeencity.gov.uk\",\n      \"LocalAuthorityEmailAddress\": \"commercial@aberdeencity.gov.uk\",\n      \"scores\": {\n        \"Hygiene\": null,\n        \"Structural\": null,\n        \"ConfidenceInManagement\": null\n      },\n      \"SchemeType\": \"FHIS\",\n      \"geocode\": {\n        \"longitude\": \"-2.104965\",\n        \"latitude\": \"57.148161\"\n      },\n      \"RightToReply\": \"\",\n      \"Distance\": null,\n      \"NewRatingPending\": false,\n      \"meta\": {\n        \"dataSource\": null,\n        \"extractDate\": \"0001-01-01T00:00:00\",\n        \"itemCount\": 0,\n        \"returncode\": null,\n        \"totalCount\": 0,\n        \"totalPages\": 0,\n        \"pageSize\": 0,\n        \"pageNumber\": 0\n      },\n      \"links\": []\n    },\n    {\n      \"FHRSID\": 593681,\n      \"LocalAuthorityBusinessID\": \"EHDC9793\",\n      \"BusinessName\": \"210 BISTRO\",\n      \"BusinessType\": \"Restaurant/Cafe/Canteen\",\n      \"BusinessTypeID\": 1,\n      \"AddressLine1\": \"\",\n      \"AddressLine2\": \"210 Market Street\",\n      \"AddressLine3\": \"Aberdeen\",\n      \"AddressLine4\": \"\",\n      \"PostCode\": \"AB11 5PQ\",\n      \"Phone\": \"\",\n      \"RatingValue\": \"Pass\",\n      \"RatingKey\": \"fhis_pass_en-gb\",\n      \"RatingDate\": \"2015-10-12T00:00:00\",\n      \"LocalAuthorityCode\": \"760\",\n      \"LocalAuthorityName\": \"Aberdeen City\",\n      \"LocalAuthorityWebSite\": \"http://www.aberdeencity.gov.uk\",\n      \"LocalAuthorityEmailAddress\": \"commercial@aberdeencity.gov.uk\",\n      \"scores\": {\n        \"Hygiene\": null,\n        \"Structural\": null,\n        \"ConfidenceInManagement\": null\n      },\n      \"SchemeType\": \"FHIS\",\n      \"geocode\": {\n        \"longitude\": \"-2.092258\",\n        \"latitude\": \"57.142278\"\n      },\n      \"RightToReply\": \"\",\n      \"Distance\": null,\n      \"NewRatingPending\": false,\n      \"meta\": {\n        \"dataSource\": null,\n        \"extractDate\": \"0001-01-01T00:00:00\",\n        \"itemCount\": 0,\n        \"returncode\": null,\n        \"totalCount\": 0,\n        \"totalPages\": 0,\n        \"pageSize\": 0,\n        \"pageNumber\": 0\n      },\n      \"links\": []\n    },\n    {\n      \"FHRSID\": 593037,\n      \"LocalAuthorityBusinessID\": \"EHDC4774\",\n      \"BusinessName\": \"22 CLUB\",\n      \"BusinessType\": \"Pub/bar/nightclub\",\n      \"BusinessTypeID\": 7843,\n      \"AddressLine1\": \"\",\n      \"AddressLine2\": \"55 Rose Street\",\n      \"AddressLine3\": \"Aberdeen\",\n      \"AddressLine4\": \"\",\n      \"PostCode\": \"AB10 1UB\",\n      \"Phone\": \"\",\n      \"RatingValue\": \"Pass\",\n      \"RatingKey\": \"fhis_pass_en-gb\",\n      \"RatingDate\": \"2010-08-30T00:00:00\",\n      \"LocalAuthorityCode\": \"760\",\n      \"LocalAuthorityName\": \"Aberdeen City\",\n      \"LocalAuthorityWebSite\": \"http://www.aberdeencity.gov.uk\",\n      \"LocalAuthorityEmailAddress\": \"commercial@aberdeencity.gov.uk\",\n      \"scores\": {\n        \"Hygiene\": null,\n        \"Structural\": null,\n        \"ConfidenceInManagement\": null\n      },\n      \"SchemeType\": \"FHIS\",\n      \"geocode\": {\n        \"longitude\": \"-2.111912\",\n        \"latitude\": \"57.144562\"\n      },\n      \"RightToReply\": \"\",\n      \"Distance\": null,\n      \"NewRatingPending\": false,\n      \"meta\": {\n        \"dataSource\": null,\n        \"extractDate\": \"0001-01-01T00:00:00\",\n        \"itemCount\": 0,\n        \"returncode\": null,\n        \"totalCount\": 0,\n        \"totalPages\": 0,\n        \"pageSize\": 0,\n        \"pageNumber\": 0\n      },\n      \"links\": []\n    },\n    {\n      \"FHRSID\": 593410,\n      \"LocalAuthorityBusinessID\": \"EHDC8497\",\n      \"BusinessName\": \"3rd HOUSE GUESTHOUSE\",\n      \"BusinessType\": \"Hotel/bed & breakfast/guest house\",\n      \"BusinessTypeID\": 7842,\n      \"AddressLine1\": \"\",\n      \"AddressLine2\": \"406 Great Western Road\",\n      \"AddressLine3\": \"Aberdeen\",\n      \"AddressLine4\": \"\",\n      \"PostCode\": \"AB10 6NR\",\n      \"Phone\": \"\",\n      \"RatingValue\": \"Pass\",\n      \"RatingKey\": \"fhis_pass_en-gb\",\n      \"RatingDate\": \"2012-08-02T00:00:00\",\n      \"LocalAuthorityCode\": \"760\",\n      \"LocalAuthorityName\": \"Aberdeen City\",\n      \"LocalAuthorityWebSite\": \"http://www.aberdeencity.gov.uk\",\n      \"LocalAuthorityEmailAddress\": \"commercial@aberdeencity.gov.uk\",\n      \"scores\": {\n        \"Hygiene\": null,\n        \"Structural\": null,\n        \"ConfidenceInManagement\": null\n      },\n      \"SchemeType\": \"FHIS\",\n      \"geocode\": {\n        \"longitude\": \"-2.130746\",\n        \"latitude\": \"57.133935\"\n      },\n      \"RightToReply\": \"\",\n      \"Distance\": null,\n      \"NewRatingPending\": false,\n      \"meta\": {\n        \"dataSource\": null,\n        \"extractDate\": \"0001-01-01T00:00:00\",\n        \"itemCount\": 0,\n        \"returncode\": null,\n        \"totalCount\": 0,\n        \"totalPages\": 0,\n        \"pageSize\": 0,\n        \"pageNumber\": 0\n      },\n      \"links\": []\n    }\n  ],\n  \"meta\": {\n    \"dataSource\": \"Lucene\",\n    \"extractDate\": \"0001-01-01T00:00:00\",\n    \"itemCount\": 0,\n    \"returncode\": \"OK\",\n    \"totalCount\": 1762,\n    \"totalPages\": 353,\n    \"pageSize\": 5,\n    \"pageNumber\": 1\n  },\n  \"links\": []\n}";

    @Autowired
    EstablishmentRepository establishmentRepository;

    @Test
    public void shouldReturnEstablishmentsForSuccessfulAuthoritiesRequest() {
        //given
        server.expect(times(1), requestTo(ESTABLISHMENTS_REQUEST_URI)).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(ESTABLISHMENTS_REQUEST_VALID_RESPONSE, MediaType.APPLICATION_JSON));

        //when
        final Establishments establishments = establishmentRepository.getEstablishmentsByLocalAuthorityId(197, 1000, 1);

        //then
        server.verify();

        assertThat(establishments.getEstablishments().size()).isEqualTo(5);

        final Establishment establishment = establishments.getEstablishments().get(0);
        assertThat(establishment.getLocalAuthorityBusinessID()).isEqualTo("EHDC11985");
        assertThat(establishment.getBusinessName()).isEqualTo("14FORTY @ STATOIL");
        assertThat(establishment.getRatingValue()).isEqualTo("Pass");
    }

    @Test
    public void shouldThrowApiExceptionForInvalidResponseToEstablishmentsRequest() {
        //given
        expectedException.expect(ApiException.class);
        server.expect(times(1), requestTo(ESTABLISHMENTS_REQUEST_URI)).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(ESTABLISHMENTS_REQUEST_INVALID_RESPONSE, MediaType.APPLICATION_JSON));

        //when
        establishmentRepository.getEstablishmentsByLocalAuthorityId(197, 1000, 1);

        //then
        server.verify();
    }

    @Test
    public void shouldThrowApiExceptionForUncaughtExceptionInEstablishmentsRequest() {
        //given
        expectedException.expect(ApiException.class);
        server.expect(times(1), requestTo(ESTABLISHMENTS_REQUEST_URI)).andExpect(method(HttpMethod.GET))
                .andRespond(withServerError());

        //when
        establishmentRepository.getEstablishmentsByLocalAuthorityId(197, 1000, 1);

        //then
        server.verify();
    }

}