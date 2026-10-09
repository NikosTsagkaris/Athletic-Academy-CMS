package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Η κλάση ReservationManager διαχειρίζεται τις κρατήσεις προγραμμάτων εκπαίδευσης.
 * Παρέχει μεθόδους για προσθήκη, ενημέρωση και διαγραφή κρατήσεων.
 */
public class ReservationManager {
    private List<TrainingProgramReservation> reservations; // Λίστα με όλες τις κρατήσεις
    private static ReservationManager instance;

    private ReservationManager() {
        reservations = new ArrayList<>();
    }

    public static synchronized ReservationManager getInstance() {
        if (instance == null) {
            instance = new ReservationManager();
        }
        return instance;
    }

    public List<TrainingProgramReservation> getReservations() {
        return reservations;
    }

    public void addReservation(TrainingProgramReservation reservation) {
        reservations.add(reservation); // Προσθήκη νέας κράτησης
    }

    public void removeReservation(String reservationCode) {
        reservations.removeIf(reservation -> reservation.getReservationCode().equals(reservationCode)); // Αφαίρεση κράτησης με βάση τον κωδικό
    }

    public boolean hasReservation(int athleteCode, int trainingProgramId, String date) {
        return reservations.stream()
                .anyMatch(reservation -> reservation.getAthlete().getCode() == athleteCode &&
                        reservation.getTrainingProgram().getId() == trainingProgramId &&
                        reservation.getReservationDate().equals(date)); // Έλεγχος αν υπάρχει κράτηση
    }

    public boolean isCoachReserved(int coachCode, String timeOfDay, LocalDate date) {
        return reservations.stream()
                .anyMatch(reservation -> reservation.getCoach().getCode() == coachCode &&
                        reservation.getTrainingProgram().getTimeOfDay().equals(timeOfDay) &&
                        LocalDate.parse(reservation.getReservationDate()).equals(date)); // Έλεγχος διαθεσιμότητας προπονητή
    }

    public int getReservationsCount(int trainingProgramId, LocalDate date) {
        return (int) reservations.stream()
                .filter(reservation -> reservation.getTrainingProgram().getId() == trainingProgramId &&
                        LocalDate.parse(reservation.getReservationDate()).equals(date))
                .count(); // Μέτρηση κρατήσεων για το πρόγραμμα εκπαίδευσης
    }
}
