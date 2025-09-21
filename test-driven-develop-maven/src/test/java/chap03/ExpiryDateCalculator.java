package chap03;

import java.time.LocalDate;

public class ExpiryDateCalculator {
    LocalDate calculateExpiryDate(PayData payData) {
        if(payData.getFirstBillingDate() != null) {
            LocalDate candidateExp = payData.getBillingDate().plusMonths(1); // 윤달인 경우, 28일이 더해짐.
            if (payData.getFirstBillingDate().getDayOfMonth() != candidateExp.getDayOfMonth()) {
                return candidateExp.withDayOfMonth(payData.getFirstBillingDate().getDayOfMonth());
            }
        }
        return payData.getBillingDate().plusMonths(1);
    }
}
