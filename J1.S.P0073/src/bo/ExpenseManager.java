package bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Expense;

public class ExpenseManager {
    private final ArrayList<Expense> expenseList;
    private int expenseId = 1;

    public ExpenseManager() {
        expenseList = new ArrayList<>();
    }

    public int getSize() {
        return expenseList.size();
    }

    public ArrayList<Expense> getExpenses() {
        return new ArrayList<>(expenseList);
    }
    
    public void addExpense(Expense expense) throws Exception{
        if(expense == null){
            throw new Exception("Expense is invalid.");
        }
        if(isIdExisted(expense.getId())){
            throw new Exception("Id is existed.");
        }
        
        expense.setId(expenseId++);
        expenseList.add(expense);
    }
    
    public boolean isIdExisted(int id){
        for (Expense expense : expenseList) {
            if(expense.getId() == id){
                return true;
            }
        }
        return false;
    }

    public Expense findExpenseById(int id) {
        for (Expense expense : expenseList) {
            if (expense.getId() == id) {
                return expense;
            }
        }
        return null;
    }

    public void deleteExpense(int id) throws Exception {
        Expense expense = findExpenseById(id);
        if(!isIdExisted(id)){
            throw new Exception("Expense is not exist.");
        }
        expenseList.remove(expense);
    }

    public double getTotal() {
        double total = 0;
        if (expenseList == null) {
            return total;
        }
        for (Expense expense : expenseList) {
            total += expense.getNumber();
        }
        return total;
    }
}
