package bo;

import common.Constant;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

public class Ebank {

    private Locale locale = Locale.ENGLISH;
    private ResourceBundle bundle = ResourceBundle.getBundle("language.En", locale);
    private final Random random = new Random();

    public void setLocale(Locale locale) {
        this.locale = locale;
        if ("vi".equalsIgnoreCase(locale.getLanguage())) {
            bundle = ResourceBundle.getBundle("language.Vi", locale);
        } else {
            bundle = ResourceBundle.getBundle("language.En", locale);
        }
    }

    public Locale getLocale() {
        return locale;
    }

    public String getMessage(String key) {
        return bundle.getString(key);
    }

    public String checkAccountNumber(String accountNumber) {
        if (accountNumber != null && accountNumber.matches(Constant.REG_ACCOUNT_NUMBER)) {
            return "";
        }
        return getMessage("account.error");
    }

    public String checkPassword(String password) {
        if (password != null
                && password.matches(Constant.REG_PASSWORD_LENGTH)
                && password.matches(Constant.REG_PASSWORD_LETTER)
                && password.matches(Constant.REG_PASSWORD_DIGIT)
                && password.matches(Constant.REG_PASSWORD_NO_WHITESPACE)) {
            return "";
        }
        return getMessage("password.error");
    }

    public String generateCaptcha() {
        StringBuilder captcha = new StringBuilder();
        for (int i = 0; i < Constant.CAPTCHA_LENGTH; i++) {
            int index = random.nextInt(Constant.CAPTCHA_CHARS.length());
            captcha.append(Constant.CAPTCHA_CHARS.charAt(index));
        }
        return captcha.toString();
    }

    public String checkCaptcha(String captchaInput, String captchaGenerate) {
        if (captchaInput == null || captchaInput.isEmpty()) {
            return getMessage("captcha.error");
        }

        for (int i = 0; i < captchaInput.length(); i++) {
            if (captchaGenerate.indexOf(captchaInput.charAt(i)) == -1) {
                return getMessage("captcha.error");
            }
        }
        return "";
    }
}
