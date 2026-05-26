package model;

public class Intern extends Candidate {
    private String majors;
    private String semester;
    private String universityName;

    public Intern(String id, String firstName, String lastName, int birthDate,
            String address, String phone, String email, String majors,
            String semester, String universityName) throws Exception {
        super(id, firstName, lastName, birthDate, address, phone, email, 2);
        setMajors(majors);
        setSemester(semester);
        setUniversityName(universityName);
    }

    public String getMajors() {
        return majors;
    }

    public void setMajors(String majors) throws Exception {
        if (majors != null && !majors.trim().isEmpty()) {
            this.majors = majors.trim().replaceAll("\\s+", " ");
        } else {
            throw new Exception("Majors is invalid.");
        }
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) throws Exception {
        if (semester != null && !semester.trim().isEmpty()) {
            this.semester = semester.trim().replaceAll("\\s+", " ");
        } else {
            throw new Exception("Semester is invalid.");
        }
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) throws Exception {
        if (universityName != null && !universityName.trim().isEmpty()) {
            this.universityName = universityName.trim().replaceAll("\\s+", " ");
        } else {
            throw new Exception("University name is invalid.");
        }
    }
}
