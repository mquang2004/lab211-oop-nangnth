package bo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import model.Enrollment;
import model.Student;

public class StudentManager {
    private final ArrayList<Enrollment> listEnrollment;

    public StudentManager() {
        listEnrollment = new ArrayList<>();
    }

    public int getSize() {
        return listEnrollment.size();
    }

    public void addEnrollment(Enrollment enrollment) throws Exception {
        if (enrollment == null) {
            throw new Exception("Enrollment is invalid.");
        }
        listEnrollment.add(enrollment);
    }

    public void updateEnrollment(Enrollment oldEnrollment, Enrollment newEnrollment) throws Exception {
        if (oldEnrollment == null || newEnrollment == null) {
            throw new Exception("Enrollment is invalid.");
        }
        oldEnrollment.setStudent(newEnrollment.getStudent());
        oldEnrollment.setSemester(newEnrollment.getSemester());
        oldEnrollment.setCourse(newEnrollment.getCourse());
    }

    public void updateStudentNameById(String id, String newName) throws Exception {
        if (id == null || newName == null) {
            throw new Exception("Student is invalid.");
        }
        boolean isUpdated = false;
        for (Enrollment enrollment : listEnrollment) {
            if (enrollment.getStudentId().equalsIgnoreCase(id.trim())) {
                enrollment.getStudent().setStudentName(newName);
                isUpdated = true;
            }
        }
        if (!isUpdated) {
            throw new Exception("Student is not exist.");
        }
    }

    public void deleteEnrollment(Enrollment enrollment) throws Exception {
        if (enrollment == null || !listEnrollment.remove(enrollment)) {
            throw new Exception("Enrollment is not exist.");
        }
    }

    public ArrayList<String> getStudentReport() {
        LinkedHashMap<String, Integer> report = new LinkedHashMap<>();
        for (Enrollment enrollment : listEnrollment) {
            String key = enrollment.getStudentId() + "|" + enrollment.getStudentName() + "|" + enrollment.getCourseName();
            report.put(key, report.getOrDefault(key, 0) + 1);
        }

        ArrayList<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : report.entrySet()) {
            result.add(entry.getKey() + "|" + entry.getValue());
        }
        return result;
    }

    public ArrayList<Enrollment> findStudentByName(String name) {
        ArrayList<Enrollment> listStudentByName = new ArrayList<>();
        String key = name.toLowerCase().trim();
        for (Enrollment enrollment : listEnrollment) {
            if (enrollment.getStudentName().toLowerCase().contains(key)) {
                listStudentByName.add(enrollment);
            }
        }
        Collections.sort(listStudentByName, new Enrollment());
        return listStudentByName;
    }

    public ArrayList<Enrollment> findStudentById(String id) throws Exception {
        if (id == null) {
            throw new Exception("Student id is invalid");
        }
        ArrayList<Enrollment> listStudentById = new ArrayList<>();
        String key = id.toLowerCase().trim();
        for (Enrollment enrollment : listEnrollment) {
            if (enrollment.getStudentId().equalsIgnoreCase(key)) {
                listStudentById.add(enrollment);
            }
        }
        return listStudentById;
    }

    public Student findStudentInfoById(String id) throws Exception {
        if (id == null) {
            throw new Exception("Student id is invalid");
        }
        for (Enrollment enrollment : listEnrollment) {
            if (enrollment.getStudentId().equalsIgnoreCase(id.trim())) {
                return enrollment.getStudent();
            }
        }
        return null;
    }

    public boolean isIdExisted(String id) throws Exception {
        if (id == null) {
            throw new Exception("Student id is invalid");
        }
        for (Enrollment enrollment : listEnrollment) {
            if (enrollment.getStudentId().equalsIgnoreCase(id.trim())) {
                return true;
            }
        }
        return false;
    }

    public boolean isCourseRegisteredInSemester(String id, int semester, String courseName) throws Exception {
        return isCourseRegisteredInSemester(id, semester, courseName, null);
    }

    public boolean isCourseRegisteredInSemester(String id, int semester, String courseName, Enrollment exceptEnrollment) throws Exception {
        if (id == null) {
            throw new Exception("Student id is invalid.");
        }
        if (courseName == null) {
            throw new Exception("Course name is invalid.");
        }
        for (Enrollment enrollment : listEnrollment) {
            if (enrollment == exceptEnrollment) {
                continue;
            }
            if (enrollment.getStudentId().equalsIgnoreCase(id.trim())
                    && enrollment.getCourseName().equalsIgnoreCase(courseName.trim())
                    && enrollment.getSemester() == semester) {
                return true;
            }
        }
        return false;
    }

    public boolean isStudentNameChanged(Enrollment enrollment, String newName) throws Exception {
        if (enrollment == null || newName == null) {
            throw new Exception("Enrollment is invalid.");
        }
        return !enrollment.getStudentName().trim().equalsIgnoreCase(newName.trim());
    }
}
