    import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Patient {

    private String name;
    private int age;
    private String gender;
    private String medicalHistory;
    private String username; // Added username field
    private String password; // Added password field
    private MedicalRecord medicalRecord;

    public Patient(String name, int age, String gender, String medicalHistory, String username, String password) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.medicalHistory = medicalHistory;
        this.username = username;
        this.password = password;
        this.medicalRecord = new MedicalRecord();
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nAge: " + age + "\nGender: " + gender + "\nMedical History: " + medicalHistory;
    }
}

class Doctor {

    private String name;
    private String specialization;

    public Doctor(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nSpecialization: " + specialization;
    }
}

class Appointment {

    private Patient patient;
    private Doctor doctor;
    private String date;

    public Appointment(Patient patient, Doctor doctor, String date) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Patient: " + patient.getName() + "\nDoctor: " + doctor.getName() + "\nDate: " + date;
    }
}

class Billing {

    private Patient patient;
    private double amount;
    private String billingDate;

    public Billing(Patient patient, double amount, String billingDate) {
        this.patient = patient;
        this.amount = amount;
        this.billingDate = billingDate;
    }

    @Override
    public String toString() {
        return "Patient: " + patient.getName() + "\nAmount: $" + amount + "\nBilling Date: " + billingDate;
    }
}

class MedicalRecord {

    private List<String> records;

    public MedicalRecord() {
        this.records = new ArrayList<>();
    }

    public void addRecord(String record) {
        records.add(record);
    }

    public List<String> getRecords() {
        return records;
    }
}

class PatientPortal {

    private static Scanner scanner = new Scanner(System.in);

    public static Patient login(List<Patient> patients) {
        System.out.print("Enter patient username: ");
        String username = scanner.nextLine();
        System.out.print("Enter patient password: ");
        String password = scanner.nextLine();

        for (Patient patient : patients) {
            if (patient.getUsername().equals(username) && patient.getPassword().equals(password)) {
                return patient;
            }
        }

        return null; // Patient not found or invalid credentials
    }

    public static void accessMedicalRecords(Patient patient) {
        if (patient != null) {
            List<String> records = patient.getMedicalRecord().getRecords();
            if (records.isEmpty()) {
                System.out.println("No medical records available for this patient.");
            } else {
                System.out.println("Medical Records for " + patient.getName() + ":");
                for (int i = 0; i < records.size(); i++) {
                    System.out.println((i + 1) + ". " + records.get(i));
                }
            }
        } else {
            System.out.println("Patient not found or invalid credentials. Access denied.");
        }
    }
}

public class HealthcareManagementSystem {

