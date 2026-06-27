package view;

public class ViewEbank {

    public void printMenu() {
        System.out.print("""
                           -------Login Program-------
                           1. Vietnamese
                           2. English
                           3. Exit
                           """);
    }

    public void displayMess(String message) {
        System.out.println(message);
    }
}
