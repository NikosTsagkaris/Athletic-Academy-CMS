package ui;

import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Παράθυρο διαχείρισης αθλητών. Παρέχει λειτουργίες για καταχώριση, ενημέρωση, διαγραφή αθλητών και διαχείριση πληρωμών.
 */
public class AthleteManagementWindow {
    private JFrame frame; // Το κύριο παράθυρο της εφαρμογής
    private DefaultTableModel athleteTableModel; // Το μοντέλο για τον πίνακα αθλητών
    private List<Athlete> athletes; // Λίστα με όλους τους αθλητές

    private JTextField codeField; // Πεδία εισαγωγής δεδομένων για τον αθλητή
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JComboBox<String> genderCombo;
    private JComboBox<Integer> dayCombo;
    private JComboBox<String> monthCombo;
    private JComboBox<Integer> yearCombo;
    private JTextField contactInfoField;
    private JTable athleteTable; // Πίνακας που εμφανίζει τους αθλητές
    private JCheckBox professionalCheckBox;
    private JComboBox<Integer> experienceLevelCombo;

    public AthleteManagementWindow() {
        this.athletes = AthleteManager.getInstance().getAthletes(); // Φορτώνει τους αθλητές από τον διαχειριστή αθλητών
        initialize(); // Καλεί την μέθοδο αρχικοποίησης του παραθύρου
    }

    private void initialize() {
        frame = new JFrame("Διαχείριση Αθλητών");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;

        codeField = new JTextField(20);
        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        genderCombo = new JComboBox<>(new String[]{"Άνδρας", "Γυναίκα", "Άλλο"});

        setupDateCombos(); // Αρχικοποίηση των πεδίων ημερομηνίας
        contactInfoField = new JTextField(20);
        professionalCheckBox = new JCheckBox("Επαγγελματίας");
        experienceLevelCombo = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Κωδικός:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(codeField, gbc);
        codeField.setEditable(false); // Το πεδίο κωδικού δεν είναι επεξεργάσιμο

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Όνομα:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Επώνυμο:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(new JLabel("Φύλο:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(genderCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(new JLabel("Ημερομηνία Γέννησης:"), gbc);

        gbc.gridx = 1;
        JPanel birthDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        birthDatePanel.add(dayCombo);
        birthDatePanel.add(monthCombo);
        birthDatePanel.add(yearCombo);
        inputPanel.add(birthDatePanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        inputPanel.add(new JLabel("Στοιχεία Επικοινωνίας:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(contactInfoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        inputPanel.add(new JLabel("Επαγγελματίας:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(professionalCheckBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        inputPanel.add(new JLabel("Επίπεδο Εμπειρίας:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(experienceLevelCombo, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton registerButton = new JButton("Καταχώριση");
        JButton updateButton = new JButton("Ενημέρωση");
        JButton deleteButton = new JButton("Διαγραφή");
        JButton paymentButton = new JButton("Πληρωμή");
        JButton backButton = new JButton("Επιστροφή");

        buttonPanel.add(registerButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(paymentButton);
        buttonPanel.add(backButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerAthlete(); // Καλεί την μέθοδο για καταχώριση νέου αθλητή
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAthlete(); // Καλεί την μέθοδο για ενημέρωση υπάρχοντος αθλητή
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAthlete(); // Καλεί την μέθοδο για διαγραφή αθλητή
            }
        });

        paymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processPayment(); // Καλεί την μέθοδο για επεξεργασία πληρωμής
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Κλείνει το παράθυρο
                SystemMain.getMainFrame().setVisible(true); // Εμφανίζει το κύριο παράθυρο της εφαρμογής
            }
        });

        String[] columnNames = {"Κωδικός", "Όνομα", "Επώνυμο", "Φύλο", "Ημερομηνία Γέννησης", "Στοιχεία Επικοινωνίας", "Επαγγελματίας", "Επίπεδο Εμπειρίας", "Κόστος Εγγραφής", "Εξοφλημένο", "Μετέπειτα Έκπτωση Αθλητή"};
        athleteTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Οι τιμές των κελιών του πίνακα δεν είναι επεξεργάσιμες
            }
        };
        athleteTable = new JTable(athleteTableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Κεντράρει το περιεχόμενο των κελιών του πίνακα
        for (int i = 0; i < athleteTable.getColumnCount(); i++) {
            athleteTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) athleteTable.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Κεντράρει το περιεχόμενο των κελιών της επικεφαλίδας του πίνακα
        athleteTable.getTableHeader().setDefaultRenderer(headerRenderer);

        athleteTable.getTableHeader().setReorderingAllowed(false); // Απενεργοποιεί την δυνατότητα αλλαγής της σειράς των στηλών του πίνακα

        setColumnWidthsAndRenderers(centerRenderer); // Ορισμός πλάτους και renderer για τις στήλες του πίνακα

        athleteTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    loadSelectedAthleteData(); // Φορτώνει τα δεδομένα του επιλεγμένου αθλητή στα πεδία εισαγωγής
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(athleteTable);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(mainPanel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        firstNameField.requestFocusInWindow(); // Εστιάζει το πεδίο εισαγωγής του ονόματος

        updateAthleteTableModel(); // Ενημερώνει το μοντέλο του πίνακα με τα δεδομένα των αθλητών
    }

    private void setColumnWidthsAndRenderers(DefaultTableCellRenderer centerRenderer) {
        TableColumn codeColumn = athleteTable.getColumnModel().getColumn(0);
        codeColumn.setPreferredWidth(60); // Ορίζει το προτιμώμενο πλάτος της στήλης
        codeColumn.setMinWidth(60); // Ορίζει το ελάχιστο πλάτος της στήλης
        codeColumn.setMaxWidth(60); // Ορίζει το μέγιστο πλάτος της στήλης

        TableColumn genderColumn = athleteTable.getColumnModel().getColumn(3);
        genderColumn.setPreferredWidth(60);
        genderColumn.setMinWidth(60);
        genderColumn.setMaxWidth(60);

        TableColumn professionalColumn = athleteTable.getColumnModel().getColumn(6);
        professionalColumn.setPreferredWidth(100);
        professionalColumn.setMinWidth(100);
        professionalColumn.setMaxWidth(100);
        professionalColumn.setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            protected void setValue(Object value) {
                if (value instanceof Boolean) {
                    boolean isProfessional = (Boolean) value;
                    setText(isProfessional ? "Ναι" : "Όχι");
                    setHorizontalAlignment(SwingConstants.CENTER);
                } else {
                    super.setValue(value);
                }
            }
        });

        TableColumn expirTableColumn = athleteTable.getColumnModel().getColumn(7);
        expirTableColumn.setPreferredWidth(120);
        expirTableColumn.setMinWidth(120);
        expirTableColumn.setMaxWidth(120);

        TableColumn cosTableColumn = athleteTable.getColumnModel().getColumn(8);
        cosTableColumn.setPreferredWidth(120);
        cosTableColumn.setMinWidth(120);
        cosTableColumn.setMaxWidth(120);

        TableColumn paidColumn = athleteTable.getColumnModel().getColumn(9);
        paidColumn.setPreferredWidth(75);
        paidColumn.setMinWidth(75);
        paidColumn.setMaxWidth(75);
        paidColumn.setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            protected void setValue(Object value) {
                if (value instanceof Boolean) {
                    boolean isPaid = (Boolean) value;
                    setText(isPaid ? "Ναι" : "Όχι");
                    setHorizontalAlignment(SwingConstants.CENTER);
                } else {
                    super.setValue(value);
                }
            }
        });

        professionalColumn.setCellRenderer(centerRenderer); // Ορίζει τον renderer για την στήλη professionalColumn
        paidColumn.setCellRenderer(centerRenderer); // Ορίζει τον renderer για την στήλη paidColumn
    }

