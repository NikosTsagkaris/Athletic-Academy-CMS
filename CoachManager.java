package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Η κλάση CoachManager διαχειρίζεται τη λίστα των προπονητών.
 * Παρέχει μεθόδους για προσθήκη, ενημέρωση και διαγραφή προπονητών.
 */
public class CoachManager {
    private List<Coach> coaches; // Λίστα με όλους τους προπονητές
    private int nextCoachCode; // Ο κωδικός που θα δοθεί στον επόμενο προπονητή

    private static CoachManager instance;

    private CoachManager() {
        coaches = new ArrayList<>();
        nextCoachCode = 1;
    }

    public static synchronized CoachManager getInstance() {
        if (instance == null) {
            instance = new CoachManager();
        }
        return instance;
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public void addCoach(Coach coach) {
        coach.setCode(nextCoachCode++); // Ορισμός μοναδικού κωδικού για τον προπονητή
        coaches.add(coach);
    }

    public void removeCoach(int index) {
        if (index >= 0 && index < coaches.size()) {
            coaches.remove(index); // Αφαίρεση προπονητή από τη λίστα
        }
    }

    public void updateCoach(int index, Coach coach) {
        if (index >= 0 && index < coaches.size()) {
            coaches.set(index, coach); // Ενημέρωση του προπονητή στη συγκεκριμένη θέση της λίστας
        }
    }

    public boolean isDuplicateCoach(Coach coach) {
        for (Coach c : coaches) {
            if (c.getFirstName().equals(coach.getFirstName()) &&
                c.getLastName().equals(coach.getLastName()) &&
                c.getBirthDate().equals(coach.getBirthDate())) {
                return true; // Έλεγχος για διπλότυπο προπονητή
            }
        }
        return false;
    }
}
