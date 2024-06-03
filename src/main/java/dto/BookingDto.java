package dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingDto {
    private int id;
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDatesDto bookingdates;
    private String additionalneeds;
}
