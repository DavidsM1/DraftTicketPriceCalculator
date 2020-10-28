package ticketapp;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DraftTicketPriceCalculatorTest {

    @Test
    public final void testGivenExampleCase() {
        Passenger adult = new Passenger();
        adult.setChild(false);
        adult.setLuggageItemCount(2);

        Passenger child = new Passenger();
        child.setChild(true);
        child.setLuggageItemCount(1);

        List<Passenger> passengerList = new ArrayList<>();
        passengerList.add(adult);
        passengerList.add(child);

        String busTerminalName = "Vilnius";

        DraftTicket draftTicket = DraftTicketPriceCalculator.calculateDraftTicketPrice(passengerList, busTerminalName);

        // check object currency and total price
        assertEquals("EUR", draftTicket.getCurrency());
        assertEquals(BigDecimal.valueOf(29.04), draftTicket.getTotalPrice());

        // check adult passenger ticket info
        assertEquals(false, draftTicket.getTicketPriceList().get(0).getChild());
        assertEquals(2, draftTicket.getTicketPriceList().get(0).getLuggageItemCount());
        assertEquals(BigDecimal.valueOf(12.1).setScale(2, RoundingMode.HALF_UP), draftTicket.getTicketPriceList().get(0).getPassengerTicketPrice());
        assertEquals(BigDecimal.valueOf(7.26), draftTicket.getTicketPriceList().get(0).getLuggageTicketPrice());

        // check child passenger ticket info
        assertEquals(true, draftTicket.getTicketPriceList().get(1).getChild());
        assertEquals(1, draftTicket.getTicketPriceList().get(1).getLuggageItemCount());
        assertEquals(BigDecimal.valueOf(6.05), draftTicket.getTicketPriceList().get(1).getPassengerTicketPrice());
        assertEquals(BigDecimal.valueOf(3.63), draftTicket.getTicketPriceList().get(1).getLuggageTicketPrice());
    }

    @Test(expected = IllegalStateException.class)
    public final void testEmptyListCase() {
        List<Passenger> passengerList = new ArrayList<>();
        DraftTicketPriceCalculator.calculateDraftTicketPrice(passengerList, "Vilnius");
    }
}