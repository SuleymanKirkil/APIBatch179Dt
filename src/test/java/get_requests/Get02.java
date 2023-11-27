package get_requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Get02 {
    /*
       Given
           https://restful-booker.herokuapp.com/booking/0
       When
           User send a GET Request to the url
       Then
           HTTP Status code should be 404
       And
           Status Line should be HTTP/1.1 404 Not Found
       And
           Response body contains "Not Found"
       And
           Response body does not contain "TechProEd"
       And
           Server is "Cowboy"
 */

    @Test
    public void get(){
      //  i) Set the URL
        String url = "https://restful-booker.herokuapp.com/booking/0";

      //  ii) Set Expected Data
      //  iii) Sent Request and get Response
        Response response = given().when().get(url);
        response.prettyPrint();

      //  iV)  Do Assertions
        response.then().
                statusCode(404).            // HTTP Status code should be 404
                statusLine("HTTP/1.1 404 Not Found");     // Status Line should be HTTP/1.1 404 Not Found

        // Response body contains "Not Found"
        String responseStr = response.asString();  // We can change response to other data types -----> for example to String.
        assertTrue("The response Does not contains Not Found ",responseStr.contains("Not Found"));
        //assertTrue("The response Does not contains Not Found ",responseStr.equals("Not Found"));

        // Response body does not contain "TechProEd"
        assertFalse("The response contains Not Found ",responseStr.contains("TechProEd"));

        // Server is "Cowboy"
        assertEquals("Cowboy",response.header("server"));

    }

}
