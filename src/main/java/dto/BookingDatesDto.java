package dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingDatesDto {
    String checkin;
    String checkout;
}
