package view;

import java.util.Scanner;

public class InputData {
    private static final Scanner SCANNER = new Scanner(System.in);

    public int inputInteger(String mess, String regex) {
        System.out.println(mess);
        while (true) {
            String number = SCANNER.nextLine().trim();
            if (number.matches(regex)) {
                return Integer.parseInt(number);
            }
            System.out.println("Please input valid integer.");
        }
    }

    public String inputString(String mess, String regex) {
        System.out.println(mess);
        while (true) {
            String string = SCANNER.nextLine().trim();
            if (string.matches(regex) && !string.matches("\\s*")) {
                return string;
            }
            System.out.println("Please input valid string.");
        }
    }
}
