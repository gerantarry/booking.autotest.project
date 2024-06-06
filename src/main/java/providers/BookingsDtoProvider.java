package providers;

import com.github.javafaker.Faker;
import dto.requests.BookingDatesRequest;
import dto.requests.BookingRequest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class BookingsDtoProvider {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static BookingRequest getRandomBookingDto(){
        LocalDate checkin = LocalDate.now();
        checkin = checkin.plusDays(3);
        LocalDate checkout = checkin.plusWeeks(1);

        Faker faker = new Faker(Locale.US);

        return BookingRequest.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .depositpaid(faker.bool().bool())
                .totalprice(faker.number().numberBetween(10, 1500))
                .bookingdates(BookingDatesRequest.builder()
                        .checkin(formatter.format(checkin))
                        .checkout(formatter.format(checkout))
                        .build())
                .additionalneeds(faker.food().dish()).build();
    }
}
