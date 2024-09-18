package uz.developer.magistratura_ishi.validators.dataClass;

import java.util.regex.Pattern;

public class EmailOrPhoneValidator {

    public static boolean isPhoneFormat(String strNum) {
        if (strNum == null) {
            return false;
        }
        if (!(strNum.startsWith("998") || strNum.startsWith("+998"))) {
            return false;
        }
        if (!(strNum.length() == 12 || strNum.length() == 13)) {
            return false;
        }

        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isEmailFormat(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
