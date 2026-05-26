package business;

import java.util.ArrayList;
import model.Candidate;

public class CandidateBusiness {
    private final ArrayList<Candidate> candidates = new ArrayList<>();

    public void addCandidate(Candidate candidate) throws Exception {
        if (candidate == null) {
            throw new Exception("Candidate is invalid.");
        }
        if (isDuplicateId(candidate.getId())) {
            throw new Exception("Candidate id is existed.");
        }
        candidates.add(candidate);
    }

    public ArrayList<Candidate> getCandidates() {
        return new ArrayList<>(candidates);
    }

    public ArrayList<Candidate> search(String name, int type) {
        ArrayList<Candidate> result = new ArrayList<>();
        for (Candidate candidate : candidates) {
            if (candidate.getType() == type && candidate.matchName(name)) {
                result.add(candidate);
            }
        }
        return result;
    }

    private boolean isDuplicateId(String id) {
        for (Candidate candidate : candidates) {
            if (candidate.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }
}
