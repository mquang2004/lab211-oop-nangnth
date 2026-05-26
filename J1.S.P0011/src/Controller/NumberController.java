package Controller;

import Business.NumberBusiness;
import Common.Constant;
import Model.Number;
import View.InputData;
import View.NumberView;

public class NumberController {

    private final NumberBusiness numberBusiness = new NumberBusiness();
    private final NumberView numberView = new NumberView();
    private final InputData inputData = new InputData();

    public void run() {
        while (true) {
            int inputBase = inputBase("Choose input base:");
            int outputBase = inputBase("Choose output base:");
            String inputValue = inputData.inputString("Enter input value: ", regexByBase(inputBase));

            Number number = new Number(inputBase, inputValue);
            String result = numberBusiness.convert(number, outputBase);
            numberView.displayResult(number.getValue(), inputBase, result, outputBase);
        }
    }

    private int inputBase(String message) {
        numberView.printMenu(message);
        int option = inputData.inputNumber("Choose option: ", Constant.REGEX_MENU_BASE);
        return optionToBase(option);
    }

    private int optionToBase(int option) {
        switch (option) {
            case 1:
                return 2;
            case 2:
                return 10;
            default:
                return 16;
        }
    }

    private String regexByBase(int base) {
        switch (base) {
            case 2:
                return Constant.REGEX_BINARY;
            case 10:
                return Constant.REGEX_DECIMAL;
            default:
                return Constant.REGEX_HEXADECIMAL;
        }
    }
}
