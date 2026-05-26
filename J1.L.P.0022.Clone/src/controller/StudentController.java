package controller;

import bo.StudentManager;
import common.Constant;
import java.util.ArrayList;
import model.Course;
import model.Enrollment;
import model.Student;
import utils.InputData;
import view.ViewStudent;

public class StudentController {

    private final StudentManager studentManager = new StudentManager();
    private final InputData inputData = new InputData();
    private final ViewStudent viewStudent = new ViewStudent();

    public void createStudent() {
        while (true) {
            if (studentManager.getSize() >= 1) {
                String option = inputData.inputString("Do you want to continue (Y/N)? Choose Y to continue, N to return main screen: ", Constant.REG_YN);
                if (option.equalsIgnoreCase("N")) {
                    return;
                }
            }

            String id;
            String courseName;
            int semester;
            Student student;

            try {
                id = inputData.inputString("Please enter student id: ", Constant.REG_ID);
                if (studentManager.isIdExisted(id)) {
                    ArrayList<Enrollment> listStudentById = studentManager.findStudentById(id);
                    viewStudent.displayMess("Student course registered as below: ");
                    viewStudent.displayEnrollments(listStudentById);
                    student = studentManager.findStudentInfoById(id);
                } else {
                    String studentName = inputData.inputString("Please enter student name: ", Constant.REG_NAME);
                    student = new Student(id, studentName);
                }

                while (true) {
                    semester = inputData.inputInteger("Please enter semester: ", Constant.REG_SEMESTER);
                    courseName = inputData.inputString("Please enter course name: ", Constant.REG_COURSE);
                    if (!studentManager.isCourseRegisteredInSemester(id, semester, courseName)) {
                        break;
                    }
                    viewStudent.displayMess("Student registered this course in this semester, enter again.");
                }

                studentManager.addEnrollment(new Enrollment(student, semester, new Course(courseName)));
                viewStudent.displayMess("Student added successfully.");
            } catch (Exception e) {
                viewStudent.displayMess(e.getMessage());
            }
        }
    }

    public void findAndSortStudent() {
        String name = inputData.inputString("Please enter student name: ", Constant.REG_NAME);
        ArrayList<Enrollment> listStudentByName = studentManager.findStudentByName(name);
        viewStudent.displayEnrollments(listStudentByName);
    }

    public void updateOrDeleteStudent() {
        try {
            String id = inputData.inputString("Please enter student id to update or delete: ", Constant.REG_ID);
            ArrayList<Enrollment> listStudentById = studentManager.findStudentById(id);
            if (!studentManager.isIdExisted(id)) {
                viewStudent.displayMess("Student is not exist.");
                return;
            }
            viewStudent.displayEnrollments(listStudentById);

            String option = inputData.inputString("Do you want to update (U) or delete (D) student. If user chooses U, the program allows user updating. Choose D for deleting student: ", Constant.REG_UD);
            int index = inputData.inputIntegerInRange("Please enter index of student data: ", 1, listStudentById.size());
            Enrollment enrollmentByIndex = listStudentById.get(index - 1);

            if (option.equalsIgnoreCase("U")) {
                updateEnrollment(id, enrollmentByIndex);
            } else {
                studentManager.deleteEnrollment(enrollmentByIndex);
                viewStudent.displayMess("Student has been deleted.");
            }
        } catch (Exception e) {
            viewStudent.displayMess(e.getMessage());
        }
    }

    private void updateEnrollment(String id, Enrollment enrollmentByIndex) throws Exception {
        String studentName = inputData.inputString("Please enter student name: ", Constant.REG_NAME);
        int semester;
        String courseName;

        while (true) {
            semester = inputData.inputInteger("Please enter semester: ", Constant.REG_SEMESTER);
            courseName = inputData.inputString("Please enter course name: ", Constant.REG_COURSE);
            if (!studentManager.isCourseRegisteredInSemester(id, semester, courseName, enrollmentByIndex)) {
                break;
            }
            viewStudent.displayMess("Student registered this course in this semester, enter again.");
        }

        if (studentManager.isStudentNameChanged(enrollmentByIndex, studentName)) {
            studentManager.updateStudentNameById(id, studentName);
        }
        studentManager.updateEnrollment(enrollmentByIndex, new Enrollment(enrollmentByIndex.getStudent(), semester, new Course(courseName)));
        viewStudent.displayMess("Information of student has been updated.");
    }

    public void getReport() {
        viewStudent.displayMess("The report as below: ");
        viewStudent.displayReport(studentManager.getStudentReport());
    }
}
