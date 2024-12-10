package main.classes;

import java.util.Date;
import java.util.List;

public class Prescription {
    private String prescriptionId;
    private Doctor doctor;
    private Patient patient;
    private List<String> medications;
    private String dosage;
    private String duration;
    private Date prescriptionDate;
    private Date validityDate;
    private List<String> medicalExams;
    private String additionalNotes;

    // Constructor
    public Prescription(String prescriptionId, Doctor doctor, Patient patient, List<String> medications, 
                        String dosage, String duration, Date prescriptionDate, Date validityDate, 
                        List<String> medicalExams, String additionalNotes) {
        this.prescriptionId = prescriptionId;
        this.doctor = doctor;
        this.patient = patient;
        this.medications = medications;
        this.dosage = dosage;
        this.duration = duration;
        this.prescriptionDate = prescriptionDate;
        this.validityDate = validityDate;
        this.medicalExams = medicalExams;
        this.additionalNotes = additionalNotes;
    }

    // Getters and setters
    public String getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Date getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(Date prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public Date getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(Date validityDate) {
        this.validityDate = validityDate;
    }

    public List<String> getMedicalExams() {
        return medicalExams;
    }

    public void setMedicalExams(List<String> medicalExams) {
        this.medicalExams = medicalExams;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    // Validate if doctor and patient exist
    public boolean validateDoctorAndPatient(DoctorManager doctorManager, PatientManager patientManager) {
        Doctor existingDoctor = doctorManager.getDoctorById(doctor.getDoctorId());
        if (existingDoctor == null) {
            System.out.println("Error: Doctor does not exist.");
            return false;
        }

        Patient existingPatient = patientManager.getPatientById(patient.getId());
        if (existingPatient == null) {
            System.out.println("Error: Patient does not exist.");
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "prescriptionId='" + prescriptionId + '\'' +
                ", doctor=" + doctor.getDoctorName() + " (" + doctor.getSpecialization() + ")" +
                ", patient=" + patient.getName() + " (" + patient.getId() + ")" +
                ", medications=" + medications +
                ", dosage='" + dosage + '\'' +
                ", duration='" + duration + '\'' +
                ", prescriptionDate=" + prescriptionDate +
                ", validityDate=" + validityDate +
                ", medicalExams=" + medicalExams +
                ", additionalNotes='" + additionalNotes + '\'' +
                '}';
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




 
