package controller;

import bo.Ebank;
import java.util.Locale;
import utils.InputData;
import view.ViewEbank;

public class EbankController {

    private final Ebank ebank = new Ebank();
    private final InputData inputData = new InputData();
    private final ViewEbank viewEbank = new ViewEbank();

    public void setLanguage(int choice) {
        if (choice == 1) {
            ebank.setLocale(new Locale("vi", "VN"));
        } else {
            ebank.setLocale(Locale.ENGLISH);
        }
    }

    public void login() {
        inputAccountNumber();
        inputPassword();
        inputCaptcha();
    }

    private void inputAccountNumber() {
        while (true) {
            String accountNumber = inputData.getString(ebank.getMessage("account.prompt"));
            String message = ebank.checkAccountNumber(accountNumber);
            if (message.isEmpty()) {
                return;
            }
            viewEbank.displayMess(message);
        }
    }

    private void inputPassword() {
        while (true) {
            String password = inputData.getString(ebank.getMessage("password.prompt"));
            String message = ebank.checkPassword(password);
            if (message.isEmpty()) {
                return;
            }
            viewEbank.displayMess(message);
        }
    }

    private void inputCaptcha() {
        while (true) {
            String captcha = ebank.generateCaptcha();
            viewEbank.displayMess(ebank.getMessage("captcha.prompt") + captcha);
            String captchaInput = inputData.getString(ebank.getMessage("captcha.input"));
            String message = ebank.checkCaptcha(captchaInput, captcha);
            if (message.isEmpty()) {
                return;
            }
            viewEbank.displayMess(message);
        }
    }
}
