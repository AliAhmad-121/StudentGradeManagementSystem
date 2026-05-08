import java.util.Scanner;
import java.io.*;
import java.text.DecimalFormat;

public class StudentGradeManagementSystem {
    // Scanner object for user input
    static Scanner input = new Scanner(System.in);

    // Decimal formatter for averages
    static DecimalFormat df = new DecimalFormat("0.00");

    // Arrays used to store student information
    static String[] studentNames = new String[100];
    static String[] studentIDs = new String[100];
    static double[][] marks = new double[100][3];
    static double[] totals = new double[100];
    static double[] averages = new double[100];
    static char[] letterGrades = new char[100];
    static String[] status = new String[100];

    // Variable to track total number of students
    static int studentCount = 0;

    // Main method - program starts here
    public static void main(String[] args) {

        int choice;

        // Loop keeps program running until user exits
        do {

            // Display program menu
            System.out.println("\n====================================================");
            System.out.println("        STUDENT GRADE MANAGEMENT SYSTEM");
            System.out.println("====================================================");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Display Student Report Card");
            System.out.println("5. Display Class Summary");
            System.out.println("6. Find Top Student");
            System.out.println("7. Save Records to File");
            System.out.println("8. Load Records from File");
            System.out.println("9. Exit");
            System.out.println("====================================================");

            // Read user choice
            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            // Switch statement handles menu selection
            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    displayAllStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    displayReportCard();
                    break;

                case 5:
                    displayClassSummary();
                    break;

                case 6:
                    findTopStudent();
                    break;

                case 7:
                    saveToFile();
                    break;

                case 8:
                    loadFromFile();
                    break;

                case 9:
                    System.out.println("Program terminated successfully.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 9);
    }

    // ====================================================
    // Method to add student information
    // ====================================================
    public static void addStudent() {

        // Prevent adding more than 100 students
        if (studentCount >= 100) {
            System.out.println("Maximum student limit reached.");
            return;
        }

        input.nextLine();

        // Input student ID
        System.out.print("Enter Student ID: ");
        studentIDs[studentCount] = input.nextLine();

        // Input student name
        System.out.print("Enter Student Name: ");
        studentNames[studentCount] = input.nextLine();

        try {

            // Input marks for 3 subjects
            for (int i = 0; i < 3; i++) {

                System.out.print("Enter mark for Subject " + (i + 1) + ": ");
                marks[studentCount][i] = input.nextDouble();
            }

            // Calculate total marks
            totals[studentCount] = calculateTotal(studentCount);

            // Calculate average marks
            averages[studentCount] = calculateAverage(studentCount);

            // Assign grade and pass/fail status
            assignGrade(studentCount);

            // Increase student count
            studentCount++;

            System.out.println("Student added successfully!");

        }

        // Exception handling for invalid input
        catch (Exception e) {

            System.out.println("Invalid input. Please enter numbers only.");
            input.nextLine();
        }
    }

    // ====================================================
    // Method to calculate total marks
    // ====================================================
    public static double calculateTotal(int index) {

        return marks[index][0]
                + marks[index][1]
                + marks[index][2];
    }

    // ====================================================
    // Method to calculate average marks
    // ====================================================
    public static double calculateAverage(int index) {

        return totals[index] / 3;
    }

    // ====================================================
    // Method to assign letter grades and status
    // ====================================================
    public static void assignGrade(int index) {

        if (averages[index] >= 90) {
            letterGrades[index] = 'A';
            status[index] = "Pass";
        }

        else if (averages[index] >= 80) {
            letterGrades[index] = 'B';
            status[index] = "Pass";
        }

        else if (averages[index] >= 70) {
            letterGrades[index] = 'C';
            status[index] = "Pass";
        }

        else if (averages[index] >= 60) {
            letterGrades[index] = 'D';
            status[index] = "Pass";
        }

        else {
            letterGrades[index] = 'F';
            status[index] = "Fail";
        }
    }

    // ====================================================
    // Method to display all student records
    // ====================================================
    public static void displayAllStudents() {

        // Check if no students exist
        if (studentCount == 0) {
            System.out.println("No students found.");
            return;
        }

        // Loop through all students
        for (int i = 0; i < studentCount; i++) {

            System.out.println("\n====================================================");
            System.out.println("Student Number : " + (i + 1));
            System.out.println("Student ID     : " + studentIDs[i]);
            System.out.println("Student Name   : " + studentNames[i]);

            // Display subject marks
            for (int j = 0; j < 3; j++) {

                System.out.println("Subject " + (j + 1) + "      : " + marks[i][j]);
            }

            // Display calculations
            System.out.println("----------------------------------------------------");
            System.out.println("Total          : " + totals[i]);
            System.out.println("Average        : " + df.format(averages[i]));
            System.out.println("Grade          : " + letterGrades[i]);
            System.out.println("Status         : " + status[i]);
            System.out.println("====================================================");
        }
    }

    // ====================================================
    // Method to search for a student
    // ====================================================
    public static void searchStudent() {

        input.nextLine();

        // Search using ID or name
        System.out.print("Enter Student ID or Name: ");
        String search = input.nextLine();

        boolean found = false;

        // Loop through student records
        for (int i = 0; i < studentCount; i++) {

            if (studentIDs[i].equalsIgnoreCase(search)
                    || studentNames[i].equalsIgnoreCase(search)) {

                System.out.println("\nStudent Found!");
                System.out.println("ID      : " + studentIDs[i]);
                System.out.println("Name    : " + studentNames[i]);
                System.out.println("Average : " + df.format(averages[i]));
                System.out.println("Grade   : " + letterGrades[i]);

                found = true;
            }
        }

        // If student does not exist
        if (!found) {
            System.out.println("Student not found.");
        }
    }

    // ====================================================
    // Method to display student report card
    // ====================================================
    public static void displayReportCard() {

        input.nextLine();

        System.out.print("Enter Student ID: ");
        String id = input.nextLine();

        boolean found = false;

        // Search for matching student ID
        for (int i = 0; i < studentCount; i++) {

            if (studentIDs[i].equalsIgnoreCase(id)) {

                System.out.println("\n============== REPORT CARD ==============");
                System.out.println("Student ID   : " + studentIDs[i]);
                System.out.println("Student Name : " + studentNames[i]);
                System.out.println("Subject 1    : " + marks[i][0]);
                System.out.println("Subject 2    : " + marks[i][1]);
                System.out.println("Subject 3    : " + marks[i][2]);
                System.out.println("-----------------------------------------");
                System.out.println("Total        : " + totals[i]);
                System.out.println("Average      : " + df.format(averages[i]));
                System.out.println("Grade        : " + letterGrades[i]);
                System.out.println("Status       : " + status[i]);
                System.out.println("=========================================");

                found = true;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }

    // ====================================================
    // Method to display class summary
    // ====================================================
    public static void displayClassSummary() {

        if (studentCount == 0) {
            System.out.println("No students available.");
            return;
        }

        double highest = averages[0];
        double lowest = averages[0];
        double classTotal = 0;

        int passCount = 0;
        int failCount = 0;

        // Loop through students for calculations
        for (int i = 0; i < studentCount; i++) {

            classTotal += averages[i];

            if (averages[i] > highest) {
                highest = averages[i];
            }

            if (averages[i] < lowest) {
                lowest = averages[i];
            }

            if (status[i].equals("Pass")) {
                passCount++;
            }

            else {
                failCount++;
            }
        }

        // Calculate class average
        double classAverage = classTotal / studentCount;

        // Display class statistics
        System.out.println("\n============== CLASS SUMMARY ==============");
        System.out.println("Class Average   : " + df.format(classAverage));
        System.out.println("Highest Average : " + df.format(highest));
        System.out.println("Lowest Average  : " + df.format(lowest));
        System.out.println("Passed Students : " + passCount);
        System.out.println("Failed Students : " + failCount);
        System.out.println("===========================================");
    }

    // ====================================================
    // Method to find top student
    // ====================================================
    public static void findTopStudent() {

        if (studentCount == 0) {
            System.out.println("No students available.");
            return;
        }

        int topIndex = 0;

        // Compare averages to find highest
        for (int i = 1; i < studentCount; i++) {

            if (averages[i] > averages[topIndex]) {
                topIndex = i;
            }
        }

        // Display top student information
        System.out.println("\n================ TOP STUDENT ================");
        System.out.println("Student ID   : " + studentIDs[topIndex]);
        System.out.println("Student Name : " + studentNames[topIndex]);
        System.out.println("Average      : " + df.format(averages[topIndex]));
        System.out.println("Grade        : " + letterGrades[topIndex]);
        System.out.println("=============================================");
    }

    // ====================================================
    // Method to save student records to text file
    // ====================================================
    public static void saveToFile() {

        try {

            // Create writer object
            PrintWriter writer = new PrintWriter("students.txt");

            // Save all student records
            for (int i = 0; i < studentCount; i++) {

                writer.println(
                        studentIDs[i] + ","
                                + studentNames[i] + ","
                                + marks[i][0] + ","
                                + marks[i][1] + ","
                                + marks[i][2] + ","
                                + totals[i] + ","
                                + averages[i] + ","
                                + letterGrades[i] + ","
                                + status[i]
                );
            }

            writer.close();

            System.out.println("Records saved successfully!");
        }

        // Exception handling
        catch (Exception e) {

            System.out.println("Error saving file.");
        }
    }

    // ====================================================
    // Method to load student records from text file
    // ====================================================
    public static void loadFromFile() {

        try {

            // Open text file
            File file = new File("students.txt");

            Scanner fileInput = new Scanner(file);

            studentCount = 0;

            // Read file line by line
            while (fileInput.hasNextLine()) {

                String line = fileInput.nextLine();

                // Split data using commas
                String[] data = line.split(",");

                studentIDs[studentCount] = data[0];
                studentNames[studentCount] = data[1];

                marks[studentCount][0] = Double.parseDouble(data[2]);
                marks[studentCount][1] = Double.parseDouble(data[3]);
                marks[studentCount][2] = Double.parseDouble(data[4]);

                totals[studentCount] = Double.parseDouble(data[5]);
                averages[studentCount] = Double.parseDouble(data[6]);

                letterGrades[studentCount] = data[7].charAt(0);

                status[studentCount] = data[8];

                studentCount++;
            }

            fileInput.close();

            System.out.println("Records loaded successfully!");

        }

        // Exception handling
        catch (Exception e) {

            System.out.println("Error loading file.");
        }
    }
}