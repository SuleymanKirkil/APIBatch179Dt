package put_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static test_data.JsonPlaceHolderTestData.jsonPlaceHolderMapper;

public class Put01 extends JsonPlaceHolderBaseUrl {

                 /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "userId": 21,
                 "title": "Wash the dishes",
                 "completed": false
               }
        When
	 		I send PUT Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 21,
									    "title": "Wash the dishes",
									    "completed": false
									   }
     */

    @Test
    public void put(){
        // Set Url
        spec.pathParams("first","todos"
        ,"second",198);

        // Set Expected Data
        Map<String ,Object> payLoad = jsonPlaceHolderMapper(21,"Wash the dishes",false);

        System.out.println(payLoad);

        // Sent Request And Response:
        Response response = given(spec).body(payLoad).when().put("{first}/{second}");
        response.prettyPrint();

        // Do Assertions

        Map<String,Object> actualData = response.as(HashMap.class);
        assertEquals(200,response.statusCode());
        assertEquals(payLoad.get("userId"),actualData.get("userId"));
        assertEquals(payLoad.get("title"),actualData.get("title"));
        assertEquals(payLoad.get("completed"),actualData.get("completed"));













    }

}
