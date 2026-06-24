/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package j1.s.p0071;

import common.Constant;
import controller.TaskController;
import utils.InputData;
import view.ViewTask;

public class J1SP0071 {

    public static void main(String[] args) {
        TaskController taskController = new TaskController();
        InputData inputData = new InputData();
        ViewTask viewTask = new ViewTask();

        while (true) {
            viewTask.printMenu();
            int choice = inputData.inputInteger("Enter your choice: ", Constant.REG_MENU);
            switch (choice) {
                case 1:
                    taskController.addTask();
                    break;
                case 2:
                    taskController.deleteTask();
                    break;
                case 3:
                    taskController.displayAll();
                    break;
                case 4:
                    return;
                default:
                    break;
            }
        }
    }
}
