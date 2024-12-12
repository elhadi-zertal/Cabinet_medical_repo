package main.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class MedicalRecordManager {
    private final Map<String, MedicalRecord> medicalRecords;
    private final Map<String, List<MedicalRecord>> patientRecords;

    public MedicalRecordManager() {
        this.medicalRecords = new ConcurrentHashMap<>();
        this.patientRecords = new ConcurrentHashMap<>();
    }

    public MedicalRecord getMedicalRecordById(String id) {
        return medicalRecords.get(id);
    }

    public List<MedicalRecord> getAllMedicalRecords() {
        return new ArrayList<>(medicalRecords.values());
    }

    public List<MedicalRecord> getPatientRecords(String patientId) {
        if (patientId == null || patientId.trim().isEmpty()) {
            throw new IllegalArgumentException("Patient ID cannot be null or empty");
        }
        return new ArrayList<>(patientRecords.getOrDefault(patientId, new ArrayList<>()));
    }

    public boolean medicalRecordExists(String recordId) {
        return recordId != null && medicalRecords.containsKey(recordId);
    }

    public void addMedicalRecord(MedicalRecord record) {
        if (record == null || record.getRecordId() == null) {
            throw new IllegalArgumentException("Invalid medical record data");
        }
        if (medicalRecordExists(record.getRecordId())) {
            throw new IllegalArgumentException("Medical record with this ID already exists");
        }

        medicalRecords.put(record.getRecordId(), record);
        
        // Update patient records
        String patientId = record.getPatient().getId();
        patientRecords.computeIfAbsent(patientId, k -> new ArrayList<>()).add(record);
    }

    public boolean updateMedicalRecord(MedicalRecord record) {
        if (record == null || record.getRecordId() == null) {
            return false;
        }

        String recordId = record.getRecordId();
        MedicalRecord existingRecord = medicalRecords.get(recordId);
        
        if (existingRecord == null) {
            return false;
        }

        // Update in main records
        medicalRecords.put(recordId, record);

        // Update in patient records
        String patientId = record.getPatient().getId();
        List<MedicalRecord> records = patientRecords.get(patientId);
        if (records != null) {
            for (int i = 0; i < records.size(); i++) {
                if (records.get(i).getRecordId().equals(recordId)) {
                    records.set(i, record);
                    break;
                }
            }
        }

        return true;
    }

    public boolean deleteMedicalRecord(String recordId) {
        Objects.requireNonNull(recordId, "Record ID cannot be null");
        
        MedicalRecord record = medicalRecords.remove(recordId);
        if (record != null) {
            // Remove from patient records
            String patientId = record.getPatient().getId();
            List<MedicalRecord> records = patientRecords.get(patientId);
            if (records != null) {
                records.removeIf(r -> r.getRecordId().equals(recordId));
                if (records.isEmpty()) {
                    patientRecords.remove(patientId);
                }
            }
            return true;
        }
        return false;
    }

    public void listAllMedicalRecords() {
        if (medicalRecords.isEmpty()) {
            System.out.println("No medical records available.");
            return;
        }
        System.out.println("List of medical records:");
        medicalRecords.values().forEach(System.out::println);
    }

    public int getMedicalRecordCount() {
        return medicalRecords.size();
    }

    public void clearAll() {
        medicalRecords.clear();
        patientRecords.clear();
    }

    // Optional: Method to get records count for a specific patient
    public int getPatientRecordCount(String patientId) {
        if (patientId == null || patientId.trim().isEmpty()) {
            return 0;
        }
        List<MedicalRecord> records = patientRecords.get(patientId);
        return records != null ? records.size() : 0;
    }
}


