package main.classes;



import java.util.Scanner;  

public class PatientMenu {
    public static void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Patient Management Menu ===");
            System.out.println("1. Add a Patient");
            System.out.println("2. Update Patient Information");
            System.out.println("3. View Patient Records");
            System.out.println("4. Delete a Patient");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Add a Patient (to be implemented)");
                    break;
                case 2:
                    System.out.println("Update Patient Information (to be implemented)");
                    break;
                case 3:
                    System.out.println("View Patient Records (to be implemented)");
                    break;
                case 4:
                    System.out.println("Delete a Patient (to be implemented)");
                    break;
                case 5:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
