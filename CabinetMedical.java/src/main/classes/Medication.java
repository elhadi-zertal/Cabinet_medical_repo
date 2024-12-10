package main.classes;
import java.util.Objects;

public class Medication {
    private final String name;
    private final String dosage;
    private final String frequency;
    private final String duration;
    private final String instructions;

    public Medication(String name, String dosage, String frequency, String duration, String instructions) {
        this.name = Objects.requireNonNull(name, "Medication name cannot be null");
        this.dosage = Objects.requireNonNull(dosage, "Dosage cannot be null");
        this.frequency = Objects.requireNonNull(frequency, "Frequency cannot be null");
        this.duration = Objects.requireNonNull(duration, "Duration cannot be null");
        this.instructions = instructions;
    }

    // Immutable getters
    public String getName() { return name; }
    public String getDosage() { return dosage; }
    public String getFrequency() { return frequency; }
    public String getDuration() { return duration; }
    public String getInstructions() { return instructions; }

    @Override
    public String toString() {
        return String.format("%s - %s, %s for %s%s", 
            name, dosage, frequency, duration,
            instructions != null ? " (" + instructions + ")" : "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medication)) return false;
        Medication that = (Medication) o;
        return Objects.equals(name, that.name) &&
               Objects.equals(dosage, that.dosage) &&
               Objects.equals(frequency, that.frequency) &&
               Objects.equals(duration, that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dosage, frequency, duration);
    }
}
