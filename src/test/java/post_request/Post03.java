package post_request;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerokuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.*;
import static test_data.HerokuAppTestData.bookingDatesMapper;
import static test_data.HerokuAppTestData.herokuAppMapper;

public class Post03 extends HerOkuAppBaseUrl {

    /*
Given
1) https://restful-booker.herokuapp.com/booking
2) {
    "firstname": "John",
    "lastname": "Doe",
    "totalprice": 11111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2021-09-09",
        "checkout": "2021-09-21"
     }
     "additionalneeds": "Extra pillows please"
  }
When
I send POST Request to the Url
Then
Status code is 200
And response body should be like {
                                   "bookingid": 5315,
                                   "booking": {
                                       "firstname": "John",
                                       "lastname": "Doe",
                                       "totalprice": 11111,
                                       "depositpaid": true,
                                       "bookingdates": {
                                           "checkin": "2021-09-09",
                                           "checkout": "2021-09-21"
                                       }
                                     "additionalneeds": "Extra pillows please"
                                   }
                                }
*/
    @Test
    public void post() {
        // Set Url:
        spec.pathParam("first", "booking");

        // Set Expected Data:

        Map<String, String> bookingDatesMap = bookingDatesMapper("2021-09-09", "2021-09-21");
        Map<String, Object> payLoad = herokuAppMapper("John", "Doe", 11111, true, bookingDatesMap, "Extra pillows please");

        // Send Request And Get Response:
        Response response = given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();

        // Do assertion:
        response.then()
                .statusCode(200)
                .body("booking.firstname", equalTo(payLoad.get("firstname"))
                        , "booking.lastname", equalTo(payLoad.get("lastname"))
                        , "booking.totalprice", equalTo(payLoad.get("totalprice"))
                        , "booking.depositpaid", equalTo(payLoad.get("depositpaid"))
                        , "booking.bookingdates.checkin", equalTo(bookingDatesMap.get("checkin"))
                        , "booking.bookingdates.checkout", equalTo(bookingDatesMap.get("checkout"))
                        , "booking.additionalneeds", equalTo(payLoad.get("additionalneeds")));

        Map<String, Object> actualData = response.as(HashMap.class);
        assertEquals(200, response.statusCode());
        assertEquals(payLoad.get("firstname"), ((Map) actualData.get("booking")).get("firstname"));
        assertEquals(payLoad.get("lastname"), ((Map) actualData.get("booking")).get("lastname"));
        assertEquals(payLoad.get("totalprice"), ((Map) actualData.get("booking")).get("totalprice"));
        assertEquals(payLoad.get("depositpaid"), ((Map) actualData.get("booking")).get("depositpaid"));
        assertEquals(bookingDatesMap.get("checkin"), ((Map) ((Map) actualData.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(bookingDatesMap.get("checkout"), ((Map) ((Map) actualData.get("booking")).get("bookingdates")).get("checkout"));
        assertEquals(payLoad.get("additionalneeds"), ((Map) actualData.get("booking")).get("additionalneeds"));
    }
}

