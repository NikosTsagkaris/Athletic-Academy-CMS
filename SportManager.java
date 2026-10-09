package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Η κλάση SportManager διαχειρίζεται τις τιμές για κάθε άθλημα.
 * Παρέχει μεθόδους για να λαμβάνετε το κόστος ενός αθλήματος.
 */
public class SportManager {
    private static SportManager instance;
    private Map<String, Double> sportCosts; // Χάρτης που περιέχει τις τιμές των αθλημάτων

    private SportManager() {
        sportCosts = new HashMap<>();
        sportCosts.put("Επαγγελματικό Ποδόσφαιρο", 100.0);
        sportCosts.put("Μπάσκετ", 80.0);
        sportCosts.put("Βόλει", 80.0);
        sportCosts.put("Επαγγελματικό Χάντμπολ", 65.0);
        sportCosts.put("Τέννις", 90.0);
        sportCosts.put("Επαγγελματικό Πόλο", 75.0);
        sportCosts.put("Στίβος", 70.0);
    }

    public static synchronized SportManager getInstance() {
        if (instance == null) {
            instance = new SportManager();
        }
        return instance;
    }

    public double getSportCost(String sportName) {
        return Math.round(sportCosts.getOrDefault(sportName, 0.0) * 100.0) / 100.0; // Στρογγυλοποίηση στο δεύτερο δεκαδικό
    }
}
