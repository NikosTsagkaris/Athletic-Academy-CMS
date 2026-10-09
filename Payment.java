package model;

import java.time.LocalDate;

/**
 * Η κλάση Payment αντιπροσωπεύει μια πληρωμή.
 * Περιλαμβάνει την ημερομηνία, το ποσό, τον τρόπο πληρωμής και την εγγραφή στην οποία αναφέρεται.
 */
public class Payment {
    private LocalDate date; // Ημερομηνία πληρωμής
    private double amount; // Ποσό πληρωμής
    private String method; // Μέθοδος πληρωμής
    private Enrollment enrollment; // Η εγγραφή στην οποία αναφέρεται η πληρωμή
    private boolean paid; // Κατάσταση πληρωμής

    public Payment(LocalDate date, double amount, String method, Enrollment enrollment) {
        this.date = date;
        this.amount = Math.round(amount * 100.0) / 100.0; // Στρογγυλοποίηση στο δεύτερο δεκαδικό
        this.method = method;
        this.enrollment = enrollment;
        this.paid = false; // Αρχική τιμή πληρωμής ως false
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = Math.round(amount * 100.0) / 100.0; // Στρογγυλοποίηση στο δεύτερο δεκαδικό
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
