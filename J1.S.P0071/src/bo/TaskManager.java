package bo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import model.Task;

public class TaskManager {

    private final ArrayList<Task> taskList;
    private int taskId = 1;

    public TaskManager() {
        taskList = new ArrayList<>();
    }

    public int getSize() {
        return taskList.size();
    }

    public ArrayList<Task> getDataTasks() {
        ArrayList<Task> result = new ArrayList<>(taskList);
        result.sort(Comparator.comparingInt(Task::getId));
        return result;
    }

    public void addTask(Task task) throws Exception {
        if (task == null) {
            throw new Exception("Task is invalid.");
        }
        if (task.getPlanFrom() >= task.getPlanTo()) {
            throw new Exception("Plan from must be less than plan to.");
        }

        task.setId(taskId++);
        taskList.add(task);
    }

    public void deleteTask(int id) throws Exception {
        Task task = findTaskById(id);
        if (task == null) {
            throw new Exception("Task is not exist.");
        }
        taskList.remove(task);
    }

    public Task findTaskById(int id) {
        for (Task task : taskList) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    public String getTaskTypeName(int taskTypeId) {
        switch (taskTypeId) {
            case 1:
                return "Code";
            case 2:
                return "Test";
            case 3:
                return "Design";
            case 4:
                return "Review";
            default:
                return "";
        }
    }

    private int parseTaskTypeId(String taskTypeId) throws Exception {
        try {
            int typeId = Integer.parseInt(taskTypeId.trim());
            if (typeId < 1 || typeId > 4) {
                throw new Exception("Task type must be from 1 to 4.");
            }
            return typeId;
        } catch (NumberFormatException e) {
            throw new Exception("Task type must be from 1 to 4.");
        }
    }

    private Date parseDate(String date) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        formatter.setLenient(false);
        try {
            return formatter.parse(date.trim());
        } catch (ParseException e) {
            throw new Exception("Date must be valid and follow dd-MM-yyyy.");
        }
    }

    private double parsePlanTime(String value, String fieldName) throws Exception {
        try {
            double time = Double.parseDouble(value.trim());
            if (!Double.isFinite(time) || time < 8.0 || time > 17.5 || time * 10 % 5 != 0) {
                throw new Exception(fieldName + " must be from 8.0 to 17.5 and increase by 0.5.");
            }
            return time;
        } catch (NumberFormatException e) {
            throw new Exception(fieldName + " must be a number.");
        }
    }
}
