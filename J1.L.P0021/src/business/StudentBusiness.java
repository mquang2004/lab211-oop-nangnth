package business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import model.Student;

public class StudentBusiness {
    private final ArrayList<Student> listStudent;

    public StudentBusiness() {
        listStudent = new ArrayList<>();
    }

    public ArrayList<Student> getListStudent() {
        return new ArrayList<>(listStudent);
    }

    public int getSize() {
        return listStudent.size();
    }

    public void addStudent(Student student) throws Exception {
        if (student == null) {
            throw new Exception("Student is invalid.");
        }
        if (isDuplicateStudent(student)) {
            throw new Exception("Student is existed.");
        }
        if (!isSameNameWithId(student.getId(), student.getStudentName())) {
            throw new Exception("Student id is existed with another name.");
        }
        listStudent.add(student);
    }

    public ArrayList<Student> findByName(String name) {
        ArrayList<Student> result = new ArrayList<>();
        String key = name.toLowerCase().trim();
        for (Student student : listStudent) {
            if (student.getStudentName().toLowerCase().contains(key)) {
                result.add(student);
            }
        }
        Collections.sort(result, new Student());
        return result;
    }

    public ArrayList<Student> findById(String id) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student student : listStudent) {
            if (student.getId().equalsIgnoreCase(id.trim())) {
                result.add(student);
            }
        }
        return result;
    }

    public void updateStudent(Student oldStudent, Student newStudent) throws Exception {
        if (oldStudent == null || newStudent == null) {
            throw new Exception("Student is invalid.");
        }
        if (isDuplicateWhenUpdate(oldStudent, newStudent)) {
            throw new Exception("Student is existed.");
        }
        if (!isSameNameWithIdWhenUpdate(oldStudent, newStudent)) {
            throw new Exception("Student id is existed with another name.");
        }
        oldStudent.setId(newStudent.getId());
        oldStudent.setStudentName(newStudent.getStudentName());
        oldStudent.setSemester(newStudent.getSemester());
        oldStudent.setCourseName(newStudent.getCourseName());
    }

    public void deleteStudent(Student student) throws Exception {
        if (!listStudent.remove(student)) {
            throw new Exception("Student is not exist.");
        }
    }

    public ArrayList<String> getReport() {
        LinkedHashMap<String, Integer> report = new LinkedHashMap<>();
        for (Student student : listStudent) {
            String key = student.getStudentName() + "|" + student.getCourseName();
            report.put(key, report.getOrDefault(key, 0) + 1);
        }

        ArrayList<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : report.entrySet()) {
            result.add(entry.getKey() + "|" + entry.getValue());
        }
        return result;
    }

    private boolean isDuplicateStudent(Student student) {
        for (Student item : listStudent) {
            if (isSameStudent(item, student)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDuplicateWhenUpdate(Student oldStudent, Student newStudent) {
        for (Student item : listStudent) {
            if (item != oldStudent && isSameStudent(item, newStudent)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSameStudent(Student first, Student second) {
        return first.getId().equalsIgnoreCase(second.getId())
                && first.getStudentName().equalsIgnoreCase(second.getStudentName())
                && first.getSemester() == second.getSemester()
                && first.getCourseName().equalsIgnoreCase(second.getCourseName());
    }

    private boolean isSameNameWithId(String id, String name) {
        for (Student student : listStudent) {
            if (student.getId().equalsIgnoreCase(id)
                    && !student.getStudentName().equalsIgnoreCase(name)) {
                return false;
            }
        }
        return true;
    }

    private boolean isSameNameWithIdWhenUpdate(Student oldStudent, Student newStudent) {
        for (Student student : listStudent) {
            if (student != oldStudent
                    && student.getId().equalsIgnoreCase(newStudent.getId())
                    && !student.getStudentName().equalsIgnoreCase(newStudent.getStudentName())) {
                return false;
            }
        }
        return true;
    }
}
