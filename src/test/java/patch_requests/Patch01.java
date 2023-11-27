package patch_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static test_data.JsonPlaceHolderTestData.jsonPlaceHolderMapper;

public class Patch01 extends JsonPlaceHolderBaseUrl {
            /*
     Given
         1) https://jsonplaceholder.typicode.com/todos/198
         2) {
              "title": "Read the books"
            }
     When
          I send PATCH Request to the Url
     Then
           Status code is 200
           And response body is like   {
                                         "userId": 10,
                                         "title": "Read the books",
                                         "completed": true,
                                         "id": 198

                                        }
             */

    @Test
    public void patch(){
        // Set Url
        spec.pathParams("first","todos"
        ,"second", 198);

        // Set Expected Data:
        Map<String,Object> payLoad =  jsonPlaceHolderMapper(null,"Read the books",null);
        Map<String,Object> expected =  jsonPlaceHolderMapper(10,"Read the books",true);
        System.out.println(payLoad);

        // Set Request Get Response
        Response response = given(spec).body(payLoad).when().patch("{first}/{second}");
        response.prettyPrint();

        // Do Assertions
       Map<String,Object> actualData =  response.as(HashMap.class);

        assertEquals(200,response.statusCode());
        assertEquals(expected.get("userId"),actualData.get("userId"));
        assertEquals(expected.get("title"),actualData.get("title"));
        assertEquals(expected.get("completed"),actualData.get("completed"));

    }
}
