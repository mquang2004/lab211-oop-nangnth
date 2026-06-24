package controller;

import bo.ExpenseManager;
import common.Constant;
import java.util.ArrayList;
import java.util.Date;
import model.Expense;
import utils.InputData;
import view.ViewExpense;

public class ExpenseController {

    private final ExpenseManager expenseManager = new ExpenseManager();
    private final InputData inputData = new InputData();
    private final ViewExpense viewExpense = new ViewExpense();

    public void addExpense() throws Exception {
        while (true) {
            Date date = null;
            double amount = 0;
            String content = null;
            Expense expense = null;

            try {
                viewExpense.displayMess("-------- Add an expense--------");
                date = inputData.getDate("Enter Date: ", Constant.REG_DATE);
                amount = inputData.inputDouble("Enter Amount: ", Constant.REG_AMOUNT);
                content = inputData.getString("Enter Content: ", Constant.REG_CONTENT);
                expense = new Expense(date, amount, content);

                if (expenseManager.countDuplicate(date, amount, content) >= 1) {
                    String choices = inputData.getString("Expense is existed. Do you want to create another (Y/N): ", Constant.REG_YN);
                    if (choices.equalsIgnoreCase("N")) {
                        expenseManager.deleteExpense(expense.getId());
                        break;
                    }
                }

                expenseManager.addExpense(expense);
                viewExpense.displayMess("Add expense successfully.");
                break;
            } catch (Exception e) {
                viewExpense.displayMess(e.getMessage());
            }
        }
    }

    public void displayAll() {
        viewExpense.displayAll(expenseManager.getExpenses(), expenseManager.getTotal());
    }

    public void deleteExpense() {
        try {
            ArrayList<Expense> expenseList = expenseManager.getExpenses();
            if (expenseList.isEmpty()) {
                viewExpense.displayMess("Expense list is empty.");
                return;
            }

            viewExpense.displayMess("--------Delete an expense------");
            viewExpense.displayAll(expenseManager.getExpenses(), expenseManager.getTotal());
            int id = inputData.inputInteger("Enter ID: ", Constant.REG_ID);
            expenseManager.deleteExpense(id);
            viewExpense.displayMess("Expense deleted successfully.");
        } catch (Exception e) {
            viewExpense.displayMess(e.getMessage());
        }
    }
}
