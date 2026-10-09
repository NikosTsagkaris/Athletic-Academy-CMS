package util;

import javax.swing.table.DefaultTableModel;

/**
 * Μοντέλο πίνακα που δεν επιτρέπει την επεξεργασία των κελιών.
 */
public class NonEditableTableModel extends DefaultTableModel {
    
    /**
     * Κατασκευαστής που δημιουργεί ένα μοντέλο πίνακα με δεδομένα στήλης και αριθμό σειρών.
     *
     * @param columnNames Ονόματα στηλών
     * @param rowCount Αριθμός σειρών
     */
    public NonEditableTableModel(Object[] columnNames, int rowCount) {
        super(columnNames, rowCount); // Κλήση στον κατασκευαστή της υπερκλάσης
    }

    /**
     * Καθορίζει αν ένα κελί είναι επεξεργάσιμο.
     *
     * @param row Η γραμμή του κελιού
     * @param column Η στήλη του κελιού
     * @return Πάντα επιστρέφει false, καθιστώντας όλα τα κελιά μη επεξεργάσιμα
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Κάνει τα κελιά μη επεξεργάσιμα
    }
}
