package ticketapp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class DraftTicket {

    private List<TicketPrice> ticketPriceList;
    private BigDecimal totalPrice;
    private String currency;

    public DraftTicket() {
        this.currency = "EUR";
    }

    public List<TicketPrice> getTicketPriceList() {
        return ticketPriceList;
    }

    public void setTicketPriceList(List<TicketPrice> ticketPriceList) {
        this.ticketPriceList = ticketPriceList;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public static DraftTicket createDraftTicketFromTicketPriceList(List<TicketPrice> ticketPriceList) {
        DraftTicket draftTicket = new DraftTicket();
        BigDecimal total = new BigDecimal(0);

        for (TicketPrice ticketPrice : ticketPriceList) {
            total = total.add(ticketPrice.getPassengerTicketPrice()).add(ticketPrice.getLuggageTicketPrice());
        }

        total = total.setScale(2, RoundingMode.HALF_UP);

        draftTicket.setTicketPriceList(ticketPriceList);
        draftTicket.setTotalPrice(total);

        return draftTicket;
    }
}
