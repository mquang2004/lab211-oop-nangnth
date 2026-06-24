package model;

import java.util.Date;

public class Expense {
    private int id;
    private Date date;
    private double number;
    private String content;

    public Expense() {
    }
    
    public Expense(Date date, double number, String content) {
        setDate(date);
        setNumber(number);
        setContent(content);
    }

    public Expense(int id, Date date, double number, String content) {
        setId(id);
        setDate(date);
        setNumber(number);
        setContent(content);
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

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        if (!Double.isFinite(number) || number <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0.");
        }
        this.number = number;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Content must not be empty.");
        }
        this.content = content.trim();
    }

    @Override
    public String toString() {
        return "Expense{" + "id=" + id + ", date=" + date + ", number=" + number + ", content=" + content + '}';
    }

}
