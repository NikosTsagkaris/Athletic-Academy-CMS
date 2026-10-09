package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Η κλάση TrainingProgramManager διαχειρίζεται τη λίστα των προγραμμάτων εκπαίδευσης.
 * Παρέχει μεθόδους για προσθήκη, ενημέρωση και διαγραφή προγραμμάτων εκπαίδευσης.
 */
public class TrainingProgramManager {
    private List<TrainingProgram> trainingPrograms; // Λίστα με όλα τα προγράμματα εκπαίδευσης

    private static TrainingProgramManager instance;

    private TrainingProgramManager() {
        trainingPrograms = new ArrayList<>();
    }

    public static synchronized TrainingProgramManager getInstance() {
        if (instance == null) {
            instance = new TrainingProgramManager();
        }
        return instance;
    }

    public List<TrainingProgram> getTrainingPrograms() {
        return trainingPrograms;
    }

    public void addTrainingProgram(TrainingProgram trainingProgram) {
        trainingPrograms.add(trainingProgram); // Προσθήκη νέου προγράμματος εκπαίδευσης
    }

    public void removeTrainingProgram(int index) {
        if (index >= 0 && index < trainingPrograms.size()) {
            trainingPrograms.remove(index); // Αφαίρεση προγράμματος εκπαίδευσης από τη λίστα
        }
    }

    public void updateTrainingProgram(int index, TrainingProgram trainingProgram) {
        if (index >= 0 && index < trainingPrograms.size()) {
            trainingPrograms.set(index, trainingProgram); // Ενημέρωση του προγράμματος εκπαίδευσης στη συγκεκριμένη θέση της λίστας
        }
    }
}
