package controller;

import bo.CandidateManager;
import common.Constant;
import java.util.ArrayList;
import model.Candidate;
import model.Experience;
import model.Fresher;
import model.Intern;
import utils.InputData;
import view.ViewCandidate;

public class CandidateController {

    private final CandidateManager candidateManager = new CandidateManager();
    private final InputData inputData = new InputData();
    private final ViewCandidate viewCandidate = new ViewCandidate();

    public void createExperience() {
        createCandidate(0);
    }

    public void createFresher() {
        createCandidate(1);
    }

    public void createIntern() {
        createCandidate(2);
    }

    public void search() {
        ArrayList<Candidate> candidates = candidateManager.getCandidates();
        viewCandidate.displayCandidateNames(candidates);
        if (candidates.isEmpty()) {
            return;
        }
        String name = inputData.inputString("Input Candidate name (First name or Last name): ", Constant.REG_NAME, true);
        int type = inputData.inputInteger("Input type of candidate: ", Constant.REG_CANDIDATE_TYPE);
        viewCandidate.displaySearchResult(candidateManager.search(name, type));
    }

    private void createCandidate(int type) {
        while (true) {
            try {
                Candidate candidate = inputCandidate(type);
                candidateManager.addCandidate(candidate);
                viewCandidate.displayMess("Information of candidate has been added.");
            } catch (Exception e) {
                viewCandidate.displayMess(e.getMessage());
            }

            String choose = inputData.inputString("Do you want to continue (Y/N)?", Constant.REG_YN, true);
            if (choose.equalsIgnoreCase("N")) {
                viewCandidate.displayCandidateNames(candidateManager.getCandidates());
                return;
            }
        }
    }

    private Candidate inputCandidate(int type) throws Exception {
        try {
            String id;
            while (true) {
                id = inputData.inputString("Enter candidate id: ", Constant.REG_ID, false);
                if (!candidateManager.isDuplicateId(id)) {
                    break;               
                }
                viewCandidate.displayMess("Candidate id is exist.");
            }

            String firstName = inputData.inputString("Enter first name: ", Constant.REG_NAME, false);
            String lastName = inputData.inputString("Enter last name: ", Constant.REG_NAME, false);
            
            int birthDate;
            while (true) {
                birthDate = inputData.inputInteger("Enter birth date: ", Constant.REG_YEAR);
                if (candidateManager.isBirthDateValid(birthDate)) {
                    break;
                }
                viewCandidate.displayMess("Birthdate is invalid.");
            }

            String address = inputData.inputString("Enter address: ", Constant.REG_TEXT, false);
            String phone = inputData.inputString("Enter phone: ", Constant.REG_PHONE, false);
            String email = inputData.inputString("Enter email: ", Constant.REG_EMAIL, false);

            switch (type) {
                case 0:
                    int expInYear = inputData.inputInteger("Enter year of experience: ", Constant.REG_EXP_YEAR);
                    String proSkill = inputData.inputString("Enter professional skill: ", Constant.REG_TEXT, false);
                    return new Experience(id, firstName, lastName, birthDate, address, phone, email,
                            expInYear, proSkill);
                case 1:
                    int graduationDate = inputData.inputInteger("Enter graduation date: ", Constant.REG_YEAR);
                    String graduationRank = inputData.inputString("Enter rank of graduation: ", Constant.REG_GRADUATION_RANK, true);
                    String education = inputData.inputString("Enter education: ", Constant.REG_TEXT, false);
                    return new Fresher(id, firstName, lastName, birthDate, address, phone, email,
                            graduationDate, graduationRank, education);
                default:
                    String majors = inputData.inputString("Enter majors: ", Constant.REG_TEXT, false);
                    String semester = inputData.inputString("Enter semester: ", Constant.REG_TEXT, false);
                    String universityName = inputData.inputString("Enter university name: ", Constant.REG_TEXT, false);
                    return new Intern(id, firstName, lastName, birthDate, address, phone, email,
                            majors, semester, universityName);
            }
        } catch (Exception e) {
            viewCandidate.displayMess(e.getMessage());
        }
        return null;
    }
}
