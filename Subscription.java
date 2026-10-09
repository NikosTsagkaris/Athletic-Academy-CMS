package model;

import java.time.LocalDate;

/**
 * Η κλάση Subscription αντιπροσωπεύει μια συνδρομή αθλητή σε πρόγραμμα εκπαίδευσης.
 * Περιλαμβάνει τον κωδικό συνδρομής, τον αθλητή, το άθλημα, τις ημερομηνίες έναρξης και λήξης, και την πληρωμή.
 */
public class Subscription {
    private String code; // Κωδικός συνδρομής
    private Athlete athlete; // Ο αθλητής
    private String sport; // Το άθλημα
    private LocalDate startDate; // Ημερομηνία έναρξης
    private LocalDate endDate; // Ημερομηνία λήξης
    private Payment payment; // Πληρωμή

    public Subscription(String code, Athlete athlete, String sport, LocalDate startDate, LocalDate endDate, Payment payment) {
        this.code = code;
        this.athlete = athlete;
        this.sport = sport;
        this.startDate = startDate;
        this.endDate = endDate;
        this.payment = payment;
    }

    public String getCode() {
        return code;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public String getSport() {
        return sport;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Payment getPayment() {
        return payment;
    }
}
