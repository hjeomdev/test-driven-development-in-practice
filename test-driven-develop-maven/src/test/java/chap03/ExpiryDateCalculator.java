package chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {
    LocalDate calculateExpiryDate(PayData payData) {
        int addedMonths = payData.getPayAmount() / 10000;

        if(payData.getFirstBillingDate() != null) {
            return expiryDateUsingFirstBillingDate(payData, addedMonths);
        } else {
            return payData.getBillingDate().plusMonths(addedMonths);
        }
    }

    private LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addedMonths) {
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths); // 윤달인 경우, 28일이 더해짐.

        final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();

        if (isNotSameDayOfMonth(payData.getFirstBillingDate(), candidateExp)) {

            final int dateLenOfCandiMon = lastDayOfMonth(candidateExp);

            if (dateLenOfCandiMon < dayOfFirstBilling) {
                return candidateExp.withDayOfMonth(dateLenOfCandiMon);
            }
            return candidateExp.withDayOfMonth(dayOfFirstBilling);
        }
        return candidateExp;
    }

    private boolean isNotSameDayOfMonth(LocalDate firstBillingDate, LocalDate candidateExp) {
        return firstBillingDate.getDayOfMonth() != candidateExp.getDayOfMonth();
    }

    private int lastDayOfMonth(LocalDate date) {
        return YearMonth.from(date).lengthOfMonth();
    }
}
