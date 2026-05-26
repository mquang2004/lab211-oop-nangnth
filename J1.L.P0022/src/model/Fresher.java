package model;

import common.Constant;

public class Fresher extends Candidate {
    private int graduationDate;
    private String graduationRank;
    private String education;

    public Fresher(String id, String firstName, String lastName, int birthDate,
            String address, String phone, String email, int graduationDate,
            String graduationRank, String education) throws Exception {
        super(id, firstName, lastName, birthDate, address, phone, email, 1);
        setGraduationDate(graduationDate);
        setGraduationRank(graduationRank);
        setEducation(education);
    }

    public int getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(int graduationDate) throws Exception {
        if (graduationDate >= 1900) {
            this.graduationDate = graduationDate;
        } else {
            throw new Exception("Graduation date is invalid.");
        }
    }

    public String getGraduationRank() {
        return graduationRank;
    }

    public void setGraduationRank(String graduationRank) throws Exception {
        if (graduationRank != null && graduationRank.trim().matches(Constant.REG_GRADUATION_RANK)) {
            this.graduationRank = formatRank(graduationRank);
        } else {
            throw new Exception("Graduation rank must be Excellence, Good, Fair, or Poor.");
        }
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) throws Exception {
        if (education != null && !education.trim().isEmpty()) {
            this.education = education.trim().replaceAll("\\s+", " ");
        } else {
            throw new Exception("Education is invalid.");
        }
    }

    private String formatRank(String rank) {
        String lower = rank.trim().toLowerCase();
        return lower.substring(0, 1).toUpperCase() + lower.substring(1);
    }
}
