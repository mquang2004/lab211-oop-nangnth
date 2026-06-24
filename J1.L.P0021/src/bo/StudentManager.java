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
        if (isCourseRegisteredInSemester(enrollment.getStudentId(), enrollment.getSemester(), enrollment.getCourseName())) {
            throw new Exception("Student registered this course in this semester.");
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
        LinkedHashMap<String, Integer> reportMap = new LinkedHashMap<>();
        for (Enrollment enrollment : listEnrollment) {
            String key = enrollment.getStudentId() + "|" + enrollment.getStudentName() + "|" + enrollment.getCourseName();
            reportMap.put(key, reportMap.getOrDefault(key, 0) + 1);
        }

        ArrayList<String> reportList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : reportMap.entrySet()) {
            reportList.add(entry.getKey() + "|" + entry.getValue());
        }
        return reportList;
    }

    public ArrayList<Enrollment> findStudentByName(String name) throws Exception {
        if(name == null){
            throw new Exception("Name is invalid.");
        }
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
        return !enrollment.getStudentName().trim().equals(newName.trim());
    }
}
