package main.models;

import java.util.List;
import java.util.Objects;

public class MedicalRecord {
    private Patient patient;
    private List<String> diagnoses;
    private List<String> treatments;
    private List<String> prescriptions;
    private List<String> labResults;
    private String summary;
    private String lastUpdated;

    

    public MedicalRecord(Patient patient) {
        this.patient = patient;
    }

    public void addDiagnosis(String diagnosis) {
        this.diagnoses.add(diagnosis);
    }

    public void addTreatment(String treatment) {
        this.treatments.add(treatment);
    }

    public void addPrescription(String prescription) {
        this.prescriptions.add(prescription);
    }

    public void addLabResult(String result) {
        this.labResults.add(result);
    }

    public void updateSummary(String summary) {
        this.summary = summary;
    }

    public String getSummary() {
        return summary;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public List<String> getDiagnoses() {
        return diagnoses;
    }

    public List<String> getTreatments() {
        return treatments;
    }

    public List<String> getPrescriptions() {
        return prescriptions;
    }

    public List<String> getLabResults() {
        return labResults;
    }

    public Patient getPatient() {
        return patient;
    }

}
