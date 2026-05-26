package model;

public class Experience extends Candidate {
    private int expInYear;
    private String proSkill;

    public Experience(String id, String firstName, String lastName, int birthDate,
            String address, String phone, String email, int expInYear, String proSkill) throws Exception {
        super(id, firstName, lastName, birthDate, address, phone, email, 0);
        setExpInYear(expInYear);
        setProSkill(proSkill);
    }

    public int getExpInYear() {
        return expInYear;
    }

    public void setExpInYear(int expInYear) throws Exception {
        if (expInYear >= 0 && expInYear <= 100) {
            this.expInYear = expInYear;
        } else {
            throw new Exception("Year of experience must be from 0 to 100.");
        }
    }

    public String getProSkill() {
        return proSkill;
    }

    public void setProSkill(String proSkill) throws Exception {
        if (proSkill != null && !proSkill.trim().isEmpty()) {
            this.proSkill = proSkill.trim().replaceAll("\\s+", " ");
        } else {
            throw new Exception("Professional skill is invalid.");
        }
    }
}
