
package main.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class DoctorManager {
    private static final Map<String, Doctor> doctors = new ConcurrentHashMap<>();
    
    public static Doctor getDoctorById(String doctorId) {
        return doctors.get(doctorId);
    }
    
    public static Doctor getDoctorByName(String doctorName) {
        Objects.requireNonNull(doctorName, "Doctor name cannot be null");
        return doctors.values().stream()
                .filter(doctor -> doctor.getDoctorName().equalsIgnoreCase(doctorName.trim()))
                .findFirst()
                .orElse(null);
    }

    
     public static List<Doctor> getAllDoctors() {
    return new ArrayList<>(doctors.values());
}

public static Doctor findDoctorByName(String doctorName) {
    if (doctorName == null || doctorName.trim().isEmpty()) {
        return null;
    }
    return getDoctorByName(doctorName); // Call the existing method to find by name
}

public static Doctor findDoctorById(String doctorId) {
    if (doctorId == null || doctorId.trim().isEmpty()) {
        return null;
    }
    return getDoctorById(doctorId); // Call the existing method to find by ID
}
   
    public static boolean doctorExists(String doctorId, String doctorName) {
        if (doctorId != null && doctors.containsKey(doctorId)) {
            return true;
        }
        if (doctorName != null) {
            return doctors.values().stream()
                    .anyMatch(doctor -> doctor.getDoctorName().equalsIgnoreCase(doctorName.trim()));
        }
        return false;
    }

    public static void addDoctor(Doctor doctor) {
        if (doctor == null || doctor.getDoctorId() == null) {
            throw new IllegalArgumentException("Invalid doctor data");
        }
        if (doctorExists(doctor.getDoctorId(), doctor.getDoctorName())) {
            throw new IllegalArgumentException("Doctor with this ID or name already exists");
        }
        doctors.put(doctor.getDoctorId(), doctor);
    }

    public static boolean updateDoctor(Doctor doctor) {
        if (doctor != null && doctor.getDoctorId() != null && doctors.containsKey(doctor.getDoctorId())) {
            doctors.put(doctor.getDoctorId(), doctor);
            return true;
        }
        return false;
    }

    public static boolean deleteDoctor(String doctorId) {
        Objects.requireNonNull(doctorId, "Doctor ID cannot be null");
        if (!doctors.containsKey(doctorId)) {
            return false;
        }
        doctors.remove(doctorId);
        return true;
    }

    public static int getDoctorCount() {
        return doctors.size();
    }

    public static void clearAll() {
        doctors.clear();
    }

    public static void listAllDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("No doctors available.");
            return;
        }
        System.out.println("List of doctors:");
        doctors.values().forEach(System.out::println);
    }
}

