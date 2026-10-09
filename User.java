package model;

import java.time.LocalDate;

/**
 * Η κλάση User αντιπροσωπεύει έναν χρήστη με κωδικό, όνομα, επώνυμο, φύλο, ημερομηνία γέννησης και στοιχεία επικοινωνίας.
 */
public class User {
    private int code; // Κωδικός χρήστη
    private String firstName; // Όνομα
    private String lastName; // Επώνυμο
    private String gender; // Φύλο
    private LocalDate birthDate; // Ημερομηνία γέννησης
    private String contactInfo; // Στοιχεία επικοινωνίας

    public User(int code, String firstName, String lastName, String gender, LocalDate birthDate, String contactInfo) {
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.contactInfo = contactInfo;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return getFullName();
    }
}
