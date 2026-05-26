
package Model;

import Common.Constant;

public class Number {
    private int base;
    private String value;

    public Number() {
    }

    public Number(int base, String value) {
        if (!isValidBase(base)) {
            throw new IllegalArgumentException("Base must be 2, 10, or 16.");
        }
        if (value == null || !isValidValue(base, value.trim())) {
            throw new IllegalArgumentException("Invalid number for base " + base + ".");
        }
        this.base = base;
        this.value = base == 16 ? value.trim().toUpperCase() : value.trim();
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        if (!isValidBase(base)) {
            throw new IllegalArgumentException("Base must be 2, 10, or 16.");
        }
        this.base = base;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        if (value == null || !isValidValue(base, value.trim())) {
            throw new IllegalArgumentException("Invalid number for base " + base + ".");
        }
        this.value = base == 16 ? value.trim().toUpperCase() : value.trim();
    }

    public static boolean isValidValue(int base, String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        switch (base) {
            case 2:
                return value.matches(Constant.REGEX_BINARY);
            case 10:
                return value.matches(Constant.REGEX_DECIMAL);
            case 16:
                return value.matches(Constant.REGEX_HEXADECIMAL);
            default:
                return false;
        }
    }

    private boolean isValidBase(int base) {
        return base == 2 || base == 10 || base == 16;
    }
}
