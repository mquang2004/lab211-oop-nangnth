package view;

import java.util.ArrayList;
import model.Candidate;

public class ViewCandidate {
    public void printMenu() {
        System.out.println("""
                           CANDIDATE MANAGEMENT SYSTEM
                           1. Experience
                           2. Fresher
                           3. Internship
                           4. Searching
                           5. Exit
                           Please choose 1 to Create Experience Candidate, 2 to Create Fresher Candidate,
                           3 to Create Internship Candidate, 4 to Searching and 5 to Exit program.
                           -------------------------
                           """);
    }

    public void displayMess(String mess) {
        System.out.println(mess);
    }

    public void displayCandidateNames(ArrayList<Candidate> candidates) {
        if (candidates.isEmpty()) {
            displayMess("No candidate found.");
            return;
        }
        displayGroup(candidates, 0, "===========EXPERIENCE CANDIDATE============");
        displayGroup(candidates, 1, "==========FRESHER CANDIDATE==============");
        displayGroup(candidates, 2, "===========INTERN CANDIDATE==============");
    }

    public void displaySearchResult(ArrayList<Candidate> candidates) {
        if (candidates.isEmpty()) {
            displayMess("No candidate found.");
            return;
        }
        System.out.println("The candidates found:");
        for (Candidate candidate : candidates) {
            System.out.println(candidate);
        }
    }

    private void displayGroup(ArrayList<Candidate> candidates, int type, String title) {
        System.out.println(title);
        for (Candidate candidate : candidates) {
            if (candidate.getType() == type) {
                System.out.println(candidate.getFullName());
            }
        }
    }
}
