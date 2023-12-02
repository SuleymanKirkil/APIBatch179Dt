package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.HerokuPojo;
import test_data.HerokuAppTestData;

import java.util.Map;

import static herokuapp_smoketest.C01CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class C04PartiallyUpdateBooking extends HerOkuAppBaseUrl {
     /*
    Given
        https://restful-booker.herokuapp.com/booking/{{bookingId}}
    And
        {
            "firstname" : "Brad",
            "lastname" : "Pit",
            "additionalneeds": "Lemonate"
        }
    When
        sent patch request to the url
    Then
        status code is 200
    And
        body: {
                "firstname": "Brad",
                "lastname": "Pit",
                "totalprice": 113,
                "depositpaid": false,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Lemonate"
               }
     */
    @Test
    public void patch(){
        spec.pathParams("first","booking"
                ,"second",bookingId);


        Map<String,Object> payLoad = HerokuAppTestData.herokuAppMapper("Brad","Pit"
                                                                    ,null,null
                                                                    ,null,"Lemonate");

        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01","2019-01-01");
        HerokuPojo expectedData = new HerokuPojo("Brad","Pit",113,false,bookingDates,"Lemonate");

        System.out.println("payLoad = " + payLoad);

        Response response = given(spec).body(payLoad).when().patch("{first}/{second}");

        HerokuPojo actualData = response.as(HerokuPojo.class);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(bookingDates.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());



    }
}
