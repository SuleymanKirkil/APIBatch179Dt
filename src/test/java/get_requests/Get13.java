package get_requests;

import base_urls.SpaceXBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get13 extends SpaceXBaseUrl {
                /*
    Given
        https://api.spacexdata.com/v3/launches
    When
        User sends Get Request to the Url
    Then
        Status code is 200
    And
        There are  111 launches
    And
        "Falcon 1" and "Falcon 9" are among the rocket names
    And
        25 launches are fired on 2020
    And
        "Trailblazer" is one of the  failed mission name



     */
    @Test
    public void get(){

    spec.pathParam("first","launches")    ;

    Response response = given(spec).when().get("{first}");
    response.prettyPrint();

    JsonPath json = response.jsonPath();
      //  Status code is 200

    assertEquals(200,response.statusCode());

    //    There are  111 launches
     int numOfLaunches = json.getList("flight_number").size();
     assertEquals(111,numOfLaunches);

     //   "Falcon 1" and "Falcon 9" are among the rocket names
        List<String> rocketNamesList =  json.getList("rocket.rocket_name");
        assertTrue(rocketNamesList.contains("Falcon 1"));
        assertTrue(rocketNamesList.contains("Falcon 9"));

       // 25 launches are fired on 2020

        int numOfLaunch2020 = json.getList("findAll{it.launch_year=='2020'}").size();
        assertEquals(25,numOfLaunch2020);

       // "Trailblazer" is one of the  failed mission name
        List<String> names = json.getList("findAll{it.launch_success== false}.mission_name");
        assertTrue(names.contains("Trailblazer"));

 }
}
