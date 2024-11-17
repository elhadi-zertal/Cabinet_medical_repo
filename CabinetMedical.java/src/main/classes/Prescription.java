package main.classes;


    import java.time.LocalDate;
    import java.util.List;
    
    public class Prescription {
        private String prescriptionId;
        private String patientId;
        private List<String> medicationNames;  
        private String dosage;
        private int durationInDays;  
        private LocalDate issueDate;
        private LocalDate expirationDate;
        private String prescribingDoctor;
    
        // Constructor
        public Prescription(String prescriptionId, String patientId, List<String> medicationNames, 
                            String dosage, int durationInDays, LocalDate issueDate, 
                            LocalDate expirationDate, String prescribingDoctor) {
            this.prescriptionId = prescriptionId;
            this.patientId = patientId;
            this.medicationNames = medicationNames;
            this.dosage = dosage;
            this.durationInDays = durationInDays;
            this.issueDate = issueDate;
            this.expirationDate = expirationDate;
            this.prescribingDoctor = prescribingDoctor;
        }
    
        // Getters and Setters omitted for brevity
    
        // Method to display medication details
        public String getMedicationDetails() {
            return "Medications: " + String.join(", ", medicationNames) + 
                   " | Dosage: " + dosage + 
                   " | Duration: " + durationInDays + " days";
        }
    
        // toString Method
        @Override
        public String toString() {
            return "Prescription issued by " + prescribingDoctor + "\n" +
                   "For patient: " + patientId + "\n" +
                   getMedicationDetails() + "\n" +
                   "Issued on: " + issueDate + ", Expiration: " + expirationDate;
        }
    }

