
import dto.BookingIdsDto;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ResponseUtils;

import java.util.ArrayList;
import java.util.List;

import static constants.UriConstants.*;

public class BookingTest extends TestBase {

    @Test
    @DisplayName("Get booking ids")
    public void getBookingIds() {
        Response response = httpManager.get(BOOKING);
        List<BookingIdsDto> bookingIds = ResponseUtils.extractBodyTo(response, new ArrayList<BookingIdsDto>());
        //TODO Cannot parse object because no JSON deserializer found in classpath. Please put either Jackson (Databind) or Gson in the classpath.

    }

}
