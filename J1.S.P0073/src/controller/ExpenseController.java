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

    public void addExpense() {
        try {
            viewExpense.displayMess("-------- Add an expense--------");
            Date date = inputData.getDate("Enter Date: ", Constant.REG_DATE);
            double amount = inputData.inputDouble("Enter Amount: ", Constant.REG_AMOUNT);
            String content = inputData.getString("Enter Content: ", Constant.REG_CONTENT);

            expenseManager.addExpense(new Expense(0, date, amount, content));
        } catch (Exception e) {
            viewExpense.displayMess(e.getMessage());
        }
    }

    public void displayAll() {
        viewExpense.displayAll(expenseManager.getExpenses(), expenseManager.getTotal());
    }

    public void deleteExpense() {
        try {
            ArrayList<Expense> expenseList = expenseManager.getExpenses();
            if(expenseList.isEmpty()){
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
