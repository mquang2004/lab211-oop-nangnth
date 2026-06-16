
package j1.s.p0073;

import common.Constant;
import controller.ExpenseController;
import utils.InputData;
import view.ViewExpense;

public class J1SP0073 {

    public static void main(String[] args) {
        ViewExpense view = new ViewExpense();
        ExpenseController controller = new ExpenseController();
        InputData input = new InputData();

        while (true) {
            view.printMenu();
            int choose = input.inputInteger("Your choice: ", Constant.REG_MENU_OPTION);
            switch (choose) {
                case 1:
                    controller.addExpense();
                    break;
                case 2:
                    controller.displayAll();
                    break;
                case 3:
                    controller.deleteExpense();
                    break;
                default:
                    return;
            }
        }
    }
    
}
