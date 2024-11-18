package main.classes;
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





   


    

    





    
    


    
             }














































