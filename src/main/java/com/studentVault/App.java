package com.studentVault;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("Project started...");

        StudentDao studentDAO = new StudentDao();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            try {
                System.out.println("1. Create Student");
                System.out.println("2. Read Student");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter Student ID: ");
                        int createId = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        System.out.print("Enter Student Name: ");
                        String createName = scanner.nextLine();
                        System.out.print("Enter Student City: ");
                        String createCity = scanner.nextLine();
                        Student createStudent = new Student(createId, createName, createCity);
                        studentDAO.createStudent(createStudent);
                        System.out.println("Student created: " + createStudent);
                        break;

                    case 2:
                        System.out.print("Enter Student ID to read: ");
                        int readId = scanner.nextInt();
                        Student readStudent = studentDAO.readStudent(readId);
                        if (readStudent != null) {
                            System.out.println("Student found: " + readStudent);
                        } else {
                            System.out.println("Student not found with ID: " + readId);
                        }
                        break;

                    case 3:
                        System.out.print("Enter Student ID to update: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        Student updateStudent = studentDAO.readStudent(updateId);
                        if (updateStudent != null) {
                            System.out.print("Enter new Student Name: ");
                            String updateName = scanner.nextLine();
                            System.out.print("Enter new Student City: ");
                            String updateCity = scanner.nextLine();
                            updateStudent.setName(updateName);
                            updateStudent.setCity(updateCity);
                            studentDAO.updateStudent(updateStudent);
                            System.out.println("Student updated: " + updateStudent);
                        } else {
                            System.out.println("Student not found with ID: " + updateId);
                        }
                        break;

                    case 4:
                        System.out.print("Enter Student ID to delete: ");
                        int deleteId = scanner.nextInt();
                        studentDAO.deleteStudent(deleteId);
                        System.out.println("Student deleted with ID: " + deleteId);
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("An error occurred. Please try again.");
                scanner.nextLine(); // consume the remaining input
            }
        } while (choice != 5);

        studentDAO.closeFactory();
        scanner.close();
    }
}
