package model;

/**
 * Η κλάση Facility αντιπροσωπεύει μια αθλητική εγκατάσταση.
 * Περιλαμβάνει το όνομα και τη μέγιστη χωρητικότητα της εγκατάστασης.
 */
public class Facility {
    private String nameFacility; // Όνομα εγκατάστασης
    private int maxCapacity; // Μέγιστη χωρητικότητα εγκατάστασης

    public Facility(String nameFacility, int maxCapacity) {
        this.nameFacility = nameFacility;
        this.maxCapacity = maxCapacity;
    }

    public String getNameFacility() {
        return nameFacility;
    }

    public void setNameFacility(String nameFacility) {
        this.nameFacility = nameFacility;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    @Override
    public String toString() {
        return nameFacility;
    }
}
