package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class GoRestBaseUrl {

    protected RequestSpecification spec;
   @Before  // This method will work before @Test methods
    public void setUp(){
       String baseUrl = "https://gorest.co.in/public/v1";
       spec = new RequestSpecBuilder().setBaseUri(baseUrl).setContentType(ContentType.JSON).build();

   }

}
