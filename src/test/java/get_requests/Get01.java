package get_requests;

/*
     1) We will use Postman for manuel Tests
     2) We will use Rest Assured Library for automation.
     3) To type Automation Scripts follow those steps:
        a)  We should understand the requirements
        b)  We should write "Test Script"
            While writing test case we should use "Gherkin Language"
            "Gherkin Language" has four keywords:
                i) Given: used for pre-conditions (url, body, authorisation , Content Type ,...
                ii) When: used for action ( method name: get, post , put ....)
                iii) Then: used for assertion
                iV) And: used for multiple usage of keyword
        c)  Start to write automation test scripts:
            i) Set the URL
            ii) Set Expected Data
            iii) Sent Request and Get Response
            iV)  Do Assertions

 */

/*
          Given
              https://restful-booker.herokuapp.com/booking/10
          When
              User sends a GET Request to the url
          Then
              HTTP Status Code should be 200
          And
              Content Type should be application/json
          And
              Status Line should be HTTP/1.1 200 OK
 */
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get01 {

    @Test
    public void get(){
       // i) Set the URL
        String url = "https://restful-booker.herokuapp.com/booking/10";

       // ii) Set Expected Data  ---- > we will do it later
       // iii) Sent Request and get Response

        // First way: For simple tests

        /*given().
                when().
                get(url).
                then().         // Assertion starts....
                statusCode(200).
                contentType("application/json").
                statusLine("HTTP/1.1 200 OK");

         */

        // iii) Sent Request and get Response
        Response response = given().when().get(url);
        response.prettyPrint();

       // iV)  Do Assertions
        response.then().        // Assertion starts....
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK");

    }
}
