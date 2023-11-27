package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class Get05 extends HerOkuAppBaseUrl {
        /*
       Given
           https://restful-booker.herokuapp.com/booking
       When
           User sends get request to the URL
       Then
           Status code is 200
       And
           Among the data there should be someone whose firstname is "John" and lastname is "Smith"
    */

    @Test
    public  void get(){
     //  i) Set the Url
        spec.pathParam("first", "booking")
                .queryParams("firstname","John"
                            ,"lastname","Smith");
     //  ii) Set the Expected Data
     //  iii) Send Request And Get Response
        Response response = given().spec(spec).when().get("{first}");
        response.prettyPrint();
     //  iv)  Do Assertions
        response.then()
                .statusCode(200)
                .body(containsString("bookingid"))
                .body("bookingid",hasSize(greaterThan(0)));

        assertTrue(response.asString().contains("bookingid"));








    }

}
