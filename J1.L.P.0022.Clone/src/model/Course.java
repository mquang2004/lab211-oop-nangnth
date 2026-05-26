package model;

import common.Constant;

public class Course {
    private String courseName;

    public Course(String courseName) throws Exception {
        setCourseName(courseName);
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) throws Exception {
        if (courseName != null && courseName.trim().matches(Constant.REG_COURSE)) {
            this.courseName = formatCourseName(courseName.trim());
        } else {
            throw new Exception("Course name is invalid.");
        }
    }

    private String formatCourseName(String courseName) {
        if (courseName.equalsIgnoreCase("java")) {
            return "Java";
        }
        if (courseName.equalsIgnoreCase(".net")) {
            return ".Net";
        }
        return "C/C++";
    }
}
