package main.classes;


public class Medication {
    private String name;
    private String dosage;  // Dosage for this medication
    private int duration;   // Duration in days

    // Constructor
    public Medication(String name, String dosage, int duration) {
        this.name = name;
        this.dosage = dosage;
        this.duration = duration;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDosage() {
        return dosage;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Medication: " + name + ", Dosage: " + dosage + ", Duration: " + duration + " days";
    }
}
