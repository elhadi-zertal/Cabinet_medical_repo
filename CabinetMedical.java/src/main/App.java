package main;
<<<<<<< HEAD
import java.util.Scanner;
import main.classes.Appointment;
import main.classes.AppointmentManager;
import main.classes.Consultation;


public class App {
    public static void main(String[] args) throws Exception {
      

        Scanner scanner = new Scanner(System.in);
        int choice;
        
        do {
            System.out.println("\n=== Gestion d'un Cabinet Médical ===");
            System.out.println("1. Gestion et Suivi du Dossier Médical");
            System.out.println("2. Gestion des Rendez-vous");
            System.out.println("3. Gestion des Fiches Patients");
            System.out.println("4. Consulter le Dossier Médical");
            System.out.println("5. Rédiger une Ordonnance");
            System.out.println("6. Gérer les Certificats Médicaux");
            System.out.println("7. Quitter");
            System.out.print("Entrez votre choix : ");
        
            choice = scanner.nextInt();
            scanner.nextLine(); // Consommer la ligne restante
        
            switch (choice) {
                case 1:
                    System.out.println("Gestion et Suivi du Dossier Médical (à implémenter)");
                    break;
                case 2:
                    System.out.println("Gestion des Rendez-vous (à implémenter)");
                    break;
                case 3:
                    System.out.println("Gestion des Fiches Patients (à implémenter)");
                    break;
                case 4:
                    System.out.println("Consulter le Dossier Médical (à implémenter)");
                    break;
                case 5:
                    System.out.println("Rédiger une Ordonnance (à implémenter)");
                    break;
                case 6:
                    System.out.println("Gérer les Certificats Médicaux (à implémenter)");
                    break;
                case 7:
                    System.out.println("Fermeture du système. Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide ! Veuillez réessayer.");
            }
        } while (choice != 7);
        
        scanner.close();
        

    }
}
