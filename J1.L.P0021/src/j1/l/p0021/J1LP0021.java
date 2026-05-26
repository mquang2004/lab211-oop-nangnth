package j1.l.p0021;

import common.Constant;
import controller.StudentController;
import view.InputData;
import view.ViewStudent;

public class J1LP0021 {

    public static void main(String[] args) {
        ViewStudent view = new ViewStudent();
        StudentController controller = new StudentController();
        InputData input = new InputData();
        boolean loop = true;
        while (loop) {
            view.printMenu();
            int choose = input.inputInteger("Your option:", Constant.REG_MENU);
            switch (choose) {
                case 1:
                    controller.createStudent();
                    break;
                case 2:
                    controller.findAndSort();
                    break;
                case 3:
                    controller.updateOrDelete();
                    break;
                case 4:
                    controller.report();
                    break;
                default:
                    loop = false;
                    break;
            }
        }
    }
}
