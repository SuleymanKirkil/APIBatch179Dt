package delete_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class Delete01 extends JsonPlaceHolderBaseUrl {
             /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */
    @Test
    public void delete(){
        // Set Url
        spec.pathParams("first","todos"
        ,"second",198);

        // Set Expected Data:

        Map<String ,Object> expectedData = JsonPlaceHolderTestData.jsonPlaceHolderMapper(null,null,null);

        System.out.println("expectedData = " + expectedData);


        // Sent Request and Get Response
        Response response = given(spec).when().delete("{first}/{second}");
        response.prettyPrint();

        // Do Assertions:

       Map<String,Object> actualData = convertJsonToJava(response.asString(), HashMap.class);
        System.out.println(actualData);

        assertEquals(200,response.statusCode());

        // First Way:
        assertEquals(expectedData,actualData);

        // Second Way;
        assertTrue(actualData.isEmpty());

        // Third Way:
        assertEquals(null,actualData.get(0));


    }
}
