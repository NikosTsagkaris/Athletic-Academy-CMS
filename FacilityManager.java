package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Η κλάση FacilityManager διαχειρίζεται τη λίστα των αθλητικών εγκαταστάσεων.
 * Παρέχει μεθόδους για προσθήκη, ενημέρωση και διαγραφή εγκαταστάσεων.
 */
public class FacilityManager {
    private List<Facility> facilities; // Λίστα με όλες τις εγκαταστάσεις

    private static FacilityManager instance;

    private FacilityManager() {
        facilities = new ArrayList<>();
    }

    public static synchronized FacilityManager getInstance() {
        if (instance == null) {
            instance = new FacilityManager();
        }
        return instance;
    }

    public List<Facility> getFacilities() {
        return facilities;
    }

    public void addFacility(Facility facility) {
        facilities.add(facility); // Προσθήκη νέας εγκατάστασης
    }

    public void removeFacility(int index) {
        if (index >= 0 && index < facilities.size()) {
            facilities.remove(index); // Αφαίρεση εγκατάστασης από τη λίστα
        }
    }

    public void updateFacility(int index, Facility facility) {
        if (index >= 0 && index < facilities.size()) {
            facilities.set(index, facility); // Ενημέρωση της εγκατάστασης στη συγκεκριμένη θέση της λίστας
        }
    }

    public Facility getFacilityByName(String name) {
        return facilities.stream()
                .filter(facility -> facility.getNameFacility().equals(name))
                .findFirst()
                .orElse(null); // Επιστροφή της εγκατάστασης με το συγκεκριμένο όνομα
    }
}
