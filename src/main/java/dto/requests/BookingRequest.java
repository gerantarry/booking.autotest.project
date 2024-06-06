package dto.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingRequest {
    private int id;
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDatesRequest bookingdates;
    private String additionalneeds;
}
