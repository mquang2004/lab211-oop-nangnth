/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

public class Task {
    private int id;
    private int taskTypeId;
    private String name;
    private Date date;
    private double planFrom;
    private double planTo;
    private String assignee;
    private String reviewer;

    public Task() {
    }

    public Task(int taskTypeId, String name, Date date, double planFrom, double planTo, String assignee, String reviewer) {
        setTaskTypeId(taskTypeId);
        setName(name);
        setDate(date);
        setPlanFrom(planFrom);
        setPlanTo(planTo);
        setAssignee(assignee);
        setReviewer(reviewer);
    }

    public Task(int id, int taskTypeId, String name, Date date, double planFrom, double planTo, String assignee, String reviewer) {
        setId(id);
        setTaskTypeId(taskTypeId);
        setName(name);
        setDate(date);
        setPlanFrom(planFrom);
        setPlanTo(planTo);
        setAssignee(assignee);
        setReviewer(reviewer);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id must be greater than or equal to 0.");
        }
        this.id = id;
    }

    public int getTaskTypeId() {
        return taskTypeId;
    }

    public void setTaskTypeId(int taskTypeId) {
        if (taskTypeId < 1 || taskTypeId > 4) {
            throw new IllegalArgumentException("Task type must be from 1 to 4.");
        }
        this.taskTypeId = taskTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Requirement name must not be empty.");
        }
        this.name = name.trim();
    }

    public Date getDate() {
        if (date == null) {
            return null;
        }
        return new Date(date.getTime());
    }

    public void setDate(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Date must not be null.");
        }
        this.date = new Date(date.getTime());
    }

    public double getPlanFrom() {
        return planFrom;
    }

    public void setPlanFrom(double planFrom) {
        validatePlanTime(planFrom, "Plan from");
        this.planFrom = planFrom;
    }

    public double getPlanTo() {
        return planTo;
    }

    public void setPlanTo(double planTo) {
        validatePlanTime(planTo, "Plan to");
        this.planTo = planTo;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        if (assignee == null || assignee.trim().isEmpty()) {
            throw new IllegalArgumentException("Assignee must not be empty.");
        }
        this.assignee = assignee.trim();
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        if (reviewer == null || reviewer.trim().isEmpty()) {
            throw new IllegalArgumentException("Reviewer must not be empty.");
        }
        this.reviewer = reviewer.trim();
    }

    private void validatePlanTime(double time, String fieldName) {
        if (!Double.isFinite(time) || time < 8.0 || time > 17.5 || time * 10 % 5 != 0) {
            throw new IllegalArgumentException(fieldName + " must be from 8.0 to 17.5 and increase by 0.5.");
        }
    }

    @Override
    public String toString() {
        return "Task{" + "id=" + id + ", taskTypeId=" + taskTypeId + ", name=" + name + ", date=" + date + ", planFrom=" + planFrom + ", planTo=" + planTo + ", assignee=" + assignee + ", reviewer=" + reviewer + '}';
    }

}
