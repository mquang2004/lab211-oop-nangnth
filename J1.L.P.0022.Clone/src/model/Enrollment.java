package model;

import java.util.Comparator;

public class Enrollment implements Comparator<Enrollment> {
    private Student student;
    private Course course;
    private int semester;

    public Enrollment() {
    }

    public Enrollment(Student student, int semester, Course course) throws Exception {
        setStudent(student);
        setSemester(semester);
        setCourse(course);
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) throws Exception {
        if (student == null) {
            throw new Exception("Student is invalid.");
        }
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) throws Exception {
        if (course == null) {
            throw new Exception("Course is invalid.");
        }
        this.course = course;
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

    public String getStudentId() {
        return student.getId();
    }

    public String getStudentName() {
        return student.getStudentName();
    }

    public String getCourseName() {
        return course.getCourseName();
    }

    @Override
    public String toString() {
        return String.format("%-20s %-10d %-10s", getStudentName(), semester, getCourseName());
    }

    @Override
    public int compare(Enrollment firstEnrollment, Enrollment secondEnrollment) {
        return firstEnrollment.getStudentName().compareToIgnoreCase(secondEnrollment.getStudentName());
    }
}
