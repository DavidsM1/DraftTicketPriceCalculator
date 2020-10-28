package ticketapp;

import services.BasePriceService;
import services.TaxRateService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DraftTicketPriceCalculator {

    static DraftTicket calculateDraftTicketPrice(List<Passenger> passengerList, String busTerminalName) {

        if (passengerList.isEmpty()) {
            throw new IllegalStateException("Passenger list is empty");
        }

        BigDecimal basePrice = BasePriceService.getBasePrice(busTerminalName);
        BigDecimal taxRate = TaxRateService.getTaxRate(LocalDate.now());

        List<TicketPrice> ticketPriceList = new ArrayList<>();

        for (Passenger passenger : passengerList) {
            TicketPrice ticketPrice = TicketPrice.createTicketPriceFromPassenger(passenger, basePrice, taxRate);
            ticketPriceList.add(ticketPrice);
        }

        return DraftTicket.createDraftTicketFromTicketPriceList(ticketPriceList);
    }
}
