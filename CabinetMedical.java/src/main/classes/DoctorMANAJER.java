package main.classes;


import java.util.ArrayList;
import java.util.List;



public class DoctorMANAJER {
    private List<Doctor> doctors = new ArrayList<>();

    // Add a doctor to the system
    public void addDoctor(Doctor doctor) {
        if (doctorExists(doctor.getDoctorId(), doctor.getDoctorName())) {
            System.out.println("Doctor with ID " + doctor.getDoctorId() + " (" + doctor.getDoctorName() + ") already exists.");
        } else {
            doctors.add(doctor);
            System.out.println("Doctor " + doctor.getDoctorName() + " has been successfully added.");
        }
    }

    // Retrieve a doctor by ID
    public Doctor getDoctorById(String doctorId) {
        for (Doctor doctor : doctors) {
            if (doctor.getDoctorId().equals(doctorId)) {
                return doctor;
            }
        }
        return null;
    }

    // Retrieve a doctor by name
    public Doctor getDoctorByName(String doctorName) {
        for (Doctor doctor : doctors) {
            if (doctor.getDoctorName().equalsIgnoreCase(doctorName)) {
                return doctor;
            }
        }
        return null;
    }

    // Check if a doctor exists by either ID or name
    public boolean doctorExists(String doctorId, String doctorName) {
        return doctors.stream()
                .anyMatch(doctor -> doctor.getDoctorId().equals(doctorId) || doctor.getDoctorName().equalsIgnoreCase(doctorName));
    }

    // Update doctor details
    public void updateDoctor(String doctorId, String newDoctorName, String newSpecialization) {
        Doctor doctor = getDoctorById(doctorId);
        if (doctor != null) {
            doctor.setDoctorName(newDoctorName);
            doctor.setSpecialization(newSpecialization);
            System.out.println("Doctor details updated successfully.");
        } else {
            System.out.println("Doctor with ID " + doctorId + " not found.");
        }
    }

    // Remove a doctor from the system by ID
    public void deleteDoctor(String doctorId) {
        Doctor doctorToDelete = getDoctorById(doctorId);
        if (doctorToDelete != null) {
            doctors.remove(doctorToDelete);
            System.out.println("Doctor with ID " + doctorId + " has been removed.");
        } else {
            System.out.println("Doctor with ID " + doctorId + " not found.");
        }
    }

    // List all doctors
    public void listAllDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("No doctors available.");
        } else {
            System.out.println("List of doctors:");
            for (Doctor doctor : doctors) {
                System.out.println(doctor);
            }
        }
    }
}
  
