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

    @Test
    void 첫_납부일과_만료일_일자가_다를때_만원_납부하면_첫_납부일_기준으로_다음_만료일_정함() {
        // 재납부한 경우에는(첫 납부일 기록이 있으면) 첫 납부의 만료일을 기준으로 금액에 따라 만료일 지정
        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2025, 1, 31))
                        .billingDate(LocalDate.of(2025, 2, 28))
                        .payAmount(10000)
                        .build(),
                LocalDate.of(2025, 3, 31));
    }

    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(payData);
        assertEquals(expectedExpiryDate, realExpiryDate);
    }
}
