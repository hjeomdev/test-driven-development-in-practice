package chap03;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpiryDateCalculatorTest {
    @Test
    void 납부금_만원이면_만료일은_한달_뒤() {
        LocalDate billingDate = LocalDate.of(2025, 3, 1);
        int payAmount = 10000;

        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate expiryDate = cal.calculateExpiryDate(billingDate, payAmount);

        assertEquals(LocalDate.of(2025, 4, 1), expiryDate);

        LocalDate billingDate2 = LocalDate.of(2025, 5, 5);
        int payAmount2 = 10000;

        ExpiryDateCalculator cal2 = new ExpiryDateCalculator();
        LocalDate expiryDate2 = cal.calculateExpiryDate(billingDate2, payAmount2);

        assertEquals(LocalDate.of(2025, 6, 5), expiryDate2);
    }
}
