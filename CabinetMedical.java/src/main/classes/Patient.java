package main.classes;
import java.util.List;

public class Patient {
    private String id;
    private String name;
    private int age;
    private String gender;
    private double height; 
    private double weight; 
    private List<String> medicalHistory;
    private List<String> currentMedications;
    private List<String> allergies;
    private String contactInfo;

    public Patient(String id, String name, int age, String gender, double height, double weight,
                   List<String> medicalHistory, List<String> currentMedications, List<String> allergies, 
                   String contactInfo) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.medicalHistory = medicalHistory;
        this.currentMedications = currentMedications;
        this.allergies = allergies;
        this.contactInfo = contactInfo;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    
   
    public String getName() {
        return name;
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

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
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
                ", medicalHistory=" + medicalHistory +
                ", currentMedications=" + currentMedications +
                ", allergies=" + allergies +
                ", contactInfo='" + contactInfo + '\'' +
                '}';
    }
}