    private void setupDateCombos() {
        dayCombo = new JComboBox<>();
        monthCombo = new JComboBox<>(new String[]{"Ιανουάριος", "Φεβρουάριος", "Μάρτιος", "Απρίλιος", "Μάιος", "Ιούνιος", "Ιούλιος", "Αύγουστος", "Σεπτέμβριος", "Οκτώβριος", "Νοέμβριος", "Δεκέμβριος"});
        yearCombo = new JComboBox<>();
        for (int i = 1; i <= 31; dayCombo.addItem(i++));
        for (int i = 1924; i <= 2024; yearCombo.addItem(i++));
    }

    private void loadSelectedAthleteData() {
        int selectedRow = athleteTable.getSelectedRow(); // Λαμβάνει την επιλεγμένη γραμμή από τον πίνακα
        if (selectedRow == -1) {
            return;
        }
        Athlete selectedAthlete = athletes.get(selectedRow); // Λαμβάνει τον επιλεγμένο αθλητή από την λίστα

        codeField.setText(String.valueOf(selectedAthlete.getCode()));
        firstNameField.setText(selectedAthlete.getFirstName());
        lastNameField.setText(selectedAthlete.getLastName());
        genderCombo.setSelectedItem(selectedAthlete.getGender());

        LocalDate birthDate = selectedAthlete.getBirthDate();
        dayCombo.setSelectedItem(birthDate.getDayOfMonth());
        monthCombo.setSelectedIndex(birthDate.getMonthValue() - 1);
        yearCombo.setSelectedItem(birthDate.getYear());

        contactInfoField.setText(selectedAthlete.getContactInfo());
        professionalCheckBox.setSelected(selectedAthlete.isProfessional());
        experienceLevelCombo.setSelectedItem(selectedAthlete.getExperienceLevel());
    }

