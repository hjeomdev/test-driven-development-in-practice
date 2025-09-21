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
    }
}
