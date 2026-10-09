package model;

import java.time.LocalDate;
import java.util.List;

/**
 * Η κλάση Coach αντιπροσωπεύει έναν προπονητή.
 * Επεκτείνει την κλάση User και προσθέτει πληροφορίες όπως το άθλημα και τα πτυχία του προπονητή.
 */
public class Coach extends User {
    private Sport sport; // Το άθλημα που ειδικεύεται ο προπονητής
    private List<String> degrees; // Λίστα με τα πτυχία του προπονητή

    public Coach(int code, String firstName, String lastName, String gender, LocalDate birthDate, String contactInfo, Sport sport, List<String> degrees) {
        super(code, firstName, lastName, gender, birthDate, contactInfo);
        this.sport = sport;
        this.degrees = degrees;
    }

    public Sport getSport() {
        return sport;
    }

    public List<String> getDegrees() {
        return degrees;
    }
}
