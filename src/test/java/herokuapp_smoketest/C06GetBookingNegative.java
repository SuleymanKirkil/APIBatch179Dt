package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.HerokuPojo;

import static herokuapp_smoketest.C01CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class C06GetBookingNegative extends HerOkuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/{{userId}}

    When
        sent get request to the url
    Then
        status code is 404
    And
        body: Not Found

     */
    @Test
    public void get(){
        spec.pathParams("first","booking"
        ,"second", bookingId);

        String expectedData = "Not Found";
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        String actualData = response.asString();
        assertEquals(404,response.statusCode());
        assertEquals(expectedData,actualData);
 }
}
