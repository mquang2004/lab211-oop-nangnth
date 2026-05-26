package utils;

import java.util.Scanner;

public class InputData {
    private static final Scanner SCANNER = new Scanner(System.in);

    public int inputInteger(String mess, String regex) {
        System.out.print(mess);
        while (true) {
            String number = SCANNER.nextLine().trim();
            if (number.matches(regex)) {
                return Integer.parseInt(number);
            }
            System.out.println("Invalid integer. Please enter again: ");
        }
    }
    
    public int inputIntegerInRange(String mess, String regex, int start, int end){
        System.out.print(mess);
        while(true){
            String string = SCANNER.nextLine().trim();
            if(string.matches(regex)){
                int number = Integer.parseInt(string);
                if(number >= start && number <= end){
                    return number;
                }
            }
            System.out.printf("Please input valid integer in range %d - %d: ", start, end);
        }
    }

    public String inputString(String mess, String regex) {
        System.out.print(mess);
        while (true) {
            String string = SCANNER.nextLine().trim();
            if (string.matches(regex) && !string.matches("\\s*")) {
                return string;
            }
            System.out.println("Please input valid string: ");
        }
    }
}
