package main.classes;
import java.util.ArrayList;
import java.util.List;

public class Appointment {
    public String family_name;    //family name of the patient
    public String first_name;     //first name of the patient
    public String email;          //email of the patient
    public String phone;          //phone number
    public int day;               //the day of the appointment
    public int month;             //the month of the appointment
    public int year;              //the year of the appointment
    public int hour;              //the hour of the appointment
    static List<Appointment> scheduledAppointments = new ArrayList<>();  //list to store the appointments 

    // Getter for scheduledAppointments
    public static List<Appointment> getScheduledAppointments() {
        return scheduledAppointments;
    }


    // Constructor for Appointment
    public Appointment(String family_name, String first_name, String email, String phone, int day, int month, int year , int hour) {
        this.family_name = family_name;
        this.first_name = first_name;
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
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
    

}
