package main.classes;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public final class Doctor {
    private static int doctorCounter = 0; // Static counter to ensure unique IDs
    private final String doctorId;
    private final String doctorName;
    private final String specialization;
    private final String contactInfo;
    private final WorkSchedule workSchedule;
    private final AtomicInteger currentPatientCount;
    private static final int MAX_PATIENTS = 100;
    private final List<Appointment> appointments;

    public static class WorkSchedule {
        private final Map<DayOfWeek, List<TimeSlot>> weeklySchedule;

        public WorkSchedule() {
            this.weeklySchedule = new EnumMap<>(DayOfWeek.class);
        }

        public void addWorkingHours(DayOfWeek day, LocalTime start, LocalTime end) {
            weeklySchedule.computeIfAbsent(day, k -> new ArrayList<>())
                         .add(new TimeSlot(start, end));
        }

        public boolean isWithinWorkingHours(LocalDateTime dateTime) {
            List<TimeSlot> daySchedule = weeklySchedule.get(dateTime.getDayOfWeek());
            if (daySchedule == null) return false;

            LocalTime time = dateTime.toLocalTime();
            return daySchedule.stream()
                            .anyMatch(slot -> slot.contains(time));
        }
    }

    public static class TimeSlot {
        private final LocalTime start;
        private final LocalTime end;

        public TimeSlot(LocalTime start, LocalTime end) {
            this.start = start;
            this.end = end;
        }

        public boolean contains(LocalTime time) {
            return !time.isBefore(start) && !time.isAfter(end);
        }
    }

    // Builder pattern
    public static class Builder {
        private String doctorId;
        private String doctorName;
        private String specialization;
        private String contactInfo;
        private WorkSchedule workSchedule = new WorkSchedule();

        public Builder doctorId(String doctorId) {
            this.doctorId = doctorId;
            return this;
        }

        public Builder doctorName(String doctorName) {
            this.doctorName = doctorName;
            return this;
        }

        public Builder specialization(String specialization) {
            this.specialization = specialization;
            return this;
        }

        public Builder contactInfo(String contactInfo) {
            this.contactInfo = contactInfo;
            return this;
        }

        public Builder workSchedule(WorkSchedule workSchedule) {
            this.workSchedule = workSchedule;
            return this;
        }

        public Doctor build() {
            return new Doctor(this);
        }
    }

    private Doctor(Builder builder) {
        this.doctorId = Objects.requireNonNull(builder.doctorId, "Doctor ID cannot be null");
        this.doctorName = Objects.requireNonNull(builder.doctorName, "Doctor name cannot be null");
        this.specialization = Objects.requireNonNull(builder.specialization, "Specialization cannot be null");
        this.contactInfo = Objects.requireNonNull(builder.contactInfo, "Contact info cannot be null");
        this.workSchedule = Objects.requireNonNull(builder.workSchedule, "Work schedule cannot be null");
        this.currentPatientCount = new AtomicInteger(0);
        this.appointments = new ArrayList<>();
    }
   // Method to generate a unique Doctor ID
   public static String generateDoctorId() {
    doctorCounter++; // Increment the counter
    return "DOC" + String.format("%04d", doctorCounter); // Format as DOC0001, DOC0002, etc.
}

    // Getters
    public String getDoctorId() {
        return doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public WorkSchedule getWorkSchedule() {
        return workSchedule;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public boolean isAvailableForAppointment(LocalDateTime dateTime, Duration duration) {
        if (!workSchedule.isWithinWorkingHours(dateTime)) {
            return false;
        }

        LocalDateTime endTime = dateTime.plus(duration);
        if (!workSchedule.isWithinWorkingHours(endTime)) {
            return false;
        }

        return currentPatientCount.get() < MAX_PATIENTS;
    }

    public boolean incrementPatientCount() {
        int current;
        do {
            current = currentPatientCount.get();
            if (current >= MAX_PATIENTS) {
                return false;
            }
        } while (!currentPatientCount.compareAndSet(current, current + 1));
        return true;
    }

    public boolean decrementPatientCount() {
        int current;
        do {
            current = currentPatientCount.get();
            if (current <= 0) {
                return false;
            }
        } while (!currentPatientCount.compareAndSet(current, current - 1));
        return true;
    }

    public int getCurrentPatientCount() {
        return currentPatientCount.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Doctor)) return false;
        Doctor doctor = (Doctor) o;
        return doctorId.equals(doctor.doctorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId);
    }

    @Override
    public String toString() {
        return "Doctor{" +
               "doctorId='" + doctorId + '\'' +
               ", doctorName='" + doctorName + '\'' +
               ", specialization='" + specialization + '\'' +
               ", contactInfo='" + contactInfo + '\'' +
               '}';
    }
}


/*usage example 
    Doctor doctor = new Doctor.Builder()
    .doctorId("D123")
    .doctorName("Dr. Smith")
    .specialization("Cardiology")
    .contactInfo("smith@hospital.com")
    .workSchedule(new WorkSchedule())
    .build();
*/

