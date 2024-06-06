package base.checks;

import dto.requests.BookingRequest;
import org.junit.jupiter.api.Assertions;

public class BookingChecks {
    public static void checkBookings(BookingRequest expected, BookingRequest actual){
        Assertions.assertAll(
                () -> {
                    Assertions.assertEquals(expected.getFirstname(), actual.getFirstname(), "Wrong Firstname");
                    Assertions.assertEquals(expected.getLastname(), actual.getLastname(), "Wrong Lastname");
                    Assertions.assertEquals(expected.getTotalprice(), actual.getTotalprice(), "Wrong total price");
                    Assertions.assertEquals(expected.getAdditionalneeds(), actual.getAdditionalneeds(), "Wrong additional needs");
                    Assertions.assertEquals(expected.getBookingdates().getCheckin(), actual.getBookingdates().getCheckin(), "Wrong checkin date");
                    Assertions.assertEquals(expected.getBookingdates().getCheckout(), actual.getBookingdates().getCheckout(), "Wrong checkout date");
                }
        );
    }
}
