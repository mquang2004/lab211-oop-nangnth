package view;

import java.util.ArrayList;
import model.Enrollment;

public class ViewStudent {
    public void printMenu() {
        System.out.print("""      
                           ---------------------------------------------------------
                           WELCOME TO STUDENT MANAGEMENT
                           1. Create
                           2. Find and Sort
                           3. Update/Delete
                           4. Report
                           5. Exit
                           Please choose 1 to Create, 2 to Find and Sort, 3 to Update/Delete, 4 to Report and 5 to Exit program.
                           ---------------------------------------------------------
                           """);
    }

    public void displayMess(String mess) {
        System.out.println(mess);
    }

    public void displayEnrollments(ArrayList<Enrollment> listEnrollment) {
        if (listEnrollment.isEmpty()) {
            displayMess("No student found.");
            return;
        }
        System.out.printf("%-5s %-5s %-20s %-10s %-10s%n", "No.", "ID", "Student name", "Semester", "Course");
        for (int i = 0; i < listEnrollment.size(); i++) {
            Enrollment enrollment = listEnrollment.get(i);
            System.out.printf("%-5d %-5s %s%n", i + 1, enrollment.getStudentId(), enrollment.toString());
        }
    }
    
    public void displayReport(ArrayList<String> report) {
        if (report.isEmpty()) {
            displayMess("No student found.");
            return;
        }
        for (String item : report) {
            String[] data = item.split("\\|");
            System.out.printf("%s | %s | %s%n", data[1], data[2], data[3]);
        }
    }
}
