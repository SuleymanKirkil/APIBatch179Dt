package gmibank_api;

import base_urls.GMIBankBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.gmibank_pojos.Country;
import pojos.gmibank_pojos.State;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class CreateCountry extends GMIBankBaseUrl {
     /*
    Given
        https://gmibank.com/api/tp-countries
    And
        {
            "id": null,
            "name": "Banana Republic",
            "states": [
                {
                    "id": 0,
                    "name": "Apple",
                    "tpcountry": null
                },
                {
                    "id": 1,
                    "name": "Orange",
                    "tpcountry": null
                },
                {
                    "id": 2,
                    "name": "Peach",
                    "tpcountry": null
                }
            ]
        }
    When
        Send post request
    Then
        Status code is 201
    And
        Response body is like:
        {
            "id": 189865,
            "name": "Banana Republic",
            "states": [
                {
                    "id": 0,
                    "name": "Apple",
                    "tpcountry": null
                },
                {
                    "id": 1,
                    "name": "Orange",
                    "tpcountry": null
                },
                {
                    "id": 2,
                    "name": "Peach",
                    "tpcountry": null
                }
            ]
        }
     */
    @Test
    public void createCountry(){
     spec.pathParams("first","api"
     ,"second","tp-countries");

        State state1 = new State(0,"Apple",null);
        State state2 = new State(1,"Orange",null);
        State state3 = new State(2,"Peach",null);

        List<State> states = new ArrayList<>();
        states.add(state1);
        states.add(state2);
        states.add(state3);
        System.out.println("states = " + states);

        Country payLoad = new Country(null,"Banana Republic",states);

        Response response = given(spec).body(payLoad).when().post("{first}/{second}");
        response.prettyPrint();

        //1st Validation: response.then() method


        //2nd Validation: jsonPath()


        //3rd Validation: as() method


        //4th Validation as() method (By converting response to Map)


        //5th Validation-->  Map + Object Mapper


        //6th Validation--> Best Practice: Pojo Class + Object Mapper
        Country actualData =convertJsonToJava(response.asString(), Country.class);
        assertEquals(201,response.statusCode());
        assertEquals(payLoad.getName(),actualData.getName());
        assertEquals(payLoad.getStates().get(0).getId(),actualData.getStates().get(0).getId());
        assertEquals(state1.getId(),actualData.getStates().get(0).getId());
        assertEquals(state1.getName(),actualData.getStates().get(0).getName());
        assertEquals(state1.getTpcountry(),actualData.getStates().get(0).getTpcountry());

        assertEquals(state2.getId(),actualData.getStates().get(1).getId());
        assertEquals(state2.getName(),actualData.getStates().get(1).getName());
        assertEquals(state2.getTpcountry(),actualData.getStates().get(1).getTpcountry());

        assertEquals(state3.getId(),actualData.getStates().get(2).getId());
        assertEquals(state3.getName(),actualData.getStates().get(2).getName());
        assertEquals(state3.getTpcountry(),actualData.getStates().get(2).getTpcountry());




    }
}
