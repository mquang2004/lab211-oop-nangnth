package common;

public class Constant {
    public static final String REG_MENU = "[1-5]";
    public static final String REG_ID = "[A-Za-z0-9]+";
    public static final String REG_NAME = "[\\p{L} ]+";
    public static final String REG_TEXT = ".+";
    public static final String REG_YEAR = "[0-9]{4}";
    public static final String REG_PHONE = "[0-9]{10}";
    public static final String REG_EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    public static final String REG_EXP_YEAR = "([0-9]|[1-9][0-9]|100)";
    public static final String REG_GRADUATION_RANK = "(?i)(Excellence|Good|Fair|Poor)";
    public static final String REG_CANDIDATE_TYPE = "[0-2]";
    public static final String REG_YN = "(?i)[YN]";
}
