package post_request;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.HerokuPojo;
import pojos.HerokuResponsePojo;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class Post05 extends HerOkuAppBaseUrl {
            /*
   Given
    1)  https://restful-booker.herokuapp.com/booking
    2)   {
          "firstname": "John",
          "lastname": "Doe",
          "totalprice": 999,
          "depositpaid": true,
          "bookingdates": {
              "checkin": "2021-09-21",
              "checkout": "2021-12-21"
           },
           "additionalneeds": "Breakfast"
          }
  When
       I send POST Request to the URL
   Then
       Status code is 200
   And
       Response body is like {
                               "bookingid": 4319,
                               "booking" :{
                                  "firstname": "John",
                                  "lastname": "Doe",
                                  "totalprice": 999,
                                  "depositpaid": true,
                                  "bookingdates": {
                                      "checkin": "2021-09-21",
                                      "checkout": "2021-12-21"
                                  },
                                  "additionalneeds": "Breakfast"
                               }
                            }
*/
    @Test
    public void post(){
        // Set Url
        spec.pathParam("first","booking");

        // Set Expected Data:
       BookingDatesPojo bookingDates = new BookingDatesPojo("2021-09-21","2021-12-21");
       HerokuPojo payLoad = new HerokuPojo("John","Doe",999,true,bookingDates,"Breakfast");

        // Send Request and Get Response:
        Response response = given(spec).body(payLoad).when().post("{first}");

        // Do Assertion:
        HerokuResponsePojo actualData = response.as(HerokuResponsePojo.class);
        assertEquals(200, response.statusCode());
        assertEquals(payLoad.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(payLoad.getLastname(),actualData.getBooking().getLastname());
        assertEquals(payLoad.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(payLoad.getDepositpaid(),actualData.getBooking().getDepositpaid());
        assertEquals(payLoad.getBookingdates().getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(),actualData.getBooking().getBookingdates().getCheckout());























    }

}
