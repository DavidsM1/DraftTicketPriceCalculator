package services;

import java.math.BigDecimal;

public class BasePriceService {

    public static BigDecimal getBasePrice(String busTerminalName) {

        return switch (busTerminalName) {
            case "Vilnius" -> BigDecimal.TEN;
            case "Riga" -> BigDecimal.valueOf(12);
            default -> BigDecimal.ONE;
        };
    }

}
