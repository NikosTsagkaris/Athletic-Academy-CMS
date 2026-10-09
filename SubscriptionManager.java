package model;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

/**
 * Η κλάση SubscriptionManager διαχειρίζεται τις συνδρομές των αθλητών.
 * Παρέχει μεθόδους για προσθήκη, αφαίρεση και έλεγχο συνδρομών.
 */
public class SubscriptionManager {
    private List<Subscription> subscriptions; // Λίστα με όλες τις συνδρομές
    private int nextSubscriptionCode;

    private static SubscriptionManager instance;

    private SubscriptionManager() {
        subscriptions = new ArrayList<>();
        nextSubscriptionCode = 1;
    }

    public static synchronized SubscriptionManager getInstance() {
        if (instance == null) {
            instance = new SubscriptionManager();
        }
        return instance;
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void addSubscription(Subscription subscription) {
        subscriptions.add(subscription); // Προσθήκη νέας συνδρομής
    }

    public void removeSubscription(String code) {
        subscriptions.removeIf(subscription -> subscription.getCode().equals(code)); // Αφαίρεση συνδρομής με βάση τον κωδικό
    }

    public boolean hasSubscription(int athleteCode, String sport) {
        return subscriptions.stream()
                .anyMatch(subscription -> subscription.getAthlete().getCode() == athleteCode &&
                        subscription.getSport().equals(sport)); // Έλεγχος αν υπάρχει συνδρομή για τον αθλητή και το άθλημα
    }

    public LocalDate getSubscriptionEndDate(int athleteCode, String sport) {
        for (Subscription subscription : subscriptions) {
            if (subscription.getAthlete().getCode() == athleteCode &&
                    subscription.getSport().equals(sport)) {
                return subscription.getEndDate(); // Επιστροφή της ημερομηνίας λήξης συνδρομής
            }
        }
        return null;
    }

    public int getNextSubscriptionCode() {
        return nextSubscriptionCode;
    }

    public String generateSubscriptionCode(int athleteCode, String sport) {
        int sportCode = getSportCode(sport);
        return athleteCode + "_" + sportCode + "_" + (nextSubscriptionCode++); // Δημιουργία κωδικού συνδρομής
    }

    private int getSportCode(String sport) {
        switch (sport) {
            case "Επαγγελματικό Ποδόσφαιρο":
                return 1;
            case "Μπάσκετ":
                return 2;
            case "Βόλει":
                return 3;
            case "Επαγγελματικό Χάντμπολ":
                return 4;
            case "Τέννις":
                return 5;
            case "Επαγγελματικό Πόλο":
                return 6;
            case "Στίβος":
                return 7;
            default:
                return 0;
        }
    }
}
