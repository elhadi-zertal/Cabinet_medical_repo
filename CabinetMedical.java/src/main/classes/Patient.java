package main.classes;
import java.util.ArrayList;
import java.util.List;



    public class Patient {

    // Personal infos 
    private String id;
    private String name;
    private int age;
    private String gender;

    //Measurements
    private double height; 
    private double weight; 


    // Contact informations
    private String address;
    private String ContactInfo;

    // RDV Infos
    private String lastAppointmentDate;
    private String nextAppointmentDate;
    


    // Medical Status
    private List<String> medicalHistory;
    private List<String> currentMedications;
    private List<String> allergies;
    private  String BloodType;







    // Constructor
    public Patient(String id, String name, int age, String gender, double height, double weight, String address,
            String contactInfo, String lastAppointmentDate, String nextAppointmentDate, List<String> medicalHistory,
            List<String> currentMedications, List<String> allergies, String bloodType) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.address = address;
        ContactInfo = contactInfo;
        this.lastAppointmentDate = lastAppointmentDate;
        this.nextAppointmentDate = nextAppointmentDate;
        this.medicalHistory = medicalHistory;
        this.currentMedications = currentMedications;
        this.allergies = allergies;
        BloodType = bloodType;
    }





    
    
    // Getters and Setters
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getContactInfo() {
        return ContactInfo;
    }
    public void setContactInfo(String contactInfo) {
        ContactInfo = contactInfo;
    }
    public String getLastAppointmentDate() {
        return lastAppointmentDate;
    }
    public void setLastAppointmentDate(String lastAppointmentDate) {
        this.lastAppointmentDate = lastAppointmentDate;
    }
    public String getNextAppointmentDate() {
        return nextAppointmentDate;
    }
    public void setNextAppointmentDate(String nextAppointmentDate) {
        this.nextAppointmentDate = nextAppointmentDate;
    }
    public List<String> getMedicalHistory() {
        return medicalHistory;
    }
    public void setMedicalHistory(List<String> medicalHistory) {
        this.medicalHistory = medicalHistory;
    }
    public List<String> getCurrentMedications() {
        return currentMedications;
    }
    public void setCurrentMedications(List<String> currentMedications) {
        this.currentMedications = currentMedications;
    }
    public List<String> getAllergies() {
        return allergies;
    }
    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }
    public String getBloodType() {
        return BloodType;
    }
    public void setBloodType(String bloodType) {
        BloodType = bloodType;
    }
   public String getId() {
        return id;

    }





    @Override
public String toString() {
    return "Patient{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", age=" + age +
            ", gender='" + gender + '\'' +
            ", height=" + height +
            ", weight=" + weight +
            ", address='" + address + '\'' +
            ", contactInfo='" + ContactInfo + '\'' +
            ", lastAppointmentDate='" + lastAppointmentDate + '\'' +
            ", nextAppointmentDate='" + nextAppointmentDate + '\'' +
            ", medicalHistory=" + medicalHistory +
            ", currentMedications=" + currentMedications +
            ", allergies=" + allergies +
            ", bloodType='" + BloodType + '\'' +
            '}';
}



// Crud operations for the patient object

    public class PatientManager {
    private List<Patient> patients = new ArrayList<>();  // a list That containst patients with their infos
    public void addPatient(Patient patient) {
        patients.add(patient); // Adding a patient to the list 
    }
    
    public  Patient getPatientById(String id){
        for (Patient patient : patients) { 
            if (patient.getId().equals(id)) //comparing The ID with the given id To see the patient 
            {
                return patient; // returning the patient if the id is found
            }
        }
        return null;
    }


    // Updating an information of an patient by his id , Like date of rdv or medical history this function will give true when the change is done succesfuly and it will just change the guven data
public boolean updatePatient(String id, String address, String contactInfo, String lastAppointmentDate,String nextAppointmentDate, List<String> medicalHistory, List<String> currentMedications, List<String> allergies) {
for (Patient patient : patients) {
if (patient.getId().equals(id)) {
// Update only if new data is provided
if (address != null) patient.setAddress(address);
if (contactInfo != null) patient.setContactInfo(contactInfo);
if (lastAppointmentDate != null) patient.setLastAppointmentDate(lastAppointmentDate);
if (nextAppointmentDate != null) patient.setNextAppointmentDate(nextAppointmentDate);
if (medicalHistory != null) patient.setMedicalHistory(medicalHistory);
if (currentMedications != null) patient.setCurrentMedications(currentMedications);
if (allergies != null) patient.setAllergies(allergies);
return true; // Update was done successful
}
}
return false; // when Patient not found
}

public void deletePatient(String id) { // deleting a patient by his id 
    boolean Removed=patients.removeIf(patient-> patient.id.equals(id)); // removing the patient from the list if the id is found
    if (Removed==true ) {
        System.out.println("Patient removed");
        
    }else{
        System.out.println("Patient not found");
    }
        
        }
}











    }


   


    

    





    
    


    
             










































