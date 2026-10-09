package model;

import util.PriceList;
import java.time.LocalDate;

/**
 * Η κλάση Enrollment αντιπροσωπεύει μια εγγραφή αθλητή.
 * Περιλαμβάνει τον κωδικό του αθλητή, την ημερομηνία εγγραφής, το κόστος και την έκπτωση.
 * Επίσης, υλοποιεί την διεπαφή PriceList για τον υπολογισμό του συνολικού κόστους.
 */
public class Enrollment implements PriceList {
    private int athleteCode; // Κωδικός αθλητή
    private LocalDate date; // Ημερομηνία εγγραφής
    private double cost; // Κόστος εγγραφής
    private double discount; // Έκπτωση
    private Payment payment; // Πληρωμή

    public Enrollment(int athleteCode, LocalDate date, double cost, double discount) {
        this.athleteCode = athleteCode;
        this.date = date;
        this.cost = Math.round(cost * 100.0) / 100.0; // Στρογγυλοποίηση στο δεύτερο δεκαδικό
        this.discount = Math.round(discount * 100.0) / 100.0; // Στρογγυλοποίηση στο δεύτερο δεκαδικό
    }

    public int getAthleteCode() {
        return athleteCode;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getCost() {
        return cost;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = Math.round(discount * 100.0) / 100.0; // Στρογγυλοποίηση στο δεύτερο δεκαδικό
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public double calculateTotalPrice() {
        return cost - (cost * discount / 100.0); // Υπολογισμός συνολικού κόστους με έκπτωση
    }
}
