package ui;

import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Παράθυρο διαχείρισης κρατήσεων προγραμμάτων εκπαίδευσης.
 * Παρέχει λειτουργίες για δημιουργία, ακύρωση και φιλτράρισμα κρατήσεων.
 */
public class ReservationsManagementWindow {
    private JFrame frame; // Κύριο παράθυρο
    private DefaultTableModel reservationTableModel; // Μοντέλο πίνακα κρατήσεων
    private JTable reservationTable; // Πίνακας κρατήσεων
    private List<TrainingProgramReservation> reservations; // Λίστα κρατήσεων

    public ReservationsManagementWindow() {
        this.reservations = ReservationManager.getInstance().getReservations();
        initialize(); // Αρχικοποίηση παραθύρου
    }

    private void initialize() {
        frame = new JFrame("Διαχείριση Κρατήσεων");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Πλήρης οθόνη

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;

        JComboBox<Athlete> athleteComboBox = new JComboBox<>();
        for (Athlete athlete : AthleteManager.getInstance().getAthletes()) {
            athleteComboBox.addItem(athlete); // Προσθήκη αθλητών στο συνδυαστικό κουτί
        }

        JComboBox<String> trainingProgramComboBox = new JComboBox<>();
        JComboBox<String> sportComboBox = new JComboBox<>(new String[]{
                "Επαγγελματικό Ποδόσφαιρο", "Μπάσκετ", "Βόλει", "Επαγγελματικό Χάντμπολ", "Τέννις", "Επαγγελματικό Πόλο", "Στίβος"
        });

        JComboBox<Facility> facilityComboBox = new JComboBox<>();
        sportComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTrainingPrograms((String) sportComboBox.getSelectedItem(), trainingProgramComboBox);
                updateFacilities((String) sportComboBox.getSelectedItem(), facilityComboBox);
            }
        });

        JComboBox<Coach> coachComboBox = new JComboBox<>();
        for (Coach coach : CoachManager.getInstance().getCoaches()) {
            coachComboBox.addItem(coach); // Προσθήκη προπονητών στο συνδυαστικό κουτί
        }

        JComboBox<Integer> dayCombo = new JComboBox<>();
        JComboBox<String> monthCombo = new JComboBox<>(new String[]{"Ιανουάριος", "Φεβρουάριος", "Μάρτιος", "Απρίλιος", "Μάιος", "Ιούνιος", "Ιούλιος", "Αύγουστος", "Σεπτέμβριος", "Οκτώβριος", "Νοέμβριος", "Δεκέμβριος"});
        int currentYear = LocalDate.now().getYear();
        for (int i = 1; i <= 31; i++) dayCombo.addItem(i);

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Αθλητής:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(athleteComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Άθλημα:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(sportComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Πρόγραμμα Εκπαίδευσης:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(trainingProgramComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(new JLabel("Προπονητής:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(coachComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(new JLabel("Μονάδα:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(facilityComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        inputPanel.add(new JLabel("Ημερομηνία:"), gbc);

        gbc.gridx = 1;
        JPanel birthDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        birthDatePanel.add(dayCombo);
        birthDatePanel.add(monthCombo);
        birthDatePanel.add(new JLabel(String.valueOf(currentYear)));
        inputPanel.add(birthDatePanel, gbc);

        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JComboBox<String> filterComboBox = new JComboBox<>(new String[]{"Όλα", "Αθλητής", "Πρόγραμμα", "Μελλοντικά", "Ιστορικό"});
        filterPanel.add(new JLabel("Φίλτρο:"));
        filterPanel.add(filterComboBox);
        JButton applyFilterButton = new JButton("Εφαρμογή Φίλτρου");

        applyFilterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFilter = (String) filterComboBox.getSelectedItem();
                filterReservations(selectedFilter, (Athlete) athleteComboBox.getSelectedItem(), getSelectedTrainingProgram((String) trainingProgramComboBox.getSelectedItem()));
            }
        });

        filterPanel.add(applyFilterButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton addReservationButton = new JButton("Κράτηση");
        JButton cancelReservationButton = new JButton("Ακύρωση");
        JButton backButton = new JButton("Επιστροφή");

        buttonPanel.add(addReservationButton);
        buttonPanel.add(cancelReservationButton);
        buttonPanel.add(backButton);


        addReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addReservation(athleteComboBox, trainingProgramComboBox, coachComboBox, facilityComboBox, dayCombo, monthCombo, currentYear);
            }
        });

        cancelReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelReservation();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                SystemMain.getMainFrame().setVisible(true);
            }
        });

        String[] columnNames = {"Κωδικός", "Αθλητής", "Πρόγραμμα Εκπαίδευσης", "Προπονητής", "Μονάδα", "Ημερομηνία"};
        reservationTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Απενεργοποίηση επεξεργασίας κελιών
            }
        };
        reservationTable = new JTable(reservationTableModel);


        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < reservationTable.getColumnCount(); i++) {
            reservationTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }


        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) reservationTable.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        reservationTable.getTableHeader().setDefaultRenderer(headerRenderer);

        reservationTable.getTableHeader().setReorderingAllowed(false); // Απενεργοποίηση μετακίνησης στηλών

        JScrollPane scrollPane = new JScrollPane(reservationTable);

        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(inputPanel, BorderLayout.CENTER);
        northPanel.add(filterPanel, BorderLayout.SOUTH);

        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(mainPanel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        updateReservationTableModel(); // Φόρτωση δεδομένων κρατήσεων
    }

    private void updateTrainingPrograms(String selectedSport, JComboBox<String> trainingProgramComboBox) {
        trainingProgramComboBox.removeAllItems();
        for (TrainingProgram trainingProgram : TrainingProgramManager.getInstance().getTrainingPrograms()) {
            if (trainingProgram.getSport().getName().equals(selectedSport)) {
                trainingProgramComboBox.addItem(trainingProgram.getFormattedName());
            }
        }
    }

    private void updateFacilities(String selectedSport, JComboBox<Facility> facilityComboBox) {
        facilityComboBox.removeAllItems();
        FacilityManager facilityManager = FacilityManager.getInstance();
        switch (selectedSport) {
            case "Επαγγελματικό Ποδόσφαιρο":
                facilityComboBox.addItem(facilityManager.getFacilityByName("Γήπεδο Ποδοσφαίρου"));
                break;
            case "Μπάσκετ":
                facilityComboBox.addItem(facilityManager.getFacilityByName("Γήπεδο Μπάσκετ"));
                break;
            case "Βόλει":
                facilityComboBox.addItem(facilityManager.getFacilityByName("Γήπεδο Βόλει 1"));
                facilityComboBox.addItem(facilityManager.getFacilityByName("Γήπεδο Βόλει 2"));
                break;
            case "Επαγγελματικό Χάντμπολ":
                facilityComboBox.addItem(facilityManager.getFacilityByName("Γήπεδο Χάντμπολ"));
                break;
            case "Τέννις":
                facilityComboBox.addItem(facilityManager.getFacilityByName("Γήπεδο Τέννις 1"));
                facilityComboBox.addItem(facilityManager.getFacilityByName("Γήπεδο Τέννις 2"));
                break;
            case "Επαγγελματικό Πόλο":
                facilityComboBox.addItem(facilityManager.getFacilityByName("Κολυμβητήριο"));
                break;
            case "Στίβος":
                facilityComboBox.addItem(facilityManager.getFacilityByName("Στίβος"));
                break;
        }
    }

    private TrainingProgram getSelectedTrainingProgram(String formattedName) {
        for (TrainingProgram trainingProgram : TrainingProgramManager.getInstance().getTrainingPrograms()) {
            if (trainingProgram.getFormattedName().equals(formattedName)) {
                return trainingProgram;
            }
        }
        return null;
    }

    private void addReservation(JComboBox<Athlete> athleteComboBox, JComboBox<String> trainingProgramComboBox, JComboBox<Coach> coachComboBox, JComboBox<Facility> facilityComboBox, JComboBox<Integer> dayCombo, JComboBox<String> monthCombo, int year) {
        Athlete selectedAthlete = (Athlete) athleteComboBox.getSelectedItem();
        String selectedTrainingProgramName = (String) trainingProgramComboBox.getSelectedItem();
        Coach selectedCoach = (Coach) coachComboBox.getSelectedItem();
        Facility selectedFacility = (Facility) facilityComboBox.getSelectedItem();
        int day = (int) dayCombo.getSelectedItem();
        int month = monthCombo.getSelectedIndex() + 1;

        if (selectedAthlete == null || selectedTrainingProgramName == null || selectedCoach == null || selectedFacility == null) {
            JOptionPane.showMessageDialog(frame, "Παρακαλώ επιλέξτε έναν αθλητή, πρόγραμμα εκπαίδευσης, προπονητή και μονάδα.");
            return;
        }

        TrainingProgram selectedTrainingProgram = getSelectedTrainingProgram(selectedTrainingProgramName);

        if (selectedTrainingProgram == null) {
            JOptionPane.showMessageDialog(frame, "Παρακαλώ επιλέξτε ένα έγκυρο πρόγραμμα εκπαίδευσης.");
            return;
        }

        LocalDate reservationDate;
        try {
            reservationDate = LocalDate.of(year, month, day);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Παρακαλώ εισάγετε έγκυρη ημερομηνία.");
            return;
        }

        if (!SubscriptionManager.getInstance().hasSubscription(selectedAthlete.getCode(), selectedTrainingProgram.getSport().getName())) {
            JOptionPane.showMessageDialog(frame, "Ο αθλητής δεν έχει συνδρομή στο επιλεγμένο άθλημα.");
            return;
        }

        LocalDate subscriptionEndDate = SubscriptionManager.getInstance().getSubscriptionEndDate(selectedAthlete.getCode(), selectedTrainingProgram.getSport().getName());

        if (reservationDate.isBefore(LocalDate.now()) || reservationDate.isAfter(subscriptionEndDate)) {
            JOptionPane.showMessageDialog(frame, "Η ημερομηνία κράτησης πρέπει να είναι εντός της περιόδου συνδρομής.");
            return;
        }

        if (ReservationManager.getInstance().hasReservation(selectedAthlete.getCode(), selectedTrainingProgram.getId(), reservationDate.toString())) {
            JOptionPane.showMessageDialog(frame, "Ο αθλητής έχει ήδη κάνει κράτηση για την ίδια ημερομηνία στο ίδιο πρόγραμμα.");
            return;
        }

        if (!isGenderCompatible(selectedAthlete, selectedTrainingProgram)) {
            JOptionPane.showMessageDialog(frame, "Ο αθλητής δεν μπορεί να συμμετάσχει σε αυτό το πρόγραμμα λόγω ασυμβατότητας φύλου.");
            return;
        }

        if (!isExperienceLevelCompatible(selectedAthlete, selectedTrainingProgram)) {
            JOptionPane.showMessageDialog(frame, "Ο αθλητής δεν μπορεί να συμμετάσχει σε αυτό το πρόγραμμα λόγω ασυμβατότητας επιπέδου εμπειρίας.");
            return;
        }

        if (ReservationManager.getInstance().isCoachReserved(selectedCoach.getCode(), selectedTrainingProgram.getTimeOfDay(), reservationDate)) {
            JOptionPane.showMessageDialog(frame, "Ο προπονητής έχει ήδη κράτηση για την ίδια ώρα.");
            return;
        }

        int reservedCount = ReservationManager.getInstance().getReservationsCount(selectedTrainingProgram.getId(), reservationDate);
        if (reservedCount >= selectedTrainingProgram.getFacility().getMaxCapacity()) {
            JOptionPane.showMessageDialog(frame, "Η μονάδα έχει φτάσει το μέγιστο όριο κρατήσεων για αυτή την ώρα.");
            return;
        }

        String reservationCode = selectedTrainingProgram.getId() + "_" + selectedAthlete.getCode() + "_" + reservationDate.toString().replace("-", "");
        TrainingProgramReservation reservation = new TrainingProgramReservation(reservationCode, selectedAthlete, selectedTrainingProgram, selectedCoach, selectedFacility, reservationDate.toString());
        ReservationManager.getInstance().addReservation(reservation);
        updateReservationTableModel();
    }

    private void cancelReservation() {
        int selectedRow = reservationTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Παρακαλώ επιλέξτε μια κράτηση για ακύρωση.");
            return;
        }

        String reservationCode = (String) reservationTableModel.getValueAt(selectedRow, 0);
        LocalDate reservationDate = LocalDate.parse((String) reservationTableModel.getValueAt(selectedRow, 5));
        if (reservationDate.isBefore(LocalDate.now().plusDays(1))) {
            JOptionPane.showMessageDialog(frame, "Οι κρατήσεις μπορούν να ακυρωθούν μέχρι και μία μέρα πριν την προγραμματισμένη ημερομηνία.");
            return;
        }

        ReservationManager.getInstance().removeReservation(reservationCode);
        updateReservationTableModel();
    }

    private void filterReservations(String filter, Athlete athlete, TrainingProgram trainingProgram) {
        List<TrainingProgramReservation> filteredReservations = reservations;

        if (filter.equals("Αθλητής") && athlete != null) {
            filteredReservations = reservations.stream()
                .filter(reservation -> reservation.getAthlete().equals(athlete))
                .collect(Collectors.toList());
        } else if (filter.equals("Πρόγραμμα") && trainingProgram != null) {
            filteredReservations = reservations.stream()
                .filter(reservation -> reservation.getTrainingProgram().equals(trainingProgram))
                .collect(Collectors.toList());
        } else if (filter.equals("Μελλοντικά")) {
            filteredReservations = reservations.stream()
                .filter(reservation -> LocalDate.parse(reservation.getReservationDate()).isAfter(LocalDate.now()))
                .collect(Collectors.toList());
        } else if (filter.equals("Ιστορικό")) {
            filteredReservations = reservations.stream()
                .filter(reservation -> LocalDate.parse(reservation.getReservationDate()).isBefore(LocalDate.now()))
                .collect(Collectors.toList());
        }
        
        updateReservationTableModel(filteredReservations);
    }

    private void updateReservationTableModel() {
        updateReservationTableModel(reservations);
    }

    private void updateReservationTableModel(List<TrainingProgramReservation> reservationsToDisplay) {
        reservationTableModel.setRowCount(0); // Εκκαθάριση πίνακα
        for (TrainingProgramReservation reservation : reservationsToDisplay) {
            Object[] rowData = {
                reservation.getReservationCode(),
                reservation.getAthlete().getFullName(),
                reservation.getTrainingProgram().getFormattedName(),
                reservation.getCoach().getFullName(),
                reservation.getFacility().getNameFacility(),
                reservation.getReservationDate()
            };
            reservationTableModel.addRow(rowData);
        }
    }

    private boolean isGenderCompatible(Athlete athlete, TrainingProgram trainingProgram) {
        String athleteGender = athlete.getGender();
        String programGender = trainingProgram.getGender();
        return programGender.equals("Μεικτό") || programGender.equals(athleteGender);
    }

    private boolean isExperienceLevelCompatible(Athlete athlete, TrainingProgram trainingProgram) {
        int athleteExperienceLevel = athlete.getExperienceLevel();
        String[] experienceRange = trainingProgram.getExperienceLevel().split("-");
        int minExperience = Integer.parseInt(experienceRange[0]);
        int maxExperience = Integer.parseInt(experienceRange[1]);
        return athleteExperienceLevel >= minExperience && athleteExperienceLevel <= maxExperience;
    }
}
