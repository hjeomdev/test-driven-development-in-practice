package chap03;

import java.time.LocalDate;

public class ExpiryDateCalculator {
    LocalDate calculateExpiryDate(PayData payData) {
        return payData.getBillingDate().plusMonths(1);
    }
}
