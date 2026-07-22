package controller;

import bo.TaskManager;
import common.Constant;
import java.util.ArrayList;
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
                String taskTypeId = inputData.getString("Task Type: ", Constant.REG_TASK_TYPE);
                String date = inputData.getString("Date: ", Constant.REG_DATE);
                ArrayList<Task> tasksInDay = taskManager.getTasksByDate(date);
                if (!tasksInDay.isEmpty()) {
                    viewTask.displayTasksInDay(tasksInDay);
                }

                String planFrom;
                String planTo;
                while (true) {
                    try {
                        planFrom = inputData.getString("From: ", Constant.REG_PLAN_TIME);
                        planTo = inputData.getString("To: ", Constant.REG_PLAN_TIME);
                        if (taskManager.isTaskTimeOverlapInDay(date, planFrom, planTo)) {
                            viewTask.displayMess("Task time is overlapped.");
                            continue;
                        }
                        break;
                    } catch (Exception e) {
                        viewTask.displayMess(e.getMessage());
                    }
                }
                String assignee = inputData.getString("Assignee: ", Constant.REG_TEXT);
                String reviewer = inputData.getString("Reviewer: ", Constant.REG_TEXT);

                int id = taskManager.addTask(requirementName, assignee, reviewer, taskTypeId, date, planFrom, planTo);
                viewTask.displayMess("Add task successfully. ID: " + id);
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
            String id = inputData.getString("ID: ", Constant.REG_ID);
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
