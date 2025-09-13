package chap02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String s){
        if (s == null || s.isEmpty()) {
            return PasswordStrength.INVALID;
        }
        boolean lengthEnough = s.length() >= 8;
        boolean containsUpp = meetsContainingUppercaseCriteria(s);
        boolean containsNum = meetsContainingNumberCriteria(s);
        if((lengthEnough && !containsUpp && !containsNum)
            || (!lengthEnough && !containsUpp && containsNum)
            || (!lengthEnough && containsUpp && !containsNum)){
            return PasswordStrength.WEAK;
        }
        if (!containsNum || !containsUpp || !lengthEnough) {
            return PasswordStrength.NORMAL;
        }

        return PasswordStrength.STRONG;
    }

    private boolean meetsContainingUppercaseCriteria(String s) {
        for (char ch : s.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }

    private boolean meetsContainingNumberCriteria(String s) {
        for (char ch : s.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }
}