    private void registerAthlete() {
        Athlete newAthlete = getAthleteFromFields(); // Δημιουργεί έναν νέο αθλητή με τα δεδομένα από τα πεδία εισαγωγής
        if (newAthlete == null) {
            return;
        }
    
        if (AthleteManager.getInstance().isDuplicateAthlete(newAthlete)) {
            JOptionPane.showMessageDialog(frame, "Υπάρχει ήδη καταχωρημένος αθλητής με τα ίδια στοιχεία.");
            return;
        }
    
        AthleteManager.getInstance().addAthlete(newAthlete); // Προσθέτει τον νέο αθλητή στην λίστα
        int cost = calculateCost(newAthlete.isProfessional());
        double discount = calculateDiscount(newAthlete.getExperienceLevel(), newAthlete.isProfessional());
    
        Enrollment enrollment = new Enrollment(newAthlete.getCode(), LocalDate.now(), cost, discount); // Δημιουργεί μια νέα εγγραφή για τον αθλητή
        Payment payment = new Payment(LocalDate.now(), cost, "Cash", enrollment); // Δημιουργεί μια νέα πληρωμή για την εγγραφή
        enrollment.setPayment(payment); // Συνδέει την πληρωμή με την εγγραφή
    
        AthleteManager.getInstance().addEnrollment(enrollment); // Προσθέτει την εγγραφή στην λίστα εγγραφών
    
        
        updateAthleteTableModel(); // Ενημερώνει το μοντέλο του πίνακα αθλητών
        clearFields(); // Καθαρίζει τα πεδία εισαγωγής
    }
    

    private void updateAthlete() {
        int selectedRow = athleteTable.getSelectedRow(); // Λαμβάνει την επιλεγμένη γραμμή από τον πίνακα
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Παρακαλώ επιλέξτε έναν αθλητή για ενημέρωση.");
            return;
        }

        Athlete updatedAthlete = getAthleteFromFields(); // Δημιουργεί έναν ενημερωμένο αθλητή με τα δεδομένα από τα πεδία εισαγωγής
        if (updatedAthlete == null) {
            return;
        }

        updatedAthlete.setCode(athletes.get(selectedRow).getCode()); // Διατηρεί τον κωδικό του αθλητή που ενημερώνεται

        for (int i = 0; i < athletes.size(); i++) {
            if (i != selectedRow && AthleteManager.getInstance().isDuplicateAthlete(updatedAthlete)) {
                JOptionPane.showMessageDialog(frame, "Υπάρχει ήδη καταχωρημένος αθλητής με τα ίδια στοιχεία.");
                return;
            }
        }

        AthleteManager.getInstance().updateAthlete(selectedRow, updatedAthlete); // Ενημερώνει τον αθλητή στην λίστα

        int cost = calculateCost(updatedAthlete.isProfessional());
        double discount = calculateDiscount(updatedAthlete.getExperienceLevel(), updatedAthlete.isProfessional());

        Enrollment enrollment = new Enrollment(updatedAthlete.getCode(), LocalDate.now(), cost, discount); // Δημιουργεί μια νέα εγγραφή για τον ενημερωμένο αθλητή
        Payment payment = new Payment(LocalDate.now(), cost, "Cash", enrollment); // Δημιουργεί μια νέα πληρωμή για την εγγραφή
        enrollment.setPayment(payment); // Συνδέει την πληρωμή με την εγγραφή

        AthleteManager.getInstance().updateEnrollment(updatedAthlete.getCode(), enrollment); // Ενημερώνει την εγγραφή στην λίστα εγγραφών

