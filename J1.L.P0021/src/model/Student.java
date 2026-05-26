package model;

import common.Constant;
import java.util.Comparator;

public class Student implements Comparator<Student> {
    private String id;
    private String studentName;
    private int semester;
    private String courseName;

    public Student() {
    }

    public Student(String id, String studentName, int semester, String courseName) throws Exception {
        setId(id);
        setStudentName(studentName);
        setSemester(semester);
        setCourseName(courseName);
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

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) throws Exception {
        if (semester > 0) {
            this.semester = semester;
        } else {
            throw new Exception("Semester is invalid.");
        }
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) throws Exception {
        if (courseName != null && courseName.trim().matches(Constant.REG_COURSE)) {
            this.courseName = formatCourseName(courseName.trim());
        } else {
            throw new Exception("Course name is invalid.");
        }
    }

    private String formatCourseName(String courseName) {
        if (courseName.equalsIgnoreCase("java")) {
            return "Java";
        }
        if (courseName.equalsIgnoreCase(".net")) {
            return ".Net";
        }
        return "C/C++";
    }

    @Override
    public String toString() {
        return String.format("%-20s %-10d %-10s", studentName, semester, courseName);
    }

    @Override
    public int compare(Student firstStudent, Student secondStudent) {
        return firstStudent.studentName.compareToIgnoreCase(secondStudent.studentName);
    }
}
