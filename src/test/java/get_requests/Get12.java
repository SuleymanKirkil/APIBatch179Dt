package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertFalse;

public class Get12 extends GoRestBaseUrl {
                 /*
        Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 10
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 10
        And
            We have at least one "active" status
        And
            "The Hon. Sarisha Gandhi", "Anasuya Khatri", "Chakravartee Gandhi MD" are among the users
        And
            The female users are greater than or equals to male users
    */
    @Test
    public void get(){
        spec.pathParam("first","users");

        Response response = given(spec).when().get("{first}");
        response.prettyPrint();


        //  The value of "pagination limit" is 10
        int limit = 10;
        JsonPath json = response.jsonPath();
        int paginationLimit = json.getInt("meta.pagination.limit");
        assertEquals(limit,paginationLimit);

        //  The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        String expLink = "https://gorest.co.in/public/v1/users?page=1";
        String currentLink = json.getString("meta.pagination.links.current");
        assertEquals(expLink,currentLink);

       // The number of users should  be 10
        int expUsers = 10;
        int numberOfUsers =json.getList("data.name").size();
        assertEquals(expUsers,numberOfUsers);

        response.then()
                .body("data.name",hasSize(expUsers));

        //  We have at least one "active" status
        assertTrue(json.getList("data.status").contains("active"));
        assertFalse(json.getList("data.findAll{it.status=='active'}").isEmpty());

       //    "The Hon. Sarisha Gandhi", "Anasuya Khatri", "Chakravartee Gandhi MD" are among the users
        response.then()
                .body("data.name",hasItems("Chandrabhan Mishra", "Ekadant Khatri", "Miss Ganapati Sethi"));

        //  The female users are greater than or equals to male users
        int femaleUsers = json.getList("data.findAll{it.gender == 'female'}").size();
        assertTrue(femaleUsers >= (numberOfUsers-femaleUsers));



    }
}
