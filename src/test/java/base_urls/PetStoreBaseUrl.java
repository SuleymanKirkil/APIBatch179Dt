package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class PetStoreBaseUrl {
    protected RequestSpecification spec;
    @Before  // "Before" makes the method to be executed befare every @Test method
    public void setUp(){
        String baseUrl = "https://petstore.swagger.io/v2";

        spec = new RequestSpecBuilder().setBaseUri(baseUrl).build();
    }
}
