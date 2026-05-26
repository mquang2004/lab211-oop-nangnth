package view;

import java.util.ArrayList;
import model.Student;

public class ViewStudent {
    public void printMenu() {
        System.out.println("""                           
                           WELCOME TO STUDENT MANAGEMENT
                           1. Create
                           2. Find and Sort
                           3. Update/Delete
                           4. Report
                           5. Exit
                           Please choose 1 to Create, 2 to Find and Sort, "
                    + "3 to Update/Delete, 4 to Report and 5 to Exit program.
                           -------------------------
                           """);
    }

    public void displayMess(String mess) {
        System.out.println(mess);
    }

    public void displayStudents(ArrayList<Student> listStudent) {
        if (listStudent.isEmpty()) {
            displayMess("No student found.");
            return;
        }
        System.out.printf("%-5s %-20s %-10s %-10s%n", "No.", "Student name", "Semester", "Course");
        for (int i = 0; i < listStudent.size(); i++) {
            System.out.printf("%-5d %s%n", i + 1, listStudent.get(i).toString());
        }
    }
    
    public void displayStudent(Student student){
        System.out.println(student.toString());
    }

    public void displayReport(ArrayList<String> report) {
        if (report.isEmpty()) {
            displayMess("No student found.");
            return;
        }
        for (String item : report) {
            String[] data = item.split("\\|");
            System.out.printf("%s | %s | %s%n", data[0], data[1], data[2]);
        }
    }
}
