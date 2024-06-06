package dto.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingDatesRequest {
    String checkin;
    String checkout;
}
