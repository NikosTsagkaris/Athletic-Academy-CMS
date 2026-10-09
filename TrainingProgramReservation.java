package model;

/**
 * Η κλάση TrainingProgramReservation αντιπροσωπεύει μια κράτηση προγράμματος εκπαίδευσης.
 * Περιλαμβάνει τον κωδικό κράτησης, τον αθλητή, το πρόγραμμα εκπαίδευσης, τον προπονητή, τη μονάδα και την ημερομηνία κράτησης.
 */
public class TrainingProgramReservation {
    private String reservationCode; // Κωδικός κράτησης
    private Athlete athlete; // Ο αθλητής
    private TrainingProgram trainingProgram; // Πρόγραμμα εκπαίδευσης
    private Coach coach; // Προπονητής
    private Facility facility; // Μονάδα
    private String reservationDate; // Ημερομηνία κράτησης

    public TrainingProgramReservation(String reservationCode, Athlete athlete, TrainingProgram trainingProgram, Coach coach, Facility facility, String reservationDate) {
        this.reservationCode = reservationCode;
        this.athlete = athlete;
        this.trainingProgram = trainingProgram;
        this.coach = coach;
        this.facility = facility;
        this.reservationDate = reservationDate;
    }

    public String getReservationCode() {
        return reservationCode;
    }

    public void setReservationCode(String reservationCode) {
        this.reservationCode = reservationCode;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }

    public TrainingProgram getTrainingProgram() {
        return trainingProgram;
    }

    public void setTrainingProgram(TrainingProgram trainingProgram) {
        this.trainingProgram = trainingProgram;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }
}
