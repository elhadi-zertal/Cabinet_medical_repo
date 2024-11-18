package main.classes;

import java.time.LocalDate;


    public class MedicalExam {
    private String examType;    // Type of exam (e.g., "Blood Test", "X-ray", "MRI")
    private String reason;      // Reason for the exam (e.g., "Check cholesterol", "Suspected fracture")
    private LocalDate date;     // Date the exam was ordered
    private String orderedBy;   // Name of the doctor who ordered the exam
    private String result;      // Result of the exam (e.g., "Normal", "Fracture detected")

    // Constructor
    public MedicalExam(String examType, String reason, LocalDate date, String orderedBy, String result) {
        this.examType = examType;
        this.reason = reason;
        this.date = date;
        this.orderedBy = orderedBy;
        this.result = result;
    }

    // Getters and Setters
    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(String orderedBy) {
        this.orderedBy = orderedBy;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    // Method to return exam details along with the result as a formatted string
    public String getExamDetails() {
        return "Exam Type: " + examType + "\n" +
               "Reason: " + reason + "\n" +
               "Recommended by: " + orderedBy + "\n" +
               "Date: " + date + "\n" +
               "Result: " + result;
    }
}



