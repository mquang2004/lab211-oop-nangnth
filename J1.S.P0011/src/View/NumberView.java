package View;

public class NumberView {

    public void printMenu(String message) {
        System.out.println(message);
        System.out.println("1. Binary");
        System.out.println("2. Decimal");
        System.out.println("3. Hexadecimal");
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayResult(String inputValue, int inputBase, String result, int outputBase) {
        System.out.println("Result: " + inputValue + " (" + inputBase + ") = "
                + result + " (" + outputBase + ")");
        System.out.println();
    }
}
