package view;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import model.Expense;

public class ViewExpense {
    public void printMenu(){
        System.out.print("""      
                           =======Handy Expense program======
                           1. Add an expense
                           2. Display all expenses
                           3. Delete an expense
                           4. Quit
                           """);
    }

    public void displayMess(String message) {
        System.out.println(message);
    }

    public void displayAll(List<Expense> list, double total) {
        if (list == null || list.isEmpty()) {
            displayMess("List is empty.");
            return;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        System.out.println("---------Display all expenses------------");
        System.out.printf("%-5s %-15s %-10s %-20s%n", "ID", "Date", "Amount", "Content");
        for (Expense expense : list) {
            System.out.printf("%-5d %-15s %-10s %-20s%n",
                    expense.getId(),
                    formatter.format(expense.getDate()),
                    formatAmount(expense.getNumber()),
                    expense.getContent());
        }
        System.out.println("Total: " + formatAmount(total));
    }

    private String formatAmount(double amount) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(amount);
    }
}
