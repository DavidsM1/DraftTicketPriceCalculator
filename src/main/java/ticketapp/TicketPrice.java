package ticketapp;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TicketPrice extends Passenger {
    final BigDecimal LUGGAGE_PRICE_DISCOUNT = new BigDecimal(30);
    final BigDecimal CHILD_DISCOUNT = new BigDecimal(50);
    final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    private BigDecimal passengerTicketPrice;
    private BigDecimal luggageTicketPrice;

    public BigDecimal getPassengerTicketPrice() {
        return passengerTicketPrice;
    }

    public BigDecimal getLuggageTicketPrice() {
        return luggageTicketPrice;
    }

    public void setPassengerTicketPrice(BigDecimal basePrice, BigDecimal taxRate) {
        BigDecimal passengerTicketPrice = new BigDecimal(0);

        // basePrice + (basePrice * taxRate / 100)
        passengerTicketPrice =  passengerTicketPrice.add(basePrice)
                                                    .multiply(taxRate)
                                                    .divide(ONE_HUNDRED, 2, RoundingMode.HALF_UP)
                                                    .add(basePrice);

        if (this.getChild()) {
            // passengerTicketPrice * childDiscount / 100
            passengerTicketPrice = passengerTicketPrice.multiply(CHILD_DISCOUNT).divide(ONE_HUNDRED, 2, RoundingMode.HALF_UP);
        }

        this.passengerTicketPrice = passengerTicketPrice.setScale(2, RoundingMode.HALF_UP);
    }

    public void setLuggageTicketPrice(BigDecimal basePrice, BigDecimal taxRate) {

        BigDecimal luggageTotalPrice = new BigDecimal(0);
        BigDecimal luggageTicketPrice = new BigDecimal(this.getLuggageItemCount());

        // basePrice * luggagePriceDiscount / 100
        luggageTicketPrice = luggageTicketPrice.multiply(basePrice)
                                               .multiply(LUGGAGE_PRICE_DISCOUNT)
                                               .divide(ONE_HUNDRED, 2, RoundingMode.HALF_UP);
        // luggageTicketPrice + (luggageTicketPrice * taxRate / 100)
        luggageTotalPrice = luggageTotalPrice.add(luggageTicketPrice)
                                             .multiply(taxRate)
                                             .divide(ONE_HUNDRED, 2, RoundingMode.HALF_UP)
                                             .add(luggageTicketPrice);

        this.luggageTicketPrice = luggageTotalPrice.setScale(2, RoundingMode.HALF_UP);
    }

    public static TicketPrice createTicketPriceFromPassenger(Passenger passenger, BigDecimal basePrice, BigDecimal taxRate) {
        TicketPrice ticketPrice = new TicketPrice();

        ticketPrice.setChild(passenger.getChild());
        ticketPrice.setLuggageItemCount(passenger.getLuggageItemCount());
        ticketPrice.setPassengerTicketPrice(basePrice, taxRate);
        ticketPrice.setLuggageTicketPrice(basePrice, taxRate);

        return ticketPrice;
    }
}