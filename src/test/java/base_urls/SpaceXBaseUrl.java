package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class SpaceXBaseUrl {

    protected RequestSpecification spec;
   @Before  // This method will work before @Test methods
    public void setUp(){
       String baseUrl = "https://api.spacexdata.com/v3";
       spec = new RequestSpecBuilder().setBaseUri(baseUrl).setContentType(ContentType.JSON).build();

   }

}
