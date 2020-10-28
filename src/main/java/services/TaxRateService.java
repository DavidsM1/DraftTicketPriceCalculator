package services;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TaxRateService {

    public static BigDecimal getTaxRate(LocalDate date) {
        if (date.isEqual(LocalDate.now())) {
            return BigDecimal.valueOf(21);
        }
        return BigDecimal.valueOf(17);
    }
}
