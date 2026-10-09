package model;

import java.time.LocalDate;

/**
 * Η κλάση Athlete αντιπροσωπεύει έναν αθλητή.
 * Επεκτείνει την κλάση User και προσθέτει πληροφορίες όπως αν είναι επαγγελματίας και το επίπεδο εμπειρίας του.
 */
public class Athlete extends User {
    private boolean isProfessional; // Αν ο αθλητής είναι επαγγελματίας
    private int experienceLevel; // Επίπεδο εμπειρίας του αθλητή
    private boolean paid; // Αν ο αθλητής έχει πληρώσει

    public Athlete(int code, String firstName, String lastName, String gender, LocalDate birthDate, String contactInfo, boolean isProfessional, int experienceLevel) {
        super(code, firstName, lastName, gender, birthDate, contactInfo);
        this.isProfessional = isProfessional;
        this.experienceLevel = experienceLevel;
        this.paid = false; // Αρχικοποίηση ως μη πληρωμένο
    }

    public boolean isProfessional() {
        return isProfessional;
    }

    public int getExperienceLevel() {
        return experienceLevel;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
