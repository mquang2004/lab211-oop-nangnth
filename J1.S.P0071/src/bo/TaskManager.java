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

    public TaskManager() {
        taskList = new ArrayList<>();
    }

    public int getSize() {
        return taskList.size();
    }

    public ArrayList<Task> getDataTasks() {
        ArrayList<Task> result = new ArrayList<>(taskList);
        result.sort(Comparator.comparingInt(task -> Integer.parseInt(task.getId())));
        return result;
    }

    public ArrayList<Task> getTasksByDate(String date) throws Exception {
        Date validDate = parseDate(date);
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDate().equals(validDate)) {
                result.add(task);
            }
        }
        result.sort(Comparator.comparingDouble(Task::getPlanFrom));
        return result;
    }

    public int addTask(String requirementName, String assignee, String reviewer, String taskTypeId,
            String date, String planFrom, String planTo) throws Exception {
        String validTaskTypeId = parseTaskTypeId(taskTypeId);
        Date validDate = parseDate(date);
        double validPlanFrom = parsePlanTime(planFrom, "Plan from");
        double validPlanTo = parsePlanTime(planTo, "Plan to");

        if (validPlanFrom >= validPlanTo) {
            throw new Exception("Plan from must be less than plan to.");
        }
        if (isDuplicateTask(requirementName, assignee, reviewer, validTaskTypeId,
                validDate, validPlanFrom, validPlanTo)) {
            throw new Exception("Task is duplicated.");
        }
        if (isTaskTimeOverlapInDay(validDate, validPlanFrom, validPlanTo)) {
            throw new Exception("Task time is overlapped.");
        }

        int newId = getNextId();
        Task task = new Task(String.valueOf(newId), validTaskTypeId, requirementName, validDate,
                validPlanFrom, validPlanTo, assignee, reviewer);
        taskList.add(task);
        return newId;
    }

    public void deleteTask(String id) throws Exception {
        Task task = findTaskById(id);
        if (task == null) {
            throw new Exception("Task is not exist.");
        }
        taskList.remove(task);
    }

    public Task findTaskById(String id) {
        for (Task task : taskList) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

    public String getTaskTypeName(String taskTypeId) {
        switch (taskTypeId) {
            case "1":
                return "Code";
            case "2":
                return "Test";
            case "3":
                return "Design";
            case "4":
                return "Review";
            default:
                return "";
        }
    }

    public boolean isDuplicateTask(String requirementName, String assignee, String reviewer,
            String taskTypeId, String date, String planFrom, String planTo) throws Exception {
        String validTaskTypeId = parseTaskTypeId(taskTypeId);
        Date validDate = parseDate(date);
        double validPlanFrom = parsePlanTime(planFrom, "Plan from");
        double validPlanTo = parsePlanTime(planTo, "Plan to");

        if (validPlanFrom >= validPlanTo) {
            throw new Exception("Plan from must be less than plan to.");
        }

        return isDuplicateTask(requirementName, assignee, reviewer, validTaskTypeId,
                validDate, validPlanFrom, validPlanTo);
    }

    private boolean isDuplicateTask(String requirementName, String assignee, String reviewer,
            String taskTypeId, Date date, double planFrom, double planTo) {
        for (Task task : taskList) {
            if (task.getName().equalsIgnoreCase(requirementName.trim())
                    && task.getAssignee().equalsIgnoreCase(assignee.trim())
                    && task.getReviewer().equalsIgnoreCase(reviewer.trim())
                    && task.getTaskTypeId().equals(taskTypeId)
                    && task.getDate().equals(date)
                    && task.getPlanFrom() == planFrom
                    && task.getPlanTo() == planTo) {
                return true;
            }
        }
        return false;
    }

    private int getNextId() {
        int maxId = 0;
        for (Task task : taskList) {
            int currentId = Integer.parseInt(task.getId());
            if (currentId > maxId) {
                maxId = currentId;
            }
        }
        return maxId + 1;
    }

    private String parseTaskTypeId(String taskTypeId) throws Exception {
        if (taskTypeId == null || !taskTypeId.trim().matches("[1-4]")) {
            throw new Exception("Task type must be from 1 to 4.");
        }
        return taskTypeId.trim();
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
    
    public boolean isTaskTimeOverlapInDay(Date date, double planFrom, double planTo) {
        for (Task task : taskList) {
            if (task.getDate().equals(date)
                    && planFrom < task.getPlanTo()
                    && planTo > task.getPlanFrom()) {
                return true;
            }
        }
        return false;
    }

    public boolean isTaskTimeOverlapInDay(String date, String planFrom, String planTo) throws Exception {
        Date validDate = parseDate(date);
        double validPlanFrom = parsePlanTime(planFrom, "Plan from");
        double validPlanTo = parsePlanTime(planTo, "Plan to");
        if (validPlanFrom >= validPlanTo) {
            throw new Exception("Plan from must be less than plan to.");
        }
        return isTaskTimeOverlapInDay(validDate, validPlanFrom, validPlanTo);
    }
}
