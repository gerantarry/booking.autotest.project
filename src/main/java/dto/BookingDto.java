package dto;

import lombok.Data;

@Data
public class BookingDto {
    private int id;
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDatesDto bookingDates;
    private String additionalneeds;

    @Data
    class BookingDatesDto {
        String checkin;
        String checkout;
    }

}
