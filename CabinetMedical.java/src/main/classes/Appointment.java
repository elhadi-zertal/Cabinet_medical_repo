package main.classes;
import java.util.ArrayList;
import java.util.List;

public class Appointment {
    public String family_name;    //family name of the patient
    public String first_name;     //first name of the patient
    public String address;        //address of the patient
    public String ContactInfo;    //ContactInfo number
    public int day;               //the day of the appointment
    public int month;             //the month of the appointment
    public int year;              //the year of the appointment
    public int hour;              //the hour of the appointment
    private String appointmentId; // Unique ID for the appointment
    static List<Appointment> scheduledAppointments = new ArrayList<>();  //list to store the appointments 

    // Getter for scheduledAppointments
    public static List<Appointment> getScheduledAppointments() {
        return scheduledAppointments;
    }

     // Getter for appointmentId
     public String getAppointmentId() {
        return appointmentId;
    }


    // Constructor for Appointment
    public Appointment(String family_name, String first_name, String address, String ContactInfo, int day, int month, int year , int hour) {
        this.family_name = family_name;
        this.first_name = first_name;
        this.address = address;
        this.ContactInfo = ContactInfo;
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.appointmentId = generateAppointmentId(); // Generate unique ID
    }  

    // Method to generate a unique appointment ID
    private String generateAppointmentId() {
        return family_name + "_" + first_name + "_" + day + "-" + month + "-" + year + "_" + hour;
    }


    // Method to check if the appointment slot is free
    public boolean is_free() {
        for (Appointment x : scheduledAppointments){
            if (
                x.day == this.day &&
                x.month == this.month &&
                x.year == this.year &&
                x.hour == this.hour
            ) {
                return false;  //slot is already booked
            }
        }
        return true;   //slot is free
    }


    public boolean addAppointment() {
        if (this.is_free()) {
            scheduledAppointments.add(this);  // Appointment successfully added
            System.out.println("Appointment successfully added.");
            return true;
        } else {
            System.out.println("Failed to add appointment: Slot is already booked.");
            return false;
        }
    }

    // Method to cancel an appointment by ID
    public static boolean cancelAppointment(String id) {
        for (Appointment x : scheduledAppointments) {
            if (x.getAppointmentId().equals(id)) {
                scheduledAppointments.remove(x);
                System.out.println("Appointment with ID " + id + " successfully canceled.");
                return true;
            }
        }
        System.out.println("No appointment found with ID: " + id);
        return false;
    }


    // toString() method for displaying appointment details
    @Override
    public String toString() {
        return "Appointment ID: " + appointmentId + "\n" +
               "Name: " + family_name + " " + first_name + "\n" +
               "Address: " + address + "\n" +
               "Contact Info: " + ContactInfo + "\n" +
               "Date: " + day + "/" + month + "/" + year + " at " + hour + ":00";
    }
    


}