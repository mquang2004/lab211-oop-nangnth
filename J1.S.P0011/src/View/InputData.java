package View;

import java.util.Scanner;

public class InputData {

    private static final Scanner sc = new Scanner(System.in);

    public int inputNumber(String message, String regex) {
        while (true) {
            System.out.print(message);
            String value = inputLine();
            if (value.matches(regex)) {
                return Integer.parseInt(value);
            } else {
                System.out.println("Input is invalid.");
            }
        }
    }

    public String inputString(String message, String regex) {
        while (true) {
            System.out.print(message);
            String value = inputLine();
            if (value.matches(regex)) {
                return value;
            } else {
                System.out.println("Input is invalid.");
            }
        }
    }

    private String inputLine() {
        if (!sc.hasNextLine()) {
            System.exit(0);
        }
        return sc.nextLine().trim();
    }
}
