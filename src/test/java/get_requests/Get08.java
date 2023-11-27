package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get08 extends JsonPlaceHolderBaseUrl {
            /*
      Given
             https://jsonplaceholder.typicode.com/todos
      When
           I send GET Request to the URL
      Then
           1)Status code is 200
           2)Print all ids greater than 190 on the console
             Assert that there are 10 ids greater than 190
           3)Print all userIds whose ids are less than 5 on the console
             Assert that the number of userIds whose ids are less than 5 is 4
           4)Print all titles whose ids are less than 5
             Assert that "delectus aut autem" is one of the titles whose id is less than 5
   */

    @Test
    public void get(){
        // Set Url
        spec.pathParam("first","todos");

        // Set Expected Data
        // Sent Response and Get Request
        Response response = given(spec).when().get("{first}");
       // response.prettyPrint();

        // Do Assertion

        JsonPath json = response.jsonPath();
      // Print all ids greater than 190 on the console

        // First way : through Lists and loops
        List<Integer> idList = json.getList("id");

        List<Integer> idGreaterThan190 = new ArrayList<>();
        for (Integer w: idList){
            if (w>190){
                idGreaterThan190.add(w);
            }
        }
        System.out.println(idGreaterThan190);

        // Assert that there are 10 ids greater than 190
        assertEquals(10,idGreaterThan190.size());


        // Second Way : Using Java Based "Groovy Language"----> If we need to use conditional asserts for Collections
        // Assert that there are 10 ids greater than 190

        List<Integer> idListFromGroovy = json.getList("findAll{it.id>190}.id");
        System.out.println(idListFromGroovy);

        // Assert that there are 10 ids greater than 190
        assertEquals(10,idListFromGroovy.size());

      // Print all userIds whose ids are less than 5 on the console
        List<Integer> listOfUserIdLessThanFive = json.getList("findAll{it.id<5}.userId");
        System.out.println(listOfUserIdLessThanFive);

      // Assert that the number of userIds whose ids are less than 5 is 4
        assertEquals(4,listOfUserIdLessThanFive.size());

      // Print all titles whose ids are less than 5
        List<String> titleList = json.getList("findAll{it.id<5}.title");
        System.out.println(titleList);

        // Assert that "delectus aut autem" is one of the titles whose id is less than 5
        assertTrue(titleList.contains("delectus aut autem"));

    }

}



