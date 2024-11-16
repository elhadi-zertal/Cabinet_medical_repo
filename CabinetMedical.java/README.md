# Project Structure

CabinetMedical/
│
├── src/                           # Source code directory
│   ├── main/                       # Main package (core application logic)
│   │   ├── classes/                # Core model classes
│   │   │   ├── Patient.java        # Patient model class
│   │   │   ├── Appointment.java    # Appointment model class
│   │   │   └── Consultation.java   # Consultation model class
│   │   ├── dao/                    # Data access (e.g., Database connection)
│   │   │   └── Database.java       # Database class for CRUD operations
│   │   ├── gui/                    # GUI classes
│   │   │   └── MainFrame.java      # Main GUI frame class
│   │   └── App.java                # Main entry point of the application
│
└── README.md                       # Project documentation file
