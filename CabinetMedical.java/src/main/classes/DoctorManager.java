/*package main.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class DoctorManager {
    private final Map<String, Doctor> doctors;

    public DoctorManager() {
        this.doctors = new ConcurrentHashMap<>();
    }

    public Doctor getDoctorById(String id) {
        return doctors.get(id);
    }
    public Doctor getDoctorByName(String doctorName) {
        Objects.requireNonNull(doctorName, "Doctor name cannot be null");
        return doctors.values().stream()
                .filter(doctor -> doctor.getDoctorName().equalsIgnoreCase(doctorName.trim()))
                .findFirst()
                .orElse(null);}

    public List<Doctor> getAllDoctors() {
        return new ArrayList<>(doctors.values());
    }

    public List<Doctor> getDoctorsBySpecialty(String specialty) {
        return doctors.values().stream()
                .filter(doctor -> specialty.equals(doctor.getSpecialization()))
                .collect(Collectors.toList());
    }

    public void addDoctor(Doctor doctor) {
        if (doctor == null || doctor.getDoctorId() == null) {
            throw new IllegalArgumentException("Invalid doctor data");
        }
        
        doctors.put(doctor.getDoctorId(), doctor);
    }
     // Check if doctor exists by ID or name
    public boolean doctorExists(String doctorId, String doctorName) {
        if (doctorId != null && doctors.containsKey(doctorId)) {
            return true;
        }
        if (doctorName != null) {
            return doctors.values().stream()
                    .anyMatch(doctor -> doctor.getDoctorName().equalsIgnoreCase(doctorName.trim()));
        }
                return false;
    }
    public boolean updateDoctor(String id, Doctor doctor) {
        if (doctors.containsKey(id)) {
            doctors.put(id, doctor);
            return true;
        }
        return false;
    }
     
    // Delete doctor
    public boolean deleteDoctor(String doctorId) {
        Objects.requireNonNull(doctorId, "Doctor ID cannot be null");
        return doctors.remove(doctorId) != null;
    }

    // Optional: Method to get the total number of doctors
    public int getDoctorCount() {
        return doctors.size();
    }
// List all doctors 
public void listAllDoctors() {
    if (doctors.isEmpty()) {
        System.out.println("No doctors available.");
        return;
    }
     System.out.println("List of doctors:");
    doctors.values().forEach(System.out::println);}
    // Optional: Method to clear all doctors (useful for testing)
    public void clearAll() {
        doctors.clear();
    }
}*/
package main.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class DoctorManager {
    private final Map<String, Doctor> doctors;

    public DoctorManager() {
        this.doctors = new ConcurrentHashMap<>();
    }

    public Doctor getDoctorById(String id) {
        return doctors.get(id);
    }

    public Doctor getDoctorByName(String doctorName) {
        Objects.requireNonNull(doctorName, "Doctor name cannot be null");
        return doctors.values().stream()
                .filter(doctor -> doctor.getDoctorName().equalsIgnoreCase(doctorName.trim()))
                .findFirst()
                .orElse(null);
    }

    public List<Doctor> getAllDoctors() {
        return new ArrayList<>(doctors.values());
    }

    public boolean doctorExists(String doctorId, String doctorName) {
        if (doctorId != null && doctors.containsKey(doctorId)) {
            return true;
        }
        if (doctorName != null) {
            return doctors.values().stream()
                    .anyMatch(doctor -> doctor.getDoctorName().equalsIgnoreCase(doctorName.trim()));
        }
        return false;
    }

    public void addDoctor(Doctor doctor) {
        if (doctor == null || doctor.getDoctorId() == null) {
            throw new IllegalArgumentException("Invalid doctor data");
        }
        if (doctorExists(doctor.getDoctorId(), doctor.getDoctorName())) {
            throw new IllegalArgumentException("Doctor with this ID or name already exists");
        }
        doctors.put(doctor.getDoctorId(), doctor);
    }

    public boolean updateDoctor(Doctor doctor) {
        if (doctor != null && doctor.getDoctorId() != null && doctors.containsKey(doctor.getDoctorId())) {
            doctors.put(doctor.getDoctorId(), doctor);
            return true;
        }
        return false;
    }

    public boolean deleteDoctor(String doctorId) {
        Objects.requireNonNull(doctorId, "Doctor ID cannot be null");
        if (!doctors.containsKey(doctorId)) {
            return false;
        }
        doctors.remove(doctorId);
        return true;
    }

    public int getDoctorCount() {
        return doctors.size();
    }

    public void clearAll() {
        doctors.clear();
    }
    // List all doctors 
public void listAllDoctors() {
    if (doctors.isEmpty()) {
        System.out.println("No doctors available.");
        return;
    }
     System.out.println("List of doctors:");
    doctors.values().forEach(System.out::println);}
    // Optional: Method to clear all doctors (useful for testing)
    
}
