package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.HerokuPojo;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class Get10 extends HerOkuAppBaseUrl {
      /*
        Given
            https://restful-booker.herokuapp.com/booking/3622
        When
            I send GET Request to the url
        Then
            Response body should be like that;
            {
                "firstname": "Jane",
                "lastname": "Doe",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Extra pillows please"
            }
     */

    @Test
    public void get(){
        // Set Url
        spec.pathParams("first","booking"
        ,"second",41);

        // Set Expected Data:
        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01","2019-01-01");
        HerokuPojo expectedData = new HerokuPojo("Jane","Doe",111,true,bookingDates,"Extra pillows please");

        System.out.println("bookinDates = " + bookingDates);
        System.out.println("expectedData = " + expectedData);

        // Send Request And Get the Response:
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        // Do Assertions:
        HerokuPojo actualData = response.as(HerokuPojo.class);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(bookingDates.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());














    }
}