        updateAthleteList(); // Ενημερώνει τη λίστα αθλητών
        updateAthleteTableModel(); // Ενημερώνει το μοντέλο του πίνακα αθλητών
        clearFields(); // Καθαρίζει τα πεδία εισαγωγής
    }

    private void deleteAthlete() {
        int selectedRow = athleteTable.getSelectedRow(); // Λαμβάνει την επιλεγμένη γραμμή από τον πίνακα
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Παρακαλώ επιλέξτε έναν αθλητή για διαγραφή.");
            return;
        }

        int athleteCode = athletes.get(selectedRow).getCode(); // Λαμβάνει τον κωδικό του αθλητή που θα διαγραφεί
        AthleteManager.getInstance().removeAthlete(selectedRow); // Διαγράφει τον αθλητή από την λίστα
        AthleteManager.getInstance().removeEnrollment(athleteCode); // Διαγράφει την εγγραφή του αθλητή

        this.athletes = AthleteManager.getInstance().getAthletes(); // Ενημερώνει την λίστα αθλητών

        updateAthleteList(); // Ενημερώνει τη λίστα αθλητών
        updateAthleteTableModel(); // Ενημερώνει το μοντέλο του πίνακα αθλητών
        clearFields(); // Καθαρίζει τα πεδία εισαγωγής
    }

    private void processPayment() {
        int selectedRow = athleteTable.getSelectedRow(); // Λαμβάνει την επιλεγμένη γραμμή από τον πίνακα
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Παρακαλώ επιλέξτε έναν αθλητή για πληρωμή.");
            return;
        }

        Athlete selectedAthlete = athletes.get(selectedRow); // Λαμβάνει τον επιλεγμένο αθλητή από την λίστα
        int cost = calculateCost(selectedAthlete.isProfessional());

        Object[] options = {"Επιβεβαίωση", "Άκυρο"};

        int confirm = JOptionPane.showOptionDialog(frame,
                "Θέλετε να πραγματοποιηθεί η πληρωμή της εγγραφής για τον " + selectedAthlete.toString() + " που έχει κόστος " + cost + " €;",
                "Επιβεβαίωση Πληρωμής",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (confirm == JOptionPane.YES_OPTION) {
            selectedAthlete.setPaid(true); // Θέτει την εγγραφή του αθλητή ως πληρωμένη
            updateAthleteList(); // Ενημερώνει τη λίστα αθλητών
            updateAthleteTableModel(); // Ενημερώνει το μοντέλο του πίνακα αθλητών
        }
    }

    private Athlete getAthleteFromFields() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String gender = (String) genderCombo.getSelectedItem();

        int day = (int) dayCombo.getSelectedItem();
        int month = monthCombo.getSelectedIndex() + 1;
        int year = (int) yearCombo.getSelectedItem();

        String contactInfo = contactInfoField.getText();
        boolean isProfessional = professionalCheckBox.isSelected();
        int experienceLevel = (int) experienceLevelCombo.getSelectedItem();

        if (firstName.isEmpty() || lastName.isEmpty() || contactInfo.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Παρακαλώ συμπληρώστε όλα τα πεδία.");
            return null;
        }

        LocalDate birthDate;
        try {
            birthDate = LocalDate.of(year, month, day);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Παρακαλώ εισάγετε έγκυρη ημερομηνία.");
            return null;
        }

        return new Athlete(0, firstName, lastName, gender, birthDate, contactInfo, isProfessional, experienceLevel); // Δημιουργεί έναν νέο αθλητή με τα δεδομένα από τα πεδία εισαγωγής
    }

    private int calculateCost(boolean isProfessional) {
        return isProfessional ? 20 : 50; // Υπολογίζει το κόστος εγγραφής ανάλογα με το αν ο αθλητής είναι επαγγελματίας
    }

    private double calculateDiscount(int experienceLevel, boolean isProfessional) {
        double discount = 0.0;
        if (experienceLevel == 1 || experienceLevel == 2) {
            discount = 10.0;
        } else if (experienceLevel == 3 || experienceLevel == 4) {
            discount = 20.0;
        } else if (experienceLevel == 5) {
            discount = 30.0;
        }

        if (isProfessional) {
            discount = discount + 0.2 * (100 - discount); // Υπολογίζει επιπλέον έκπτωση για επαγγελματίες
        }
        return discount;
    }

    private void updateAthleteList() {
        this.athletes = AthleteManager.getInstance().getAthletes();
    }

    private void updateAthleteTableModel() {
        athleteTableModel.setRowCount(0); // Καθαρίζει τον πίνακα
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (Athlete athlete : athletes) {
            int cost = calculateCost(athlete.isProfessional());
            double discount = calculateDiscount(athlete.getExperienceLevel(), athlete.isProfessional());
            Object[] rowData = {
                athlete.getCode(),
                athlete.getFirstName(),
                athlete.getLastName(),
                athlete.getGender(),
                athlete.getBirthDate().format(formatter),
                athlete.getContactInfo(),
                athlete.isProfessional() ? "Ναι" : "Όχι",
                athlete.getExperienceLevel(),
                String.format("%.2f €", (double) cost), // Corrected formatting issue
                athlete.isPaid() ? "Ναι" : "Όχι",
                String.format("%.2f %%", discount)
            };
            athleteTableModel.addRow(rowData); // Προσθέτει τον αθλητή στον πίνακα
        }
    }
    

    private void clearFields() {
        codeField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        genderCombo.setSelectedIndex(0);
        dayCombo.setSelectedIndex(0);
        monthCombo.setSelectedIndex(0);
        yearCombo.setSelectedIndex(0);
        contactInfoField.setText("");
        professionalCheckBox.setSelected(false);
        experienceLevelCombo.setSelectedIndex(0);
        firstNameField.requestFocusInWindow();
    }
}
