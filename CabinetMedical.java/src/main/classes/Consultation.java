package main.classes;

import java.util.List;

public class Consultation {

    private String consultationId; // Unique ID for consultation
    private String diagnosis; // Diagnosis given during the consultation
    private String treatment; // Treatment prescribed during the consultation
    private String prescription; // Prescription given during the consultation
    private String medicalHistory; // Medical history of the patient during consultation
    private String allergies; // Allergies discovered during the consultation
    private String followUp; // Follow-up advice or recommendation from the doctor
    private String notes; // Additional notes from the doctor
    private Appointment appointment; // Appointment object associated with the consultation

    // Constructor for Consultation
    public Consultation(String consultationId, String diagnosis, String treatment, String prescription, 
                        String medicalHistory, String allergies, String followUp, String notes, 
                        Appointment appointment) {
        this.consultationId = consultationId;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.prescription = prescription;
        this.medicalHistory = medicalHistory;
        this.allergies = allergies;
        this.followUp = followUp;
        this.notes = notes;
        this.appointment = appointment;
    }

    // getter and setter methods 
    public String getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(String consultationId) {
        this.consultationId = consultationId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getFollowUp() {
        return followUp;
    }

    public void setFollowUp(String followUp) {
        this.followUp = followUp;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    // Method for the doctor to provide a diagnosis
    public void provideDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
        this.notes += "\nDiagnosis provided: " + diagnosis;
    }

    // Method for the doctor to prescribe treatment
    public void prescribeTreatment(String treatment) {
        this.treatment = treatment;
        this.notes += "\nTreatment prescribed: " + treatment;
    }

    // Method for the doctor to prescribe medication
    public void prescribeMedication(String medication) {
        this.prescription = medication;
        this.notes += "\nPrescription given: " + medication;
    }

    // Method for the doctor to update medical history based on the consultation
    public void updateMedicalHistory(String medicalHistoryUpdate) {
        this.medicalHistory += "\n" + medicalHistoryUpdate;
        this.notes += "\nMedical history updated: " + medicalHistoryUpdate;
    }

    // Method for the doctor to log allergies during the consultation
    public void logAllergy(String allergy) {
        this.allergies += "\n" + allergy;
        this.notes += "\nAllergy recorded: " + allergy;
    }

    // Method for the doctor to provide follow-up recommendations
    public void provideFollowUp(String followUpRecommendation) {
        this.followUp = followUpRecommendation;
        this.notes += "\nFollow-up recommendation: " + followUpRecommendation;
    }

    // --- Methods to extract information from Appointment ---

    public String getAppointmentDate() {
        return appointment.getDay() + "/" + appointment.getMonth() + "/" + appointment.getYear();
    }

    public String getAppointmentTime() {
        return appointment.getHour() + ":00";
    }

    public String getDoctorName() {
        return appointment.getDoctor().getDoctorName(); 
    }

    public String getDoctorSpecialization() {
        return appointment.getDoctor().getSpecialization(); 
    }

    public String getPatientName() {
        return appointment.getName(); 
    }

    public String getPatientContactInfo() {
        return appointment.getContactInfo(); 
    }

    public String getPatientId() {
        return appointment.getId(); 
    }

    // Display consultation details along with extracted patient and appointment information
    @Override
    public String toString() {
        return "Consultation{" +
                "consultationId='" + consultationId + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", treatment='" + treatment + '\'' +
                ", prescription='" + prescription + '\'' +
                ", medicalHistory='" + medicalHistory + '\'' +
                ", allergies='" + allergies + '\'' +
                ", followUp='" + followUp + '\'' +
                ", notes='" + notes + '\'' +
                ", appointmentDate='" + getAppointmentDate() + '\'' +
                ", appointmentTime='" + getAppointmentTime() + '\'' +
                ", doctorName='" + getDoctorName() + '\'' +
                ", doctorSpecialization='" + getDoctorSpecialization() + '\'' +
                ", patientName='" + getPatientName() + '\'' +
                ", patientId='" + getPatientId() + '\'' +
                ", patientContactInfo='" + getPatientContactInfo() + '\'' +
                '}';
    }
}
