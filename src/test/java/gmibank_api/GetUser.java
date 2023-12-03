package gmibank_api;

import base_urls.GMIBankBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.gmibank.Country;
import pojos.gmibank.RootPojo;
import pojos.gmibank.User;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class GetUser  extends GMIBankBaseUrl {
    /*

        Given
	       https://www.gmibank.com/api/tp-customers/110452
        When
	 		I send GET Request to the URL
	 	Then
	 		Status code is 200
	 		And response body is like {
                                        "id": 110452,
                                        "firstName": "Jasmine",
                                        "lastName": "Stehr",
                                        "middleInitial": "V",
                                        "email": "marni.zboncak@yahoo.com",
                                        "mobilePhoneNumber": "463-609-2097",
                                        "phoneNumber": "1-112-497-0270",
                                        "zipCode": "16525",
                                        "address": "14387 Al Ridge5343 Bert Burgs",
                                        "city": "Waltermouth",
                                        "ssn": "761-59-2911",
                                        "createDate": "2021-11-28T21:00:00Z",
                                        "zelleEnrolled": false,
                                        "country": {
                                                },
                                        "state": "California",
                                        "user": {},
                                        "accounts": []
                                    }

     */
    @Test
    public void get(){
        spec.pathParams("first","api"
                ,"second","tp-customers"
                ,"third",110452);

        User user = new User(110016,"leopoldo.reinger","Jasmine","Stehr","marni.zboncak@yahoo.com",true,"en",null,null);
        List<Object> accounts = new ArrayList<>();
        Country country = new Country(3,"USA",null);
        RootPojo expectedData = new RootPojo(110452,"Jasmine","Stehr","V","marni.zboncak@yahoo.com","463-609-2097","1-112-497-0270","16525","14387 Al Ridge5343 Bert Burgs","Waltermouth","761-59-2911","2021-11-28T21:00:00Z",false,country,"California",user,accounts);

        Response response = given(spec).when().get("{first}/{second}/{third}");

        response.prettyPrint();

        RootPojo actualData = convertJsonToJava(response.asString(), RootPojo.class);
        assertEquals(expectedData.getFirstName(),actualData.getFirstName());
        assertEquals(expectedData.getLastName(),actualData.getLastName());
        assertEquals(expectedData.getEmail(),actualData.getEmail());
        assertEquals(expectedData.getMobilePhoneNumber(),actualData.getMobilePhoneNumber());
        // Homework:

        assertEquals(expectedData.getCountry().getName(),actualData.getCountry().getName());



        assertEquals(user.getFirstName(),actualData.getUser().getFirstName());
        assertEquals(user.getLangKey(),actualData.getUser().getLangKey());
        assertEquals(user.getLogin(),actualData.getUser().getLogin());






















    }




}
