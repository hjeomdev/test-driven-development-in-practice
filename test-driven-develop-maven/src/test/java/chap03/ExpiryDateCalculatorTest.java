package chap03;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpiryDateCalculatorTest {
    @Test
    void 납부금_만원이면_만료일은_한달_뒤() {
        assertExpiryDate(
                PayData.builder()
                    .billingDate(LocalDate.of(2025, 3, 1))
                    .payAmount(10000)
                    .build(),
                LocalDate.of(2025, 4, 1));

        assertExpiryDate(
                PayData.builder()
                    .billingDate(LocalDate.of(2025, 5, 5))
                    .payAmount( 10000)
                    .build(),
                LocalDate.of(2025, 6, 5));
    }

    @Test
    void 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpiryDate(
                PayData.builder()
                    .billingDate(LocalDate.of(2025, 1, 31))
                    .payAmount(10000)
                    .build(),
                LocalDate.of(2025, 2, 28));
    }

    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(payData);
        assertEquals(expectedExpiryDate, realExpiryDate);
    }
}
