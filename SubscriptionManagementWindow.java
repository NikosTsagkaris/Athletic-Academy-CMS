package ui;

import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Παράθυρο διαχείρισης συνδρομών. Παρέχει λειτουργίες για δημιουργία, ακύρωση και πληρωμή συνδρομών.
 */
public class SubscriptionManagementWindow {
    private JFrame frame; // Κύριο παράθυρο
    private DefaultTableModel subscriptionTableModel; // Μοντέλο πίνακα συνδρομών
    private List<Subscription> subscriptions; // Λίστα συνδρομών

    private JComboBox<Athlete> athleteComboBox; // Συνδυαστικό κουτί για επιλογή αθλητή
    private JComboBox<String> sportComboBox; // Συνδυαστικό κουτί για επιλογή αθλήματος
    private JTable subscriptionTable; // Πίνακας συνδρομών

    public SubscriptionManagementWindow() {
        this.subscriptions = SubscriptionManager.getInstance().getSubscriptions();
        initialize(); // Αρχικοποίηση παραθύρου
    }

    private void initialize() {
        frame = new JFrame("Διαχείριση Συνδρομών");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Πλήρης οθόνη

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;

        athleteComboBox = new JComboBox<>();
        for (Athlete athlete : AthleteManager.getInstance().getAthletes()) {
            athleteComboBox.addItem(athlete); // Προσθήκη αθλητών στο συνδυαστικό κουτί
        }

        sportComboBox = new JComboBox<>(new String[]{
                "Επαγγελματικό Ποδόσφαιρο", "Μπάσκετ", "Βόλει", "Επαγγελματικό Χάντμπολ", "Τέννις", "Επαγγελματικό Πόλο", "Στίβος"
        });

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

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton createSubscriptionButton = new JButton("Δημιουργία Συνδρομής");
        JButton cancelSubscriptionButton = new JButton("Ακύρωση Συνδρομής");
        JButton paymentSubscriptionButton = new JButton("Πληρωμή Συνδρομής");
        JButton backButton = new JButton("Επιστροφή");

        buttonPanel.add(createSubscriptionButton);
        buttonPanel.add(cancelSubscriptionButton);
        buttonPanel.add(paymentSubscriptionButton);
        buttonPanel.add(backButton);

        createSubscriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createSubscription(); // Δημιουργία νέας συνδρομής
            }
        });

        cancelSubscriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelSubscription(); // Ακύρωση συνδρομής
            }
        });

        paymentSubscriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processSubscriptionPayment(); // Πληρωμή συνδρομής
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                SystemMain.getMainFrame().setVisible(true); // Επιστροφή στο κύριο παράθυρο
            }
        });

        String[] columnNames = {"Κωδικός", "Αθλητής", "Άθλημα", "Ημερομηνία Έναρξης", "Ημερομηνία Λήξης", "Πληρωμή", "Έκπτωση", "Εξοφλημένο"};
        subscriptionTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Απενεργοποίηση επεξεργασίας κελιών
            }
        };
        subscriptionTable = new JTable(subscriptionTableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < subscriptionTable.getColumnCount(); i++) {
            subscriptionTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) subscriptionTable.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        subscriptionTable.getTableHeader().setDefaultRenderer(headerRenderer);

        subscriptionTable.getTableHeader().setReorderingAllowed(false); // Απενεργοποίηση μετακίνησης στηλών

        JScrollPane scrollPane = new JScrollPane(subscriptionTable);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(mainPanel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        updateSubscriptionTableModel(); // Φόρτωση δεδομένων συνδρομών
    }

    private void createSubscription() {
        Athlete selectedAthlete = (Athlete) athleteComboBox.getSelectedItem();
        String selectedSport = (String) sportComboBox.getSelectedItem();

        if (selectedAthlete == null || selectedSport == null) {
            JOptionPane.showMessageDialog(frame, "Παρακαλώ επιλέξτε έναν αθλητή και ένα άθλημα.");
            return;
        }

        if (!selectedAthlete.isPaid()) {
            JOptionPane.showMessageDialog(frame, "Η εγγραφή του αθλητή πρέπει να έχει εξοφληθεί πριν εγγραφεί σε ένα πρόγραμμα εκπαίδευσης.");
            return;
        }

        if (SubscriptionManager.getInstance().hasSubscription(selectedAthlete.getCode(), selectedSport)) {
            JOptionPane.showMessageDialog(frame, "Ο αθλητής έχει ήδη συνδρομή στο επιλεγμένο άθλημα.");
            return;
        }

        if (selectedSport.startsWith("Επαγγελματικό") && !selectedAthlete.isProfessional()) {
            JOptionPane.showMessageDialog(frame, "Μόνο επαγγελματίες αθλητές μπορούν να εγγραφούν στο " + selectedSport + ".");
            return;
        }

        double discount = AthleteManager.getInstance().getEnrollment(selectedAthlete.getCode()).getDiscount();
        double cost = SportManager.getInstance().getSportCost(selectedSport) * (1 - discount / 100);

        String subscriptionCode = SubscriptionManager.getInstance().generateSubscriptionCode(selectedAthlete.getCode(), selectedSport);

        Subscription subscription = new Subscription(
                subscriptionCode,
                selectedAthlete,
                selectedSport,
                LocalDate.now(),
                LocalDate.now().plusMonths(1),
                new Payment(LocalDate.now(), cost, "Cash", null)
        );

        SubscriptionManager.getInstance().addSubscription(subscription);
        updateSubscriptionTableModel(); // Ενημέρωση μοντέλου πίνακα συνδρομών
    }

    private void cancelSubscription() {
        int selectedRow = subscriptionTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Παρακαλώ επιλέξτε μια συνδρομή για ακύρωση.");
            return;
        }

        String subscriptionCode = (String) subscriptionTableModel.getValueAt(selectedRow, 0);
        SubscriptionManager.getInstance().removeSubscription(subscriptionCode); // Ακύρωση συνδρομής
        updateSubscriptionTableModel();
    }

    private void processSubscriptionPayment() {
        int selectedRow = subscriptionTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Παρακαλώ επιλέξτε μια συνδρομή για πληρωμή.");
            return;
        }

        Subscription selectedSubscription = subscriptions.get(selectedRow);
        double cost = selectedSubscription.getPayment().getAmount();
        Athlete athlete = selectedSubscription.getAthlete();
        String sport = selectedSubscription.getSport();

        Object[] options = {"Επιβεβαίωση", "Άκυρο"};
        int confirm = JOptionPane.showOptionDialog(frame,
                "Θέλετε να πραγματοποιηθεί η πληρωμή της εγγραφής για τον " + athlete.getFullName() + " στο άθλημα " + sport + " που έχει κόστος " + cost + " €;",
                "Επιβεβαίωση Πληρωμής",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (confirm == JOptionPane.YES_OPTION) {
            selectedSubscription.getPayment().setPaid(true); // Καταχώρηση πληρωμής
            updateSubscriptionTableModel();
        }
    }

    private void updateSubscriptionTableModel() {
        subscriptionTableModel.setRowCount(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (Subscription subscription : SubscriptionManager.getInstance().getSubscriptions()) {
            double discount = AthleteManager.getInstance().getEnrollment(subscription.getAthlete().getCode()).getDiscount();
            Object[] rowData = {
                    subscription.getCode(),
                    subscription.getAthlete().getFullName(),
                    subscription.getSport(),
                    subscription.getStartDate().format(formatter),
                    subscription.getEndDate().format(formatter),
                    String.format("%.2f €", subscription.getPayment().getAmount()), // Μορφοποίηση ποσού πληρωμής
                    String.format("%.2f %%", discount), // Μορφοποίηση έκπτωσης
                    subscription.getPayment().isPaid() ? "Ναι" : "Όχι"
            };
            subscriptionTableModel.addRow(rowData);
        }
    }
}
