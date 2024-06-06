package booking;

import base.checks.BookingChecks;
import base.checks.CommonChecks;
import base.TestBase;
import dto.requests.BookingRequest;
import dto.responses.BookingIdsResponse;
import dto.responses.CreateTokenResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import providers.BookingsDtoProvider;

import java.util.HashMap;
import java.util.List;

import static constants.UriConstants.*;
import static utils.ResponseUtils.*;

public class PositiveBookingTest extends TestBase {
//setUp - взять токен

    @BeforeAll
    public static void setUp() {

    }

    @Test
    @DisplayName("Create booking")
    public void createBookingShouldBeOk() {
        BookingRequest expectedBooking = BookingsDtoProvider.getRandomBookingDto();
        Response createResponse = httpManager.post(BOOKING_URI, expectedBooking);
        CommonChecks.statusCodeOK(createResponse);
        expectedBooking.setId(
                extractBookingId(createResponse)
        );


        Response getResponse = httpManager.get(BOOKING_ID_URI, expectedBooking.getId());
        CommonChecks.statusCodeOK(createResponse);
        BookingRequest actualBooking = extractBodyTo(getResponse, BookingRequest.class);
        BookingChecks.checkBookings(expectedBooking, actualBooking);
    }

    @Test
    @DisplayName("Delete existing booking")
    public void deleteBookingShouldBeOk() {
        BookingRequest booking = BookingsDtoProvider.getRandomBookingDto();
        Response createResponse = httpManager.post(BOOKING_URI, booking);
        CommonChecks.statusCodeOK(createResponse);
        int id = extractBookingId(createResponse);

        Response tokenResponse = httpManager.authorize();
        CreateTokenResponse tokenDto = extractBodyTo(tokenResponse, CreateTokenResponse.class);
        httpManager.setCookies("token", tokenDto.getToken());

        Response response = httpManager.delete(BOOKING_ID_URI, id);
        CommonChecks.statusCodeCreate(response);

        Response getResponse = httpManager.get(BOOKING_ID_URI, id);
        CommonChecks.statusCodeNotFound(getResponse);
    }

    @Test
    @DisplayName("Add booking and get booking ids")
    public void addBookingAndGetBookingIdsShouldBeOk() {
        BookingRequest booking = BookingsDtoProvider.getRandomBookingDto();
        Response createResponse = httpManager.post(BOOKING_URI, booking);
        CommonChecks.statusCodeOK(createResponse);
        int id = extractBookingId(createResponse);

        Response response = httpManager.get(BOOKING_URI);
        List<BookingIdsResponse> bookingIds = extractBodyAsList(response, BookingIdsResponse.class);

        Assertions.assertNotEquals(0, bookingIds.size());
        long rowsCount = bookingIds.stream().filter(n -> n.getBookingid() == id).count();
        Assertions.assertEquals(1, rowsCount);
    }


    @Test
    @DisplayName("Get booking")
    public void getBooking() {
        HashMap<String, Object> pathParams = new HashMap<>() {{
            put("id", 1);
        }};
        Response response = httpManager.get(BOOKING_ID_URI, null, pathParams);
        BookingRequest bookingDto = extractBodyTo(response, BookingRequest.class);

        Assertions.assertAll(
                () -> {
                    Assertions.assertEquals("Sally", bookingDto.getFirstname());
                    Assertions.assertEquals("Jackson", bookingDto.getLastname());
                    Assertions.assertEquals(795, bookingDto.getTotalprice());
                }
        );
    }

    private int extractBookingId(Response response){
        return Integer.parseInt(
                extractValueFromBodyAsString(response, "bookingid")
        );
    }

}
