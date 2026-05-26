package controller;

import business.StudentBusiness;
import common.Constant;
import java.util.ArrayList;
import model.Student;
import view.InputData;
import view.ViewStudent;

public class StudentController {
    private final StudentBusiness studentBusiness = new StudentBusiness();
    private final InputData inputData = new InputData();
    private final ViewStudent viewStudent = new ViewStudent();

    public void createStudent() {
        while (true) {
            if (studentBusiness.getSize() >= 10) {
                String choose = inputData.inputString("Do you want to continue (Y/N)?", Constant.REG_YN);
                if (choose.equalsIgnoreCase("N")) {
                    return;
                }
            }

            try {
                Student student = inputStudent();
                studentBusiness.addStudent(student);
                viewStudent.displayMess("Information of student has been added.");
            } catch (Exception e) {
                viewStudent.displayMess(e.getMessage());
            }

            if (studentBusiness.getSize() < 10) {
                viewStudent.displayMess("You need to create at least 10 students.");
            }
        }
    }

    public void findAndSort() {
        String name = inputData.inputString("Enter student name:", Constant.REG_NAME);
        ArrayList<Student> result = studentBusiness.findByName(name);
        viewStudent.displayStudents(result);
    }

    public void updateOrDelete() {
        String id = inputData.inputString("Enter student id:", Constant.REG_ID);
        ArrayList<Student> result = studentBusiness.findById(id);
        if (result.isEmpty()) {
            viewStudent.displayMess("Student is not exist.");
            return;
        }

        viewStudent.displayStudents(result);
        int index = 0;
        if (result.size() > 1) {
            while (true) {
                index = inputData.inputInteger("Choose student:", Constant.REG_SEMESTER) - 1;
                if (index >= 0 && index < result.size()) {
                    break;
                }
                viewStudent.displayMess("Please choose from 1 to " + result.size() + ".");
            }
        }
        Student selectedStudent = result.get(index);
        String choose = inputData.inputString("Do you want to update (U) or delete (D) student?", Constant.REG_UD);
        try {
            if (choose.equalsIgnoreCase("U")) {
                Student newStudent = inputStudent();
                studentBusiness.updateStudent(selectedStudent, newStudent);
                viewStudent.displayMess("Information of student has been updated.");
            } else {
                studentBusiness.deleteStudent(selectedStudent);
                viewStudent.displayMess("Information of student has been deleted.");
            }
        } catch (Exception e) {
            viewStudent.displayMess(e.getMessage());
        }
    }

    public void report() {
        viewStudent.displayReport(studentBusiness.getReport());
    }

    private Student inputStudent() throws Exception {
        String id = inputData.inputString("Enter student id:", Constant.REG_ID);
        String name = inputData.inputString("Enter student name:", Constant.REG_NAME);
        int semester = inputData.inputInteger("Enter semester:", Constant.REG_SEMESTER);
        String course = inputData.inputString("Enter course name (Java, .Net, C/C++):", Constant.REG_COURSE);
        return new Student(id, name, semester, course);
    }
}
