package post_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static test_data.JsonPlaceHolderTestData.jsonPlaceHolderMapper;

public class Post02 extends JsonPlaceHolderBaseUrl {
          /*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2)  {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
              }
        When
            I send POST Request to the Url

        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void post() {
        // Set URL
        spec.pathParam("first", "todos");

        // Set Expected Data:
        Map<String, Object> payload = jsonPlaceHolderMapper(55,"Tidy your room",false);



        System.out.println(payload);
        // Sent Req and Get Resp

        Response response = given(spec).body(payload).when().post("{first}"); // At this stage Serialization is taking place
        response.prettyPrint();                                                 // Serialization : process of converting Java Object to Json Object

        // Do Assertions:
        //  JsonPath json = response.jsonPath();
        // assertEquals(payload.get("title"),json.getString("title"));

        Map<String, Object> actualData = response.as(HashMap.class); // De-serialization is taking place: process of converting Json Object to Java Object.
        assertEquals(payload.get("title"),actualData.get("title"));
        assertEquals(payload.get("userId"),actualData.get("userId"));
        assertEquals(payload.get("completed"),actualData.get("completed"));

    }

}
