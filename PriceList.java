package util;

/**
 * Διεπαφή για λίστα τιμών, που καθορίζει τη μέθοδο υπολογισμού συνολικής τιμής.
 */
public interface PriceList {
    
    /**
     * Υπολογίζει τη συνολική τιμή.
     *
     * @return Η συνολική τιμή ως διπλός αριθμός
     */
    double calculateTotalPrice();
}
