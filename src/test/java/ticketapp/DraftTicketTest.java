package ticketapp;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DraftTicketTest {

    @Test
    public void createDraftTicketFromTicketPriceList() {
        List<TicketPrice> ticketPriceList = new ArrayList<>();

        DraftTicket result = DraftTicket.createDraftTicketFromTicketPriceList(ticketPriceList);

        assertEquals("EUR", result.getCurrency());
        assertEquals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP), result.getTotalPrice());
        assertEquals(new ArrayList<>(), result.getTicketPriceList());

    }
}