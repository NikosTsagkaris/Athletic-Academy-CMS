package model;

/**
 * Η κλάση TrainingProgram αντιπροσωπεύει ένα πρόγραμμα εκπαίδευσης.
 * Περιλαμβάνει τον κωδικό, το άθλημα, τη μονάδα, την τιμή, την ώρα της ημέρας, το φύλο και το επίπεδο εμπειρίας.
 */
public class TrainingProgram {
    private int id; // Κωδικός προγράμματος
    private Sport sport; // Άθλημα
    private Facility facility; // Μονάδα
    private double price; // Τιμή
    private String timeOfDay; // Ώρα της ημέρας
    private String gender; // Φύλο
    private String experienceLevel; // Επίπεδο εμπειρίας

    public TrainingProgram(int id, Sport sport, Facility facility, double price, String timeOfDay, String gender, String experienceLevel) {
        this.id = id;
        this.sport = sport;
        this.facility = facility;
        this.price = Math.round(price * 100.0) / 100.0; // Στρογγυλοποίηση στο δεύτερο δεκαδικό
        this.timeOfDay = timeOfDay;
        this.gender = gender;
        this.experienceLevel = experienceLevel;
    }

    public int getId() {
        return id;
    }

    public Sport getSport() {
        return sport;
    }

    public Facility getFacility() {
        return facility;
    }

    public double getPrice() {
        return price;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public String getGender() {
        return gender;
    }

    public String getExperienceLevel() {
        return experienceLevel;
    }

    public String getFormattedName() {
        return sport.getName() + " - " + timeOfDay + " - " + gender + " - " + experienceLevel;
    }
}
