package model;

/**
 * Η κλάση Sport αντιπροσωπεύει ένα άθλημα.
 * Περιλαμβάνει το όνομα του αθλήματος και αν είναι επαγγελματικό.
 */
public class Sport {
    private String name; // Όνομα αθλήματος
    private boolean isProfessional; // Αν το άθλημα είναι επαγγελματικό

    public Sport(String name, boolean isProfessional) {
        this.name = name;
        this.isProfessional = isProfessional;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isProfessional() {
        return isProfessional;
    }

    public void setProfessional(boolean professional) {
        isProfessional = professional;
    }
}
