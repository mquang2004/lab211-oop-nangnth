package view;

import bo.TaskManager;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import model.Task;

public class ViewTask {

    private final TaskManager taskManager = new TaskManager();

    public void printMenu(){
        System.out.print("""      
                           ==========Task Program=========
                           1. Add Task
                           2. Delete Task
                           3. Display Task
                           4. Exit
                           """);
    }

    public void displayMess(String message) {
        System.out.println(message);
    }

    public void displayAll(List<Task> list) {
        if (list == null || list.isEmpty()) {
            displayMess("List is empty.");
            return;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        System.out.println("----------------------------------------- Task ---------------------------------------");
        System.out.printf("%-5s %-20s %-12s %-12s %-8s %-12s %-12s%n",
                "ID", "Name", "Task Type", "Date", "Time", "Assignee", "Reviewer");
        for (Task task : list) {
            System.out.printf("%-5s %-20s %-12s %-12s %-8s %-12s %-12s%n",
                    task.getId(),
                    task.getName(),
                    taskManager.getTaskTypeName(task.getTaskTypeId()),
                    formatter.format(task.getDate()),
                    formatTime(task.getPlanTo() - task.getPlanFrom()),
                    task.getAssignee(),
                    task.getReviewer());
        }
    }

    public void displayTasksInDay(List<Task> list) {
        if (list == null || list.isEmpty()) {
            return;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        System.out.println("------------------------------- Tasks in selected date -------------------------------");
        System.out.printf("%-5s %-20s %-12s %-12s %-10s %-10s %-12s %-12s%n",
                "ID", "Name", "Task Type", "Date", "Plan From", "Plan To", "Assignee", "Reviewer");
        for (Task task : list) {
            System.out.printf("%-5s %-20s %-12s %-12s %-10s %-10s %-12s %-12s%n",
                    task.getId(),
                    task.getName(),
                    taskManager.getTaskTypeName(task.getTaskTypeId()),
                    formatter.format(task.getDate()),
                    formatTime(task.getPlanFrom()),
                    formatTime(task.getPlanTo()),
                    task.getAssignee(),
                    task.getReviewer());
        }
    }

    private String formatTime(double time) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        return decimalFormat.format(time);
    }
}
