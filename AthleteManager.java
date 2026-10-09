package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Η κλάση AthleteManager διαχειρίζεται τη λίστα των αθλητών και τις εγγραφές τους.
 * Παρέχει μεθόδους για προσθήκη, ενημέρωση και διαγραφή αθλητών και εγγραφών.
 */
public class AthleteManager {
    private List<Athlete> athletes; // Λίστα με όλους τους αθλητές
    private int nextAthleteCode; // Ο κωδικός που θα δοθεί στον επόμενο αθλητή
    private List<Enrollment> enrollments; // Λίστα με όλες τις εγγραφές

    private static AthleteManager instance;

    private AthleteManager() {
        athletes = new ArrayList<>();
        nextAthleteCode = 1;
        enrollments = new ArrayList<>();
    }

    public static synchronized AthleteManager getInstance() {
        if (instance == null) {
            instance = new AthleteManager();
        }
        return instance;
    }

    public List<Athlete> getAthletes() {
        return athletes;
    }

    public void addAthlete(Athlete athlete) {
        athlete.setCode(nextAthleteCode++); // Ορισμός μοναδικού κωδικού για τον αθλητή
        athletes.add(athlete);
    }

    public void updateAthlete(int index, Athlete athlete) {
        athletes.set(index, athlete); // Ενημέρωση του αθλητή στη συγκεκριμένη θέση της λίστας
    }

    public void removeAthlete(int index) {
        athletes.remove(index); // Αφαίρεση του αθλητή από τη λίστα
    }

    public boolean isDuplicateAthlete(Athlete newAthlete) {
        return athletes.stream().anyMatch(a -> 
            a.getFirstName().equalsIgnoreCase(newAthlete.getFirstName()) &&
            a.getLastName().equalsIgnoreCase(newAthlete.getLastName()) &&
            a.getGender().equalsIgnoreCase(newAthlete.getGender()) &&
            a.getBirthDate().equals(newAthlete.getBirthDate()) &&
            a.getContactInfo().equalsIgnoreCase(newAthlete.getContactInfo()));
    }

    public void addEnrollment(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    public void updateEnrollment(int athleteCode, Enrollment enrollment) {
        for (int i = 0; i < enrollments.size(); i++) {
            if (enrollments.get(i).getAthleteCode() == athleteCode) {
                enrollments.set(i, enrollment); // Αντικατάσταση της υπάρχουσας εγγραφής
                return;
            }
        }
        enrollments.add(enrollment); // Προσθήκη νέας εγγραφής αν δεν υπάρχει ήδη
    }

    public void removeEnrollment(int athleteCode) {
        enrollments.removeIf(enrollment -> enrollment.getAthleteCode() == athleteCode); // Αφαίρεση εγγραφής
    }

    public Enrollment getEnrollment(int athleteCode) {
        return enrollments.stream()
            .filter(enrollment -> enrollment.getAthleteCode() == athleteCode)
            .findFirst()
            .orElse(null); // Επιστροφή της πρώτης εγγραφής που αντιστοιχεί στον κωδικό του αθλητή
    }
}
