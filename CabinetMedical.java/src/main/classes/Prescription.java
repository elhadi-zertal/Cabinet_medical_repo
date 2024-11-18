package main.classes;


import java.time.LocalDate;
import java.util.List;

public class Prescription {
    private String prescriptionId;
    private Patient patient;  // Reference to the Patient object
    private List<Medication> medications;   // List of medications with dosage and duration
    private LocalDate issueDate;
    private LocalDate expirationDate;
    private String doctorName;
    private List<MedicalExam> recommendedExams;  // Medical exams related to this prescription

    // Constructor
    public Prescription(String prescriptionId, Patient patient, List<Medication> medications,
                        LocalDate issueDate, LocalDate expirationDate, String doctorName,
                        List<MedicalExam> recommendedExams) {
        this.prescriptionId = prescriptionId;
        this.patient = patient;   // Reference the Patient object
        this.medications = medications;
        this.issueDate = issueDate;
        this.expirationDate = expirationDate;
        this.doctorName = doctorName;
        this.recommendedExams = recommendedExams;
    }

    // Getters and Setters
    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    @Override
    public String toString() {
        StringBuilder details = new StringBuilder();
        details.append("Prescription issued by Dr. ").append(doctorName).append("\n")
               .append("For patient: ").append(patient.getName()).append(" (ID: ").append(patient.getgitId()).append(")\n")
               .append("Issued on: ").append(issueDate).append(", Expiration: ").append(expirationDate).append("\n\n");

        details.append("Medications:\n");
        for (Medication med : medications) {
            details.append(med.toString()).append("\n");
        }

        details.append("\nMedical Exams:\n");
        for (MedicalExam exam : recommendedExams) {
            details.append(exam.getExamDetails()).append("\n");
        }

        return details.toString();
    }
}

/*OUTPUT EXAMPLE
Prescription issued by Dr. Smith
For patient: John Doe (ID: P12345)
Issued on: 2024-11-17, Expiration: 2024-12-17

Medications:
Medication: Aspirin, Dosage: 500mg, Duration: 5 days
Medication: Paracetamol, Dosage: 650mg, Duration: 3 days
Medication: Ibuprofen, Dosage: 200mg, Duration: 7 days

Medical Exams:
Exam Type: Blood Test
Reason: Check cholesterol
Recommended by: Dr. Smith
Date: 2024-11-17
Result: Normal

Exam Type: X-ray
Reason: Fracture in left arm
Recommended by: Dr. Jones
Date: 2024-11-17
Result: Fracture detected */ 
