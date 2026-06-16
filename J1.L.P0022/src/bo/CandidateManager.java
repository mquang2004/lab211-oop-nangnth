package bo;

import java.time.Year;
import java.util.ArrayList;
import model.Candidate;

public class CandidateManager {
    private final ArrayList<Candidate> candidateList;
    
    public CandidateManager(){
        candidateList = new ArrayList<>();
    }

    public void addCandidate(Candidate candidate) throws Exception {
        if (candidate == null) {
            throw new Exception("Candidate is invalid.");
        }
        if (isDuplicateId(candidate.getId())) {
            throw new Exception("Candidate id is existed.");
        }
        candidateList.add(candidate);
    }

    public ArrayList<Candidate> getCandidates() {
        return new ArrayList<>(candidateList);
    }

    public ArrayList<Candidate> search(String name, int type) {
        ArrayList<Candidate> result = new ArrayList<>();
        for (Candidate candidate : candidateList) {
            if (candidate.getType() == type && candidate.matchName(name)) {
                result.add(candidate);
            }
        }
        return result;
    }

    public boolean isDuplicateId(String id) {
        for (Candidate candidate : candidateList) {
            if (candidate.getId().equalsIgnoreCase(id.trim())) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isBirthDateValid(int birthDate){
        int currentYear = Year.now().getValue();
        if(birthDate >= 1900 && birthDate <= currentYear){
            return true;
        }
        return false;
    }
}
