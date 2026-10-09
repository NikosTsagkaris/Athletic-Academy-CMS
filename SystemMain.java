package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * Κύρια κλάση του συστήματος διαχείρισης αθλητικών ακαδημιών.
 * Παρέχει το κύριο παράθυρο και την πλοήγηση σε διαχείριση αθλητών, συνδρομών και κρατήσεων.
 * Προσθέτει προκαθορισμένους προπονητές, εγκαταστάσεις και προγράμματα εκπαίδευσης.
 */
public class SystemMain {
    private static JFrame mainFrame;

    public static void main(String[] args) {
        
        addPresetCoaches(); // Προσθήκη προκαθορισμένων προπονητών
        addPresetFacilities(); // Προσθήκη προκαθορισμένων εγκαταστάσεων
        addPresetTrainingPrograms(); // Προσθήκη προκαθορισμένων προγραμμάτων εκπαίδευσης

        mainFrame = new JFrame("Διαχείριση Αθλητικών Ακαδημιών");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(40, 60, 40, 60); // Ρυθμίσεις περιθωρίων

        JButton manageAthletesButton = new JButton("Διαχείριση Αθλητών");
        JButton manageSubscriptionsButton = new JButton("Διαχείριση Συνδρομών");
        JButton manageReservationsButton = new JButton("Διαχείριση Κρατήσεων");
        JButton exitButton = new JButton("Έξοδος");

        manageAthletesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAthleteManagementWindow(); // Άνοιγμα παραθύρου διαχείρισης αθλητών
            }
        });

        manageSubscriptionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setVisible(false);
                new SubscriptionManagementWindow(); // Άνοιγμα παραθύρου διαχείρισης συνδρομών
            }
        });

        manageReservationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setVisible(false);
                new ReservationsManagementWindow(); // Άνοιγμα παραθύρου διαχείρισης κρατήσεων
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Κλείσιμο του προγράμματος
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(manageAthletesButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(manageSubscriptionsButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(manageReservationsButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(exitButton, gbc);

        mainFrame.getContentPane().add(panel);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Ορίζει το παράθυρο σε πλήρη οθόνη
        mainFrame.setVisible(true);
    }

    private static void openAthleteManagementWindow() {
        mainFrame.setVisible(false);
        new AthleteManagementWindow(); // Άνοιγμα παραθύρου διαχείρισης αθλητών
    }

    public static JFrame getMainFrame() {
        return mainFrame; // Επιστρέφει το κύριο παράθυρο
    }

    private static void addPresetCoaches() {
        CoachManager coachManager = CoachManager.getInstance();

        coachManager.addCoach(new Coach(1, "Ioannis", "Sotiriou", "Άνδρας",
                LocalDate.of(1980, 1, 15), "IoannisSotiriou@gmail.com", new Sport("Ποδόσφαιρο", true),
                Arrays.asList("Physical Education", "Sports Medicine"))); // Προσθήκη προπονητή ποδοσφαίρου

        coachManager.addCoach(new Coach(2, "Ilianna", "Papadopoulou", "Γυναίκα",
                LocalDate.of(1985, 6, 25), "IliannaPap@gmail.com", new Sport("Μπάσκετ", false),
                Arrays.asList("Exercise Science", "Nutrition and Fitness"))); // Προσθήκη προπονήτριας μπάσκετ

        coachManager.addCoach(new Coach(3, "Mixail", "Giannakis", "Άνδρας",
                LocalDate.of(1978, 3, 10), "mikeGiann@gmail.com", new Sport("Χάντμπολ", true),
                Arrays.asList("Physical Education", "Kinesiology"))); // Προσθήκη προπονητή χάντμπολ

        coachManager.addCoach(new Coach(4, "Emmanouela", "Koumpiou", "Γυναίκα",
                LocalDate.of(1990, 12, 5), "emakoump@gmail.com", new Sport("Τέννις", false),
                Arrays.asList("Exercise Science", "Nutrition and Fitness"))); // Προσθήκη προπονήτριας τέννις

        coachManager.addCoach(new Coach(5, "Petros", "Eustathiou", "Άνδρας",
                LocalDate.of(1982, 9, 20), "petrosEuth@gmail.com", new Sport("Πόλο", true),
                Arrays.asList("Kinesiology", "Sports Medicine"))); // Προσθήκη προπονητή πόλο

        coachManager.addCoach(new Coach(6, "Eleni", "Tourmpogiannaki", "Γυναίκα",
                LocalDate.of(1987, 8, 15), "EleniTourmpo@gmail.com", new Sport("Στίβος", false),
                Arrays.asList("Physical Education", "Exercise Science"))); // Προσθήκη προπονήτριας στίβου
    }

    private static void addPresetFacilities() {
        FacilityManager facilityManager = FacilityManager.getInstance();

        facilityManager.addFacility(new Facility("Γήπεδο Τέννις 1", 3)); // Προσθήκη γηπέδου τέννις 1
        facilityManager.addFacility(new Facility("Γήπεδο Τέννις 2", 3)); // Προσθήκη γηπέδου τέννις 2
        facilityManager.addFacility(new Facility("Γήπεδο Ποδοσφαίρου", 14)); // Προσθήκη γηπέδου ποδοσφαίρου
        facilityManager.addFacility(new Facility("Γήπεδο Μπάσκετ", 12)); // Προσθήκη γηπέδου μπάσκετ
        facilityManager.addFacility(new Facility("Γήπεδο Βόλει 1", 10)); // Προσθήκη γηπέδου βόλεϊ 1
        facilityManager.addFacility(new Facility("Γήπεδο Βόλει 2", 10)); // Προσθήκη γηπέδου βόλεϊ 2
        facilityManager.addFacility(new Facility("Γήπεδο Χάντμπολ", 10)); // Προσθήκη γηπέδου χάντμπολ
        facilityManager.addFacility(new Facility("Κολυμβητήριο", 10)); // Προσθήκη κολυμβητηρίου
        facilityManager.addFacility(new Facility("Στίβος", 8)); // Προσθήκη στίβου
    }

    private static void addPresetTrainingPrograms() {
        TrainingProgramManager trainingProgramManager = TrainingProgramManager.getInstance();
    
        trainingProgramManager.addTrainingProgram(new TrainingProgram(1, new Sport("Επαγγελματικό Ποδόσφαιρο", true), 
            FacilityManager.getInstance().getFacilities().get(2), 100, "Πρωινό", "Μεικτό", "1-2")); // Προσθήκη προγράμματος ποδοσφαίρου πρωινό
    
        trainingProgramManager.addTrainingProgram(new TrainingProgram(2, new Sport("Επαγγελματικό Ποδόσφαιρο", true), 
            FacilityManager.getInstance().getFacilities().get(2), 100, "Μεσημεριανό", "Μεικτό", "3-4")); // Προσθήκη προγράμματος ποδοσφαίρου μεσημεριανό
    
        trainingProgramManager.addTrainingProgram(new TrainingProgram(3, new Sport("Επαγγελματικό Ποδόσφαιρο", true), 
            FacilityManager.getInstance().getFacilities().get(2), 100, "Απογευματινό", "Άνδρας", "5")); // Προσθήκη προγράμματος ποδοσφαίρου απογευματινό
    
        trainingProgramManager.addTrainingProgram(new TrainingProgram(4, new Sport("Μπάσκετ", false), 
            FacilityManager.getInstance().getFacilities().get(3), 80, "Πρωινό", "Μεικτό", "1-2")); // Προσθήκη προγράμματος μπάσκετ πρωινό
    
        trainingProgramManager.addTrainingProgram(new TrainingProgram(5, new Sport("Μπάσκετ", false), 
            FacilityManager.getInstance().getFacilities().get(3), 80, "Μεσημεριανό", "Μεικτό", "3-4")); // Προσθήκη προγράμματος μπάσκετ μεσημεριανό
    
        trainingProgramManager.addTrainingProgram(new TrainingProgram(6, new Sport("Μπάσκετ", false), 
            FacilityManager.getInstance().getFacilities().get(3), 80, "Απογευματινό", "Άνδρας", "5")); // Προσθήκη προγράμματος μπάσκετ απογευματινό
    
        trainingProgramManager.addTrainingProgram(new TrainingProgram(7, new Sport("Βόλει", false), 
            FacilityManager.getInstance().getFacilities().get(4), 80, "Πρωινό", "Μεικτό", "1-3")); // Προσθήκη προγράμματος βόλεϊ πρωινό
    
        trainingProgramManager.addTrainingProgram(new TrainingProgram(8, new Sport("Βόλει", false), 
            FacilityManager.getInstance().getFacilities().get(4), 80, "Μεσημεριανό", "Άνδρας", "4-5")); // Προσθήκη προγράμματος βόλεϊ μεσημεριανό
    
        trainingProgramManager.addTrainingProgram(new TrainingProgram(9, new Sport("Βόλει", false), 
            FacilityManager.getInstance().getFacilities().get(4), 80, "Απογευματινό", "Γυναίκα", "4-5")); // Προσθήκη προγράμματος βόλεϊ απογευματινό
    
        trainingProgramManager.addTrainingProgram(new TrainingProgram(10, new Sport("Επαγγελματικό Χάντμπολ", true), 
            FacilityManager.getInstance().getFacilities().get(6), 65, "Πρωινό", "Μεικτό", "1-3")); // Προσθήκη προγράμματος χάντμπολ πρωινό
    
        trainingProgramManager.addTrainingProgram(new TrainingProgram(11, new Sport("Επαγγελματικό Χάντμπολ", true), 
            FacilityManager.getInstance().getFacilities().get(6), 65, "Μεσημεριανό", "Άνδρας", "4-5")); // Προσθήκη προγράμματος χάντμπολ μεσημεριανό
    
        trainingProgramManager.addTrainingProgram(new TrainingProgram(12, new Sport("Επαγγελματικό Χάντμπολ", true), 
            FacilityManager.getInstance().getFacilities().get(6), 65, "Απογευματινό", "Γυναίκα", "4-5")); // Προσθήκη προγράμματος χάντμπολ απογευματινό
    
        trainingProgramManager.addTrainingProgram(new TrainingProgram(13, new Sport("Τέννις", false), 
            FacilityManager.getInstance().getFacilities().get(0), 90, "Πρωινό", "Μεικτό", "1-2")); // Προσθήκη προγράμματος τέννις πρωινό
    
        trainingProgramManager.addTrainingProgram(new TrainingProgram(14, new Sport("Τέννις", false), 
            FacilityManager.getInstance().getFacilities().get(0), 90, "Μεσημεριανό", "Άνδρας", "3-5")); // Προσθήκη προγράμματος τέννις μεσημεριανό
    
        trainingProgramManager.addTrainingProgram(new TrainingProgram(15, new Sport("Τέννις", false), 
            FacilityManager.getInstance().getFacilities().get(0), 90, "Απογευματινό", "Γυναίκα", "3-5")); // Προσθήκη προγράμματος τένις απογευματινό
    
        trainingProgramManager.addTrainingProgram(new TrainingProgram(16, new Sport("Επαγγελματικό Πόλο", true), 
            FacilityManager.getInstance().getFacilities().get(7), 75, "Πρωινό", "Άνδρας", "4-5")); // Προσθήκη προγράμματος πόλο πρωινό
    
        trainingProgramManager.addTrainingProgram(new TrainingProgram(17, new Sport("Επαγγελματικό Πόλο", true), 
            FacilityManager.getInstance().getFacilities().get(7), 75, "Μεσημεριανό", "Μεικτό", "1-3")); // Προσθήκη προγράμματος πόλο μεσημεριανό
    
        trainingProgramManager.addTrainingProgram(new TrainingProgram(18, new Sport("Επαγγελματικό Πόλο", true), 
            FacilityManager.getInstance().getFacilities().get(7), 75, "Απογευματινό", "Γυναίκα", "4-5")); // Προσθήκη προγράμματος πόλο απογευματινό
    
        trainingProgramManager.addTrainingProgram(new TrainingProgram(19, new Sport("Στίβος", false), 
            FacilityManager.getInstance().getFacilities().get(8), 70, "Πρωινό", "Μεικτό", "1-2")); // Προσθήκη προγράμματος στίβου πρωινό
    
        trainingProgramManager.addTrainingProgram(new TrainingProgram(20, new Sport("Στίβος", false), 
            FacilityManager.getInstance().getFacilities().get(8), 70, "Μεσημεριανό", "Μεικτό", "3-4")); // Προσθήκη προγράμματος στίβου μεσημεριανό
    
        trainingProgramManager.addTrainingProgram(new TrainingProgram(21, new Sport("Στίβος", false), 
            FacilityManager.getInstance().getFacilities().get(8), 70, "Απογευματινό", "Μεικτό", "5")); // Προσθήκη προγράμματος στίβου απογευματινό
    }
}
