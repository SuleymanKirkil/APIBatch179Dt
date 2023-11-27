package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.preemptive;
import static org.hamcrest.Matchers.equalTo;

public class Get03 extends JsonPlaceHolderBaseUrl {

    /*
        Given
            https://jsonplaceholder.typicode.com/todos/23
        When
            User send GET Request to the URL
        Then
            HTTP Status Code should be 200
		And
		    Response format should be “application/json”
		And
		    “title” is “et itaque necessitatibus maxime molestiae qui quas velit”,
		And
		    “completed” is false
		And
		    “userId” is 2
 */

    @Test
    public void get(){

      // i) Set the URL
        String url = "https://jsonplaceholder.typicode.com/todos/23";

      // ii) Set Expected Data
      // iii) Sent Request and get Response
        Response response = given(spec).when().get();
        response.prettyPrint();

      // iV)  Do Assertions

        // First Way: Hard Assertion ---> If you want your test to stop when it encounters an assertion error select "Hard Assertion"
        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("completed",equalTo(false)).
                body("userId",equalTo(2)).
                body("id",equalTo(23));

        // Second Way: Soft Assertion: ----> If you want your test to continue and let you know all assertion errors use "Soft Assertion"
        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed",equalTo(false),
                        "userId",equalTo(2),
                        "id",equalTo(23));
  }

}
