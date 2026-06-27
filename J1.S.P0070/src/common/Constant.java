package common;

public class Constant {

    public static final String REG_MENU = "[1-3]";
    public static final String REG_ACCOUNT_NUMBER = "\\d{10}";
    public static final String REG_PASSWORD_LENGTH = ".{8,31}";
    public static final String REG_PASSWORD_LETTER = ".*[A-Za-z].*";
    public static final String REG_PASSWORD_DIGIT = ".*\\d.*";
    public static final String CAPTCHA_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public static final int CAPTCHA_LENGTH = 5;
}
