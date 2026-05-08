import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class StudentGradeGUI extends JFrame implements ActionListener {

    // Arrays
    String[] studentNames = new String[100];
    String[] studentIDs = new String[100];
    double[][] marks = new double[100][3];
    double[] totals = new double[100];
    double[] averages = new double[100];
    char[] grades = new char[100];
    String[] status = new String[100];

    int studentCount = 0;

    DecimalFormat df = new DecimalFormat("0.00");

    // GUI Components
    JLabel titleLabel;

    JLabel idLabel;
    JLabel nameLabel;
    JLabel subject1Label;
    JLabel subject2Label;
    JLabel subject3Label;

    JTextField idField;
    JTextField nameField;
    JTextField subject1Field;
    JTextField subject2Field;
    JTextField subject3Field;

    JButton addButton;
    JButton displayButton;
    JButton searchButton;
    JButton summaryButton;
    JButton topStudentButton;
    JButton clearButton;

    JTextArea outputArea;

    public StudentGradeGUI() {

        setTitle("Student Grade Management System");
        setSize(700, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        titleLabel = new JLabel("STUDENT GRADE MANAGEMENT SYSTEM");
        titleLabel.setBounds(150, 20, 400, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        add(titleLabel);

        idLabel = new JLabel("Student ID:");
        idLabel.setBounds(50, 80, 100, 25);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(170, 80, 200, 25);
        add(idField);

        nameLabel = new JLabel("Student Name:");
        nameLabel.setBounds(50, 120, 100, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(170, 120, 200, 25);
        add(nameField);

        subject1Label = new JLabel("Subject 1:");
        subject1Label.setBounds(50, 160, 100, 25);
        add(subject1Label);

        subject1Field = new JTextField();
        subject1Field.setBounds(170, 160, 200, 25);
        add(subject1Field);

        subject2Label = new JLabel("Subject 2:");
        subject2Label.setBounds(50, 200, 100, 25);
        add(subject2Label);

        subject2Field = new JTextField();
        subject2Field.setBounds(170, 200, 200, 25);
        add(subject2Field);

        subject3Label = new JLabel("Subject 3:");
        subject3Label.setBounds(50, 240, 100, 25);
        add(subject3Label);

        subject3Field = new JTextField();
        subject3Field.setBounds(170, 240, 200, 25);
        add(subject3Field);

        addButton = new JButton("Add Student");
        addButton.setBounds(50, 300, 150, 35);
        addButton.addActionListener(this);
        add(addButton);

        displayButton = new JButton("Display Students");
        displayButton.setBounds(220, 300, 150, 35);
        displayButton.addActionListener(this);
        add(displayButton);

        searchButton = new JButton("Search Student");
        searchButton.setBounds(390, 300, 150, 35);
        searchButton.addActionListener(this);
        add(searchButton);

        summaryButton = new JButton("Class Summary");
        summaryButton.setBounds(50, 360, 150, 35);
        summaryButton.addActionListener(this);
        add(summaryButton);

        topStudentButton = new JButton("Top Student");
        topStudentButton.setBounds(220, 360, 150, 35);
        topStudentButton.addActionListener(this);
        add(topStudentButton);

        clearButton = new JButton("Clear Fields");
        clearButton.setBounds(390, 360, 150, 35);
        clearButton.addActionListener(this);
        add(clearButton);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBounds(50, 430, 580, 150);
        add(scrollPane);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addButton) {
            addStudent();
        }

        else if (e.getSource() == displayButton) {
            displayStudents();
        }

        else if (e.getSource() == searchButton) {
            searchStudent();
        }

        else if (e.getSource() == summaryButton) {
            displaySummary();
        }

        else if (e.getSource() == topStudentButton) {
            findTopStudent();
        }

        else if (e.getSource() == clearButton) {
            clearFields();
        }
    }

    public void addStudent() {

        try {

            studentIDs[studentCount] = idField.getText();
            studentNames[studentCount] = nameField.getText();

            marks[studentCount][0] = Double.parseDouble(subject1Field.getText());
            marks[studentCount][1] = Double.parseDouble(subject2Field.getText());
            marks[studentCount][2] = Double.parseDouble(subject3Field.getText());

            totals[studentCount] = marks[studentCount][0]
                    + marks[studentCount][1]
                    + marks[studentCount][2];

            averages[studentCount] = totals[studentCount] / 3;

            if (averages[studentCount] >= 90) {
                grades[studentCount] = 'A';
                status[studentCount] = "Pass";
            }

            else if (averages[studentCount] >= 80) {
                grades[studentCount] = 'B';
                status[studentCount] = "Pass";
            }

            else if (averages[studentCount] >= 70) {
                grades[studentCount] = 'C';
                status[studentCount] = "Pass";
            }

            else if (averages[studentCount] >= 60) {
                grades[studentCount] = 'D';
                status[studentCount] = "Pass";
            }

            else {
                grades[studentCount] = 'F';
                status[studentCount] = "Fail";
            }

            outputArea.setText("Student added successfully!\n");

            studentCount++;

        }

        catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid marks.");
        }
    }

    public void displayStudents() {

        outputArea.setText("");

        for (int i = 0; i < studentCount; i++) {

            outputArea.append("====================================\n");
            outputArea.append("Student ID: " + studentIDs[i] + "\n");
            outputArea.append("Student Name: " + studentNames[i] + "\n");
            outputArea.append("Subject 1: " + marks[i][0] + "\n");
            outputArea.append("Subject 2: " + marks[i][1] + "\n");
            outputArea.append("Subject 3: " + marks[i][2] + "\n");
            outputArea.append("Total: " + totals[i] + "\n");
            outputArea.append("Average: " + df.format(averages[i]) + "\n");
            outputArea.append("Grade: " + grades[i] + "\n");
            outputArea.append("Status: " + status[i] + "\n");
        }
    }

    public void searchStudent() {

        String searchID = JOptionPane.showInputDialog(this,
                "Enter Student ID:");

        boolean found = false;

        for (int i = 0; i < studentCount; i++) {

            if (studentIDs[i].equalsIgnoreCase(searchID)) {

                outputArea.setText("STUDENT FOUND\n\n");
                outputArea.append("ID: " + studentIDs[i] + "\n");
                outputArea.append("Name: " + studentNames[i] + "\n");
                outputArea.append("Average: " + df.format(averages[i]) + "\n");
                outputArea.append("Grade: " + grades[i] + "\n");

                found = true;
            }
        }

        if (!found) {
            outputArea.setText("Student not found.\n");
        }
    }

    public void displaySummary() {

        if (studentCount == 0) {
            outputArea.setText("No students available.\n");
            return;
        }

        double highest = averages[0];
        double lowest = averages[0];
        double totalAverage = 0;

        int passCount = 0;
        int failCount = 0;

        for (int i = 0; i < studentCount; i++) {

            totalAverage += averages[i];

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

        double classAverage = totalAverage / studentCount;

        outputArea.setText("CLASS SUMMARY\n\n");
        outputArea.append("Class Average: " + df.format(classAverage) + "\n");
        outputArea.append("Highest Average: " + df.format(highest) + "\n");
        outputArea.append("Lowest Average: " + df.format(lowest) + "\n");
        outputArea.append("Passed Students: " + passCount + "\n");
        outputArea.append("Failed Students: " + failCount + "\n");
    }

    public void findTopStudent() {

        if (studentCount == 0) {
            outputArea.setText("No students available.\n");
            return;
        }

        int topIndex = 0;

        for (int i = 1; i < studentCount; i++) {

            if (averages[i] > averages[topIndex]) {
                topIndex = i;
            }
        }

        outputArea.setText("TOP STUDENT\n\n");
        outputArea.append("ID: " + studentIDs[topIndex] + "\n");
        outputArea.append("Name: " + studentNames[topIndex] + "\n");
        outputArea.append("Average: " + df.format(averages[topIndex]) + "\n");
    }

    public void clearFields() {

        idField.setText("");
        nameField.setText("");
        subject1Field.setText("");
        subject2Field.setText("");
        subject3Field.setText("");
    }

    public static void main(String[] args) {

        new StudentGradeGUI();
    }
}