package post_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class Post04 extends JsonPlaceHolderBaseUrl {
           /*
         Given
            https://jsonplaceholder.typicode.com/todos
            {
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
    public void post(){
        // Set Url
        spec.pathParam("first","todos");

        // Set Expected Data:
        JsonPlaceHolderPojo payLoad = new JsonPlaceHolderPojo(55,"Tidy your room",false);
        System.out.println(payLoad);

        // Send Request And Get Response:
        Response response = given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();

        // Do Assertion:
        JsonPlaceHolderPojo actualData = response.as(JsonPlaceHolderPojo.class);
        System.out.println(actualData);


        assertEquals(201,response.statusCode());
        assertEquals(payLoad.getUserId(),actualData.getUserId());
        assertEquals(payLoad.getTitle(),actualData.getTitle());
        assertEquals(payLoad.getCompleted(),actualData.getCompleted());
    }
}
