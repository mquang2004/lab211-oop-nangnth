package controller;

import bo.TaskManager;
import common.Constant;
import java.util.ArrayList;
import java.util.Date;
import model.Task;
import utils.InputData;
import view.ViewTask;

public class TaskController {

    private final TaskManager taskManager = new TaskManager();
    private final InputData inputData = new InputData();
    private final ViewTask viewTask = new ViewTask();

    public void addTask() {
        while (true) {
            try {
                viewTask.displayMess("------------Add Task---------------");
                String requirementName = inputData.getString("Requirement Name: ", Constant.REG_TEXT);
                int taskTypeId = inputData.inputInteger("Task Type: ", Constant.REG_TASK_TYPE);
                Date date = inputData.getDate("Date: ", Constant.REG_DATE);
                double planFrom = inputData.inputDouble("From: ", Constant.REG_PLAN_TIME);
                double planTo = inputData.inputDouble("To: ", Constant.REG_PLAN_TIME);
                String assignee = inputData.getString("Assignee: ", Constant.REG_TEXT);
                String reviewer = inputData.getString("Reviewer: ", Constant.REG_TEXT);

                taskManager.addTask(new Task(taskTypeId, requirementName, date, planFrom, planTo, assignee, reviewer));
                viewTask.displayMess("Add task successfully.");
                break;
            } catch (Exception e) {
                viewTask.displayMess(e.getMessage());
            }
        }
    }

    public void deleteTask() {
        try {
            ArrayList<Task> taskList = taskManager.getDataTasks();
            if (taskList.isEmpty()) {
                viewTask.displayMess("Task list is empty.");
                return;
            }

            viewTask.displayMess("---------Del Task------");
            int id = inputData.inputInteger("ID: ", Constant.REG_ID);
            taskManager.deleteTask(id);
            viewTask.displayMess("Task deleted successfully.");
        } catch (Exception e) {
            viewTask.displayMess(e.getMessage());
        }
    }

    public void displayAll() {
        viewTask.displayAll(taskManager.getDataTasks());
    }
}
