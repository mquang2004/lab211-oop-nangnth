package utils;

import java.util.Scanner;

public class InputData {

    private static final Scanner SCANNER = new Scanner(System.in);

    public String getString(String message) {
        System.out.print(message);
        return SCANNER.nextLine().trim();
    }

    public int inputInteger(String message, String regex) {
        System.out.print(message);
        while (true) {
            String number = SCANNER.nextLine().trim();
            if (number.matches(regex)) {
                return Integer.parseInt(number);
            }
            System.out.println("Please input valid integer.");
            System.out.print(message);
        }
    }
}
