/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package j1.s.p0070;

import common.Constant;
import controller.EbankController;
import utils.InputData;
import view.ViewEbank;

public class J1SP0070 {

    public static void main(String[] args) {
        EbankController ebankController = new EbankController();
        InputData inputData = new InputData();
        ViewEbank viewEbank = new ViewEbank();

        while (true) {
            viewEbank.printMenu();
            int choice = inputData.inputInteger("Please choice one option: ", Constant.REG_MENU);
            switch (choice) {
                case 1:
                case 2:
                    ebankController.setLanguage(choice);
                    ebankController.login();
                    break;
                case 3:
                    return;
                default:
                    break;
            }
        }
    }
}
