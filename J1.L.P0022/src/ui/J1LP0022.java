package ui;

import common.Constant;
import controller.CandidateController;
import utils.InputData;
import view.ViewCandidate;

public class J1LP0022 {

    public static void main(String[] args) {
        ViewCandidate view = new ViewCandidate();
        CandidateController controller = new CandidateController();
        InputData input = new InputData();
        while (true) {
            view.printMenu();
            int choose = input.inputInteger("Your option: ", Constant.REG_MENU);
            switch (choose) {
                case 1:
                    controller.createExperience();
                    break;
                case 2:
                    controller.createFresher();
                    break;
                case 3:
                    controller.createIntern();
                    break;
                case 4:
                    controller.search();
                    break;
                default:
                    return;
            }
        }
    }
}
