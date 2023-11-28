package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import test_data.JsonPlaceHolderTestData;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static test_data.JsonPlaceHolderTestData.StringInJson;
import static test_data.JsonPlaceHolderTestData.jsonPlaceHolderMapper;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class Get11ObjectMApper extends JsonPlaceHolderBaseUrl {
        /*
       Given
           https://jsonplaceholder.typicode.com/todos/198
       When
            I send GET Request to the URL
        Then
            Status code is 200
            And response body is like {
                                       "userId": 10,
                                       "id": 198,
                                       "title": "quis eius est sint explicabo",
                                       "completed": true
                                     }
            {"userId": 10,"id": 198,"title": "quis eius est sint explicabo","completed": true}

     */
    @Test
    public void get(){
  // Set Url:
      spec.pathParams("first","todos"
      ,"second",198)  ;

  // Set Expected:

        // First Way:
            Map<String,Object> expDataMap = jsonPlaceHolderMapper(10,"quis eius est sint explicabo",true);
            System.out.println("expDataMap = " + expDataMap);

        System.out.println("expDataMap.get(\"userId\") = " + expDataMap.get("title"));

        // Second Way:
            JsonPlaceHolderPojo expDataPojo = new JsonPlaceHolderPojo(10,"quis eius est sint explicabo",true);
            System.out.println("expDataPojo.getTitle() = " + expDataPojo.getTitle());
            System.out.println(expDataPojo);

        // Third Way --> Object mapper with map

        // String expectedStr = "{\"userId\": 10,\"id\": 198,\"title\": \"quis eius est sint explicabo\",\"completed\": true}";

           /* String expectedStr = "{\n" +
                "          \"userId\": 10,\n" +
                "          \"id\": 198,\n" +
                "          \"title\": \"quis eius est sint explicabo\",\n" +
                "          \"completed\": true\n" +
                "        }";

            */

            String expectedStr = StringInJson(10,"quis eius est sint explicabo",true);

            System.out.println("expectedStr = " + expectedStr);
            Map<String,Object> expDataMapOM = convertJsonToJava(expectedStr, HashMap.class);
            System.out.println("expDataMapOM.get(\"title\") = " + expDataMapOM.get("title"));

        // Fourth Way: Object mapper with Pojo -----> this is the most preferred way:
            JsonPlaceHolderPojo expDataPojoOM = convertJsonToJava(expectedStr, JsonPlaceHolderPojo.class);
             System.out.println("expDataPojoOM.getTitle() = " + expDataPojoOM.getTitle());


 // Sent Request And Get Response:
        Response response = given(spec).when().get("{first}/{second}");

// Do Assertions:
        Map<String,Object> actualDataMap = convertJsonToJava(response.asString(), HashMap.class);
        JsonPlaceHolderPojo actualDataPojo = convertJsonToJava(response.asString(),JsonPlaceHolderPojo.class);

        assertEquals(actualDataPojo.getUserId(),expDataPojoOM.getUserId());
        assertEquals(actualDataPojo.getTitle(),expDataPojoOM.getTitle());
        assertEquals(actualDataPojo.getCompleted(),expDataPojoOM.getCompleted());














    }
}
