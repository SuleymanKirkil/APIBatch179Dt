package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static herokuapp_smoketest.C01CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class C05DeleteBooking extends HerOkuAppBaseUrl {
        /*
    Given
        https://restful-booker.herokuapp.com/booking/{{userId}}

    When
        sent delete request to the url
    Then
        status code is 201
    And
        body: Created
     */
        @Test
    public void delete(){
       spec.pathParams("first","booking"
            ,"second",bookingId);

       String expectedData = "Created";

       Response response = given(spec).when().delete("{first}/{second}");

       String actualData = response.asString();

       response.then().statusCode(201);
       assertEquals(expectedData,actualData);
    }

}
