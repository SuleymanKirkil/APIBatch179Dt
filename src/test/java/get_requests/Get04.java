package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get04 extends JsonPlaceHolderBaseUrl {
       /*
        Given
            https://jsonplaceholder.typicode.com/todos
        And
	        Accept type is “application/json”
        When
	 	    I send a GET request to the Url
	    Then
	        HTTP Status Code should be 200
	    And
	        Response format should be "application/json"
	    And
	        There should be 200 todos
	    And
	        "quis eius est sint explicabo" should be one of the todos title
	    And
	        2, 7, and 9 should be among the userIds
     */

    @Test
    public  void get(){
       //i) Set the URL
        spec.accept(ContentType.JSON).pathParam("first","todos");
       //ii) Set Expected Data
       //iii) Sent Request and Get Response


        given(spec).when().get("{first}").prettyPrint();
       //iV)  Do Assertions

    }
}
