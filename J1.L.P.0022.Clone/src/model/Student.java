package model;

import common.Constant;

public class Student {
    private String id;
    private String studentName;

    public Student(String id, String studentName) throws Exception {
        setId(id);
        setStudentName(studentName);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) throws Exception {
        if (id != null && id.trim().matches(Constant.REG_ID)) {
            this.id = id.trim();
        } else {
            throw new Exception("Student id is invalid.");
        }
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) throws Exception {
        if (studentName != null && studentName.trim().matches(Constant.REG_NAME)) {
            this.studentName = studentName.trim().replaceAll("\\s+", " ");
        } else {
            throw new Exception("Student name is invalid.");
        }
    }
}