    private static List<Patient> patients = new ArrayList<>();
    private static List<Doctor> doctors = new ArrayList<>();
    private static List<Appointment> appointments = new ArrayList<>();
    private static List<Billing> billings = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Healthcare Management System");
            System.out.println("1. Add Patient");
            System.out.println("2. View Patients");
            System.out.println("3. Add Doctor");
            System.out.println("4. View Doctors");
            System.out.println("5. Schedule Appointment");
            System.out.println("6. View Appointments");
            System.out.println("7. Add Billing");
            System.out.println("8. View Billings");
            System.out.println("9. Add Medical Record");
            System.out.println("10. View Medical Records");
            System.out.println("11. Patient Portal"); // New option for Patient Portal
            System.out.println("12. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addPatient();
                    break;
                case 2:
                    viewPatients();
                    break;
                case 3:
                    addDoctor();
                    break;
                case 4:
                    viewDoctors();
                    break;
                case 5:
                    scheduleAppointment();
                    break;
                case 6:
                    viewAppointments();
                    break;
                case 7:
                    addBilling();
                    break;
                case 8:
                    viewBillings();
                    break;
                case 9:
                    addMedicalRecord();
                    break;
                case 10:
                    viewMedicalRecords();
                    break;//
                case 11:
                    patientPortal(); // Added Patient Portal option
                    break;
                case 12:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addPatient() {
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine();
        System.out.print("Enter patient age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter patient gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter patient medical history: ");
        String medicalHistory = scanner.nextLine();
        System.out.print("Set a username for the patient: ");
        String username = scanner.nextLine();
        System.out.print("Set a password for the patient: ");
        String password = scanner.nextLine();

        Patient patient = new Patient(name, age, gender, medicalHistory, username, password);
        patients.add(patient);

        System.out.println("Patient added successfully!");
    }

    private static void viewPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients in the system.");
        } else {
            System.out.println("List of Patients:");
            for (int i = 0; i < patients.size(); i++) {
                System.out.println("Patient " + (i + 1) + ":\n" + patients.get(i));
            }
        }
    }

    private static void addDoctor() {
        System.out.print("Enter doctor name: ");
        String name = scanner.nextLine();
        System.out.print("Enter doctor specialization: ");
        String specialization = scanner.nextLine();

        Doctor doctor = new Doctor(name, specialization);
        doctors.add(doctor);

        System.out.println("Doctor added successfully!");
    }

    private static void viewDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("No doctors in the system.");
        } else {
            System.out.println("List of Doctors:");
            for (int i = 0; i < doctors.size(); i++) {
                System.out.println("Doctor " + (i + 1) + ":\n" + doctors.get(i));
            }
        }
    }

    private static void scheduleAppointment() {
        if (patients.isEmpty() || doctors.isEmpty()) {
            System.out.println("You must have patients and doctors to schedule an appointment.");
        } else {
            System.out.println("Available Patients:");
            for (int i = 0; i < patients.size(); i++) {
                System.out.println((i + 1) + ". " + patients.get(i).getName());
            }

            System.out.println("Available Doctors:");
            for (int i = 0; i < doctors.size(); i++) {
                System.out.println((i + 1) + ". " + doctors.get(i).getName());
            }

            System.out.print("Select a patient (enter the number): ");
            int patientChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            System.out.print("Select a doctor (enter the number): ");
            int doctorChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (patientChoice > 0 && patientChoice <= patients.size()
                    && doctorChoice > 0 && doctorChoice <= doctors.size()) {

                Patient selectedPatient = patients.get(patientChoice - 1);
                Doctor selectedDoctor = doctors.get(doctorChoice - 1);

                System.out.print("Enter appointment date (e.g., DD/MM/YYYY): ");
                String date = scanner.nextLine();

                Appointment appointment = new Appointment(selectedPatient, selectedDoctor, date);
                appointments.add(appointment);

                System.out.println("Appointment scheduled successfully!");
            } else {
                System.out.println("Invalid patient or doctor selection.");
            }
        }
    }

    private static void viewAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments scheduled.");
        } else {
            System.out.println("List of Appointments:");
            for (int i = 0; i < appointments.size(); i++) {
                System.out.println("Appointment " + (i + 1) + ":\n" + appointments.get(i));
            }
        }
    }

    private static void addBilling() {
        if (patients.isEmpty()) {
            System.out.println("You must have patients to add billing information.");
        } else {
            System.out.println("Available Patients:");
            for (int i = 0; i < patients.size(); i++) {
                System.out.println((i + 1) + ". " + patients.get(i).getName());
            }

            System.out.print("Select a patient (enter the number): ");
            int patientChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (patientChoice > 0 && patientChoice <= patients.size()) {
                Patient selectedPatient = patients.get(patientChoice - 1);

                System.out.print("Enter billing amount: $");
                double amount = scanner.nextDouble();
                scanner.nextLine(); // Consume the newline character

                System.out.print("Enter billing date (e.g., DD/MM/YYYY): ");
                String billingDate = scanner.nextLine();

                Billing billing = new Billing(selectedPatient, amount, billingDate);
                billings.add(billing);

                System.out.println("Billing information added successfully!");
            } else {
                System.out.println("Invalid patient selection.");
            }
        }
    }

    private static void viewBillings() {
        if (billings.isEmpty()) {
            System.out.println("No billing information available.");
        } else {
            System.out.println("List of Billing Information:");
            for (int i = 0; i < billings.size(); i++) {
                System.out.println("Billing " + (i + 1) + ":\n" + billings.get(i));
            }
        }
    }

    private static void addMedicalRecord() {
        System.out.print("Enter patient name to add a medical record: ");
        String patientName = scanner.nextLine();

        Patient patient = findPatientByName(patientName);

        if (patient != null) {
            System.out.print("Enter medical record description: ");
            String record = scanner.nextLine();
            patient.getMedicalRecord().addRecord(record);
            System.out.println("Medical record added successfully!");
        } else {
            System.out.println("Patient not found.");
        }
    }

    private static void viewMedicalRecords() {
        System.out.print("Enter patient name: ");
        String patientName = scanner.nextLine();

        Patient patient = findPatientByName(patientName);

        if (patient != null) {
            System.out.print("Enter patient password: ");
            String patientPassword = scanner.nextLine();

            if (patient.getPassword().equals(patientPassword)) {
                List<String> records = patient.getMedicalRecord().getRecords();
                if (records.isEmpty()) {
                    System.out.println("No medical records available for this patient.");
                } else {
                    System.out.println("Medical Records for " + patientName + ":");
                    for (int i = 0; i < records.size(); i++) {
                        System.out.println((i + 1) + ". " + records.get(i));
                    }
                }
            } else {
                System.out.println("Invalid password. Access denied.");
            }
        } else {
            System.out.println("Patient not found.");
        }
    }

    private static Patient findPatientByName(String name) {
        for (Patient patient : patients) {
            if (patient.getName().equalsIgnoreCase(name)) {
                return patient;
            }
        }
        return null;
    }

    private static void patientPortal() {
        System.out.println("Patient Portal");
        Patient patient = PatientPortal.login(patients);

        if (patient != null) {
            System.out.println("Welcome, " + patient.getName() + "!");
            while (true) {
                System.out.println("1. View Medical Records");
                System.out.println("2. Exit Patient Portal");
                System.out.print("Enter your choice: ");
                int portalChoice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (portalChoice) {
                    case 1:
                        PatientPortal.accessMedicalRecords(patient);
                        break;
                    case 2:
                        return; // Return to the main menu
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid credentials. Access denied.");
        }
    }
}