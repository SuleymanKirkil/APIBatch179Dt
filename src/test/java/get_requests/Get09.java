package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerokuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static test_data.HerokuAppTestData.bookingDatesMapper;
import static test_data.HerokuAppTestData.herokuAppMapper;

public class Get09 extends HerOkuAppBaseUrl {

     /*
        Given
            https://restful-booker.herokuapp.com/booking/65
        When
            I send GET Request to the url
        Then
            Response body should be like that;
                {
                    "firstname": "Jane",
                    "lastname": "Doe",
                    "totalprice": 111,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2018-01-01",
                        "checkout": "2019-01-01"
                    },
                    "additionalneeds": "Extra pillows please"
                }
     */

    @Test
    public void get(){

        // Set Url
        spec.pathParams("first","booking"
        ,"second",72);

        // Set Expected Data
        Map<String ,String> bookingDatesMap = bookingDatesMapper("2018-01-01","2019-01-01");
        Map<String,Object> expectedDataMap  = herokuAppMapper("Jane","Doe",111,true,bookingDatesMap,"Extra pillows please");

        System.out.println(expectedDataMap);

        // Sent Request and Get Response:
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        // Do Assertions
        Map<String,Object> actualDataMap = response.as(HashMap.class);

        assertEquals(200,response.statusCode());
       // assertEquals(expectedDataMap,actualDataMap);
        assertEquals(expectedDataMap.get("firstname"),actualDataMap.get("firstname"));
        assertEquals(expectedDataMap.get("lastname"),actualDataMap.get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"),actualDataMap.get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"),actualDataMap.get("depositpaid"));
        assertEquals(bookingDatesMap.get("checkin"), ((Map)(actualDataMap.get("bookingdates"))).get("checkin"));
        assertEquals(bookingDatesMap.get("checkout"), ((Map)(actualDataMap.get("bookingdates"))).get("checkout"));
        assertEquals(expectedDataMap.get("additionalneeds"),actualDataMap.get("additionalneeds"));


        JsonPath json = response.jsonPath();
        assertEquals(bookingDatesMap.get("checkin"), json.getString("bookingdates.checkin"));
        assertEquals(bookingDatesMap.get("checkout"), json.getString("bookingdates.checkout"));

        response.then()
                .body("bookingdates.checkin",is(bookingDatesMap.get("checkin")),
                        "bookingdates.checkout",equalTo(bookingDatesMap.get("checkout")));



    }


}
