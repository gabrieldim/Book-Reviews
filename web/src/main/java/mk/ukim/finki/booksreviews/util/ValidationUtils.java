package mk.ukim.finki.booksreviews.util;

import java.util.regex.Pattern;

public class ValidationUtils {

    private final static String EMAIL_REGEX_PATTERN = "^(.+)@(\\S+)$";
    private final static String PASSWORD_REGEX_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–{}:;',?/*~$^+=<>]).{5,30}$";

    public static Boolean isValidEmailAddress(String emailAddress) {
        return Pattern.compile(EMAIL_REGEX_PATTERN)
                .matcher(emailAddress)
                .matches();
    }

    public static Boolean isValidPassword(String password) {
        return Pattern.compile(PASSWORD_REGEX_PATTERN)
                .matcher(password)
                .matches();
    }

    public static Boolean isValidUser(String emailAddress, String password) {
        return isValidEmailAddress(emailAddress) && isValidPassword(password);
    }
}
