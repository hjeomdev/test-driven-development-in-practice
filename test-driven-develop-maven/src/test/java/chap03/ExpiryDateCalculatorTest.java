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
    void 납부일과_만료일_일자가_같지_않음() {
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

        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2025, 5, 31))
                        .billingDate(LocalDate.of(2025, 6, 30))
                        .payAmount(10000)
                        .build(),
                LocalDate.of(2025, 7, 31));
    }

    @Test
    void 이만원_이상_납부하는_케이스() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2025, 3, 1))
                        .payAmount(20000)
                        .build(),
                LocalDate.of(2025, 5, 1));

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2025, 3, 1))
                        .payAmount(30000)
                        .build(),
                LocalDate.of(2025, 6, 1));

    }

    @Test
    void 첫_납부일과_만료일_일자가_다를때_이만원_이상_납부() {
        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2025, 1, 31))
                        .billingDate(LocalDate.of(2025, 2, 28))
                        .payAmount(20000)
                        .build(),
                LocalDate.of(2025, 4, 30));

        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2025, 1, 31))
                        .billingDate(LocalDate.of(2025, 2, 28))
                        .payAmount(40000)
                        .build(),
                LocalDate.of(2025, 6, 30));

        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2025, 3, 31))
                        .billingDate(LocalDate.of(2025, 4, 30))
                        .payAmount(30000)
                        .build(),
                LocalDate.of(2025, 7, 31));
    }

    @Test
    void 십만원을_납부하면_1년_제공() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2025, 1, 28))
                        .payAmount(100000)
                        .build(),
                LocalDate.of(2026, 1, 28));

//        assertExpiryDate(
//                PayData.builder()
//                        .billingDate(LocalDate.of(2028, 2, 29))
//                        .payAmount(100000)
//                        .build(),
//                LocalDate.of(2029, 3, 1));
    }

    @Test
    void 십만원_이상을_납부하면_1년_제공와_이후_서비스제공() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2025, 1, 28))
                        .payAmount(130000)
                        .build(),
                LocalDate.of(2026, 4, 28));
    }

    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(payData);
        assertEquals(expectedExpiryDate, realExpiryDate);
    }
}
