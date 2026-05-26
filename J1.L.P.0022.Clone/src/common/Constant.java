package common;

public class Constant {
    public static final String REG_ID = "[A-Za-z0-9]+";
    public static final String REG_NAME = "[A-Za-z ]+";
    public static final String REG_SEMESTER = "[1-9][0-9]*";
    public static final String REG_COURSE = "(?i)(Java|\\.Net|C/C\\+\\+)";
    public static final String REG_MENU = "[1-5]";
    public static final String REG_YN = "(?i)[YN]";
    public static final String REG_UD = "(?i)[UD]";
    public static String getMenuRegex(int min, int max) {
        StringBuilder regex = new StringBuilder("^(");
        for (int i = min; i <= max; i++) {
            regex.append(i);
            if (i < max) regex.append("|");
        }
        regex.append(")$");
        return regex.toString();
    }
}
