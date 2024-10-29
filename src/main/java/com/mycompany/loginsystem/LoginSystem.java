/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.loginsystem;

import java.util.*;
import javax.swing.*;

public class LoginSystem {

    public static void main(String[] args) {
        // Login instance of the user
        Login user = new Login();
        String registrationMessage;

        // Create the object of the Scanner that reads the user's input
        Scanner sc = new Scanner(System.in);
        final JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);

        
        //Registration
        System.out.println(">>>>>>Registration<<<<<<");
        // Registration prompts the user to enter details
        System.out.println("Please enter your name");
        String firstName = sc.nextLine();
        user.setFirstName(firstName); // Sets first name

        System.out.println("Please enter your lastname");
        String lastName = sc.nextLine();
        user.setlastName(lastName); // Sets the lastname

        String tempUsername;
        do {
            System.out.println("Please enter your username. ");
            tempUsername = sc.nextLine(); // Read the username
            user.setUserName(tempUsername); // Set username
        } while (!user.CheckUsername(tempUsername)); // Checks the username

        String password;
        do {
            System.out.println("Please enter your password");
            password = sc.nextLine(); // Read the password
            user.setPassWord(password);
        } while (!user.checkPasswordComplexity(password));

        // Call the registerUser method to register the user
        registrationMessage = user.registerUser();
        System.out.println(registrationMessage); // Displays the registration message

        // Check if registration was successful
        if (!registrationMessage.equals("Username and password successfully captured")) {
            return; // Exit when registration failed
        }

        boolean loginSuccessful = true; // Initializing the login status
        // Loop for user to login until successful
        do {
            //login
             System.out.println(">>>>login<<<<<");
            // Prompt the user to log in with their username
            System.out.println("Please enter your username to log in:");
            String enteredUsername = sc.nextLine();

            // Prompt the user to log in with their password
            System.out.println("Please enter your password:");
            String enteredPassword = sc.nextLine();

            // Attempt to log in
            loginSuccessful = user.loginUser(enteredUsername, enteredPassword);
            // Output the login status
            System.out.println(user.returnLoginStatus(loginSuccessful));
        } while (!loginSuccessful);

       
        ArrayList<Task> taskList = new ArrayList<>();
        
        String taskName;
        String taskDescription;
        String developerDetails;
        int taskDuration;
        String taskStatus;
        int taskCounter = 1; // Track number of tasks

        JOptionPane.showMessageDialog(dialog, "Welcome to EasyKanban");
        boolean exit = false;

        while (!exit) {
            String choice = JOptionPane.showInputDialog(dialog, "Choose an option:\n1)"
                    + " Add task\n2)"
                    + " Show all tasks\n3) Quit");

            switch (choice) {
               case "1":
            int numTasks = Integer.parseInt(JOptionPane.showInputDialog(dialog, "How many tasks would you like to enter?"));

                    for (int i = 0; i < numTasks; i++) {
                        taskName = JOptionPane.showInputDialog(dialog, "Enter task name:");

                        
                        
                        while (true) {
                            taskDescription = JOptionPane.showInputDialog(dialog, "Enter task description (max 50 characters):");
                            if (taskDescription.length() <= 50) {
                                break; // Valid input
                            }
                            JOptionPane.showMessageDialog(dialog, "Please enter a task description of 50 characters or less.");
                        }
                        
                        

                        while (true) {
                            developerDetails = JOptionPane.showInputDialog(dialog, "Enter developer's full name (first and last name):");
                            if (developerDetails.trim().split(" ").length == 2) {
                                break; // Valid input
                            }
                            JOptionPane.showMessageDialog(dialog, "Please enter both first and last names.");
                        }

                         
            do {
                String input = JOptionPane.showInputDialog(dialog, "Enter duration in hours:");
                if (input == null) {
                    JOptionPane.showMessageDialog(dialog, "Invalid input. Please enter a valid number for duration.");
                    return; // Exit if canceled
                }
                if (input.matches("\\d+")) {
                    taskDuration = Integer.parseInt(input);
                    if (taskDuration <= 0) {
                        JOptionPane.showMessageDialog(dialog, "Please enter a positive number.");
                    } else {
                        break; // Valid inputt
                    }
                } 
            } while (true);
                            

                        // Create the task with a placeholder status (you'll set this later)
                        Task newTask = new Task(taskCounter++, developerDetails, taskName, taskDescription, taskDuration, ""); // Empty status
                        // Show successful capture message
                        JOptionPane.showMessageDialog(dialog, "Task successfully captured with ID: " + newTask.createTaskID());
                         // Ask for task status
                        taskStatus = JOptionPane.showInputDialog("Enter task status:\n1) To Do\n2) Done\n3) Doing");

                        // Add the task to the list
                        taskList.add(newTask);
                    }
                    break;

                case "2":
                   if (taskList.isEmpty()) {
    JOptionPane.showMessageDialog(null, "No tasks available.");
} else {
    String taskDetails = "Tasks:\n"; // Initialize with the chosen name
    for (Task task : taskList) {
        taskDetails += task.printTaskDetails() + "\n";
    }
    JOptionPane.showMessageDialog(null, taskDetails); // Show the dialog here
}

                    break;
                case "3":
                    int totalHours = Task.returnTotalHours(taskList);
                    JOptionPane.showMessageDialog(dialog, "Total hours: " + totalHours);
                    exit = true; // Exit the loop
                    break;

                default:
                    JOptionPane.showMessageDialog(dialog, "Invalid option, please try again.");
                    break;
            }
        }

        dialog.dispose(); // Dispose of the dialog when done
        sc.close(); // Close the scanner
    }
}
