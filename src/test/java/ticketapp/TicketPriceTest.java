package ticketapp;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.*;

public class TicketPriceTest {

    @Test
    public void setPassengerTicketPrice() {
        TicketPrice ticketPrice = new TicketPrice();
        ticketPrice.setChild(false);
        ticketPrice.setPassengerTicketPrice(BigDecimal.ZERO, BigDecimal.ZERO);

        assertEquals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP), ticketPrice.getPassengerTicketPrice());

        ticketPrice.setPassengerTicketPrice(BigDecimal.valueOf(10), BigDecimal.valueOf(100));
        assertEquals(BigDecimal.valueOf(20).setScale(2, RoundingMode.HALF_UP), ticketPrice.getPassengerTicketPrice());

        ticketPrice.setChild(true);
        ticketPrice.setPassengerTicketPrice(BigDecimal.valueOf(10), BigDecimal.valueOf(100));
        assertEquals(BigDecimal.valueOf(10).setScale(2, RoundingMode.HALF_UP), ticketPrice.getPassengerTicketPrice());
    }

    @Test
    public void setLuggageTicketPrice() {
        TicketPrice ticketPrice = new TicketPrice();
        ticketPrice.setLuggageTicketPrice(BigDecimal.ZERO, BigDecimal.ZERO);
        ticketPrice.setLuggageItemCount(1);

        assertEquals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP), ticketPrice.getLuggageTicketPrice());

        ticketPrice.setLuggageTicketPrice(BigDecimal.valueOf(30), BigDecimal.valueOf(100));
        assertEquals(BigDecimal.valueOf(18).setScale(2, RoundingMode.HALF_UP), ticketPrice.getLuggageTicketPrice());

        ticketPrice.setLuggageItemCount(2);
        ticketPrice.setLuggageTicketPrice(BigDecimal.valueOf(30), BigDecimal.valueOf(100));
        assertEquals(BigDecimal.valueOf(36).setScale(2, RoundingMode.HALF_UP), ticketPrice.getLuggageTicketPrice());

    }

    @Test
    public void createTicketPriceFromPassenger() {
        Passenger passenger = new Passenger();
        passenger.setChild(true);
        passenger.setLuggageItemCount(1);

        TicketPrice result = TicketPrice.createTicketPriceFromPassenger(passenger, BigDecimal.ZERO, BigDecimal.ZERO);
        assertEquals(true, result.getChild());
        assertEquals(1, result.getLuggageItemCount());
        assertEquals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP), result.getPassengerTicketPrice());
        assertEquals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP), result.getLuggageTicketPrice());
    }
}