package main.classes;

import java.util.Scanner;

public class PatientMenu {

  // This method will display the menu and handle user input
  public static void displayMenu() {
      Scanner scanner = new Scanner(System.in);
      int choice;

      do {
          // Display the menu options
          System.out.println("\n=== Patient Management Menu ===");
          System.out.println("1. Add a Patient");
          System.out.println("2. Update Patient Information");
          System.out.println("3. View Patient Records");
          System.out.println("4. Delete a Patient");
          System.out.println("5. Back to Main Menu");
          System.out.print("Enter your choice: ");

          // Get the user's choice
          choice = scanner.nextInt();
          scanner.nextLine(); // Consume the newline character

          // Perform an action based on the user's choice
          switch (choice) {
              case 1:
                  //addPatient
                  break;
              case 2:
                  //updatePatient
                  break;
              case 3:
                  //viewPatient();
                  break;
              case 4:
                  //deletePatient();
                  break;
              case 5:
                 // System.out.println("Returning to the main menu...");
                  break;
              default:
                 // System.out.println("Invalid choice! Please try again.");
          }
      } while (choice != 5); // Keep showing the menu until the user selects option 5 (exit to main menu)

      scanner.close(); // Close the scanner when finished
  }
}