package model;

import common.Constant;
import java.time.Year;

public abstract class Candidate {
    private String id;
    private String firstName;
    private String lastName;
    private int birthDate;
    private String address;
    private String phone;
    private String email;
    private int type;

    public Candidate(String id, String firstName, String lastName, int birthDate,
            String address, String phone, String email, int type) throws Exception {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
        setAddress(address);
        setPhone(phone);
        setEmail(email);
        setType(type);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) throws Exception {
        if (id != null && id.trim().matches(Constant.REG_ID)) {
            this.id = id.trim();
        } else {
            throw new Exception("Candidate id is invalid.");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws Exception {
        if (firstName != null && firstName.trim().matches(Constant.REG_NAME)) {
            this.firstName = normalizeSpace(firstName);
        } else {
            throw new Exception("First name is invalid.");
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws Exception {
        if (lastName != null && lastName.trim().matches(Constant.REG_NAME)) {
            this.lastName = normalizeSpace(lastName);
        } else {
            throw new Exception("Last name is invalid.");
        }
    }

    public int getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(int birthDate) throws Exception {
        int currentYear = Year.now().getValue();
        if (birthDate >= 1900 && birthDate <= currentYear) {
            this.birthDate = birthDate;
        } else {
            throw new Exception("Birth date must be from 1900 to current year.");
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) throws Exception {
        if (address != null && address.trim().matches(Constant.REG_TEXT)) {
            this.address = normalizeSpace(address);
        } else {
            throw new Exception("Address is invalid.");
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) throws Exception {
        if (phone != null && phone.trim().matches(Constant.REG_PHONE)) {
            this.phone = phone.trim();
        } else {
            throw new Exception("Phone must contain at least 10 digits.");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception {
        if (email != null && email.trim().matches(Constant.REG_EMAIL)) {
            this.email = email.trim();
        } else {
            throw new Exception("Email is invalid.");
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) throws Exception {
        if (type >= 0 && type <= 2) {
            this.type = type;
        } else {
            throw new Exception("Candidate type is invalid.");
        }
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public boolean matchName(String name) {
        String key = name.toLowerCase().trim();
        return firstName.toLowerCase().contains(key) || lastName.toLowerCase().contains(key);
    }

    private String normalizeSpace(String value) {
        return value.trim().replaceAll("\\s+", " ");
    }

    @Override
    public String toString() {
        return String.format("%s | %d | %s | %s | %s | %d",
                getFullName(), birthDate, address, phone, email, type);
    }
}
