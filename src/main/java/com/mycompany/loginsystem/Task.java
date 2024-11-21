package com.mycompany.loginsystem;  // Ensure the package name matches the LoginSystem class

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.util.ArrayList;


/**
 * The Task class represents a task in the task management system.
 * It holds information about the task, such as name, developer, description, duration, and status.
 * It also provides methods to manage tasks (add, remove, search, etc.).
 */
public class Task {
   
    
    // Static arrays to manage tasks globally
    private static ArrayList<String> developerNames = new ArrayList<>();
    private static ArrayList<String> taskNames = new ArrayList<>();
    private static ArrayList<String> taskIDs = new ArrayList<>();
    private static ArrayList<Integer> taskDurations = new ArrayList<>();
    private static ArrayList<String> taskStatuses = new ArrayList<>();

    
     // Instance variables for a task
    private String taskName;
    private int taskNumber;
    private String taskDescription;
    private String developerDetails;
    private int taskDuration; // in hours
    private String taskStatus;
    
    // Getter for developerNames
    public static List<String> getDevelopers() {
        return developerNames;
    }
    
     /**
     * Constructor for creating a new Task.
     * 
     * taskNumber The unique number for the task.
     * developerDetails The developer working on the task.
     * taskName The name of the task.
     * taskDescription The description of the task.
     * taskDuration The duration of the task in hours.
     * taskStatus The current status of the task.
     */ 
    public Task(int taskNumber, String developerDetails, String taskName, String taskDescription, int taskduration, String taskstatus) {      
        this.developerDetails = developerDetails;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskDuration = taskduration;
        this.taskStatus = taskstatus;
        
        // Add task details to static arrays
        taskNames.add(taskName);
        developerNames.add(developerDetails);
        taskDurations.add(taskDuration);
        taskStatuses.add(taskStatus);

        // Create a unique task ID
        String taskID = "T" + (taskIDs.size() + 1); // Incremental task ID
        taskIDs.add(taskID);
    }
     /**
     * Validates the task description (should be 50 characters or less).
     * 
     * @param description The task description.
     * @return true if the description is valid, false otherwise.
     */
     public boolean checkTaskDescription(String description) {
        if (description.length() <= 50) {
            this.taskDescription = description;
            JOptionPane.showMessageDialog(null, "Task successfully captured");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Please enter a description of less than 50 characters.");
            return false;
        }
    }
/**
     * Creates a unique task ID based on the task name, task number, and developer's details.
     * 
     * @return A string representing the task ID.
     */
    public String createTaskID() {
        String letters = taskName.length() >= 2 ? taskName.substring(0, 2).toUpperCase() : taskName.toUpperCase();
        String taskNum = Integer.toString(taskNumber);
        String threeLetters = developerDetails.length() < 3 ? developerDetails.toUpperCase() : developerDetails.substring(developerDetails.length() - 3).toUpperCase();
        return letters + ":" + taskNum + ":" + threeLetters;
    }
 /**
     * Prints the details of the task.
     * 
     * @return A string containing all the task details.
     */
    public String printTaskDetails() {
        return "Task Number: " + taskNumber + "\n" +
               "Task Name: " + taskName + "\n" +
               "Developer: " + developerDetails + "\n" +
               "Description: " + taskDescription + "\n" +
               "Duration: " + taskDuration + " hrs\n" +
               "Status: " + taskStatus + "\n" +
               "Task ID: " + createTaskID();
    }
    
    /**
     * Calculates the total duration of tasks in a list.
     * 
     * @param tasks The list of tasks.
     * @return The total duration of all tasks in hours.
     */
 public static int returnTotalHours(List<Task> tasks) {
        int totalHours = 0;// Initialize total hours counter to 0 
        for (Task task : tasks) {
            totalHours += task.taskDuration; //Total the hours for every task.
        }
        return totalHours;// Return the total duration
    
}
    
   
    
     /**
     * Adds a new task to the system.
     * 
     * @param taskName The name of the task.
     * @param developerDetails The developer assigned to the task.
     * @param taskDuration The duration of the task in hours.
     * @param taskStatus The current status of the task.*/
    public static void addTask(String taskName, String developerDetails, int taskDuration, String taskStatus) {
        taskNames.add(taskName);
        developerNames.add(developerDetails);
        taskDurations.add(taskDuration);
        taskStatuses.add(taskStatus);
        
        // Create a unique task ID
        String taskID = "T" + (taskIDs.size() + 1);// Incremental task ID
        taskIDs.add(taskID);
    }
 /**
     * Displays all tasks in the system.
     * 
     * @return A string containing the details of all tasks.
     */
    public static String showAllTasks() {
    if (taskNames.isEmpty()) {
        return "No tasks available.";
    } else {
        String taskDetails = "Tasks:\n";
        for (int i = 0; i < taskNames.size(); i++) {
            taskDetails += "ID: " + taskIDs.get(i) + "\n"
                        + "Name: " + taskNames.get(i) + "\n"
                        + "Developer: " + developerNames.get(i) + "\n"
                        + "Duration: " + taskDurations.get(i) + " hours\n"
                        + "Status: " + taskStatuses.get(i) + "\n\n";
        }
        return taskDetails;
    }
}


    /**
     * Finds and returns the longest task (based on duration).
     * 
     * @return The details of the longest task.
     */
    public static String getLongestTask() {
        if (taskDurations.isEmpty()) {
            return "No tasks available.";
        } else {
            int longestTaskIndex = 0;
            for (int i = 1; i < taskDurations.size(); i++) {
                if (taskDurations.get(i) > taskDurations.get(longestTaskIndex)) {
                    longestTaskIndex = i;
                }
            }

            // Return the longest task details
            return "Longest Task:\n" +
                    "ID: " + taskIDs.get(longestTaskIndex) + "\n" +
                    "Name: " + taskNames.get(longestTaskIndex) + "\n" +
                    "Developer: " + developerNames.get(longestTaskIndex) + "\n" +
                    "Duration: " + taskDurations.get(longestTaskIndex) + " hours\n" +
                    "Status: " + taskStatuses.get(longestTaskIndex);
        }
    }

 /**
     * Searches for a task by name and returns its details.
     * 
     * @param taskNameToSearch The task name to search for.
     * @return A string with the task details, or a message if the task is not found.
     */
    public static String searchTaskByName(String taskNameToSearch) {
        for (int i = 0; i < taskNames.size(); i++) {
            if (taskNames.get(i).equalsIgnoreCase(taskNameToSearch)) {
                return "Task found:\n" +
                        "ID: " + taskIDs.get(i) + "\n" +
                        "Name: " + taskNames.get(i) + "\n" +
                        "Developer: " + developerNames.get(i) + "\n" +
                        "Duration: " + taskDurations.get(i) + " hours\n" +
                        "Status: " + taskStatuses.get(i);
            }
        }
        return "Task not found.";
    }

    // Method to search tasks by developer name
   public static String searchTasksByDeveloper(String developerNameToSearch) {
    String developerTaskDetails = "Tasks for developer " + developerNameToSearch + ":\n";
    boolean developerFound = false;
    
    for (int i = 0; i < developerNames.size(); i++) {
        if (developerNames.get(i).equalsIgnoreCase(developerNameToSearch)) {
            developerTaskDetails += "ID: " + taskIDs.get(i) + "\n"
                                  + "Name: " + taskNames.get(i) + "\n"
                                  + "Duration: " + taskDurations.get(i) + " hours\n"
                                  + "Status: " + taskStatuses.get(i) + "\n\n";
            developerFound = true;
        }
    }
    
    // Using if-else instead of ternary operator
    if (developerFound) {
        return developerTaskDetails;
    } else {
        return "No tasks found for developer: " + developerNameToSearch;
    }
}


   /**
     * Deletes a task by its name.
     * 
     * @param taskNameToDelete The name of the task to delete.
     * @return A message indicating whether the task was successfully deleted or not.
     */
    public static String deleteTaskByName(String taskNameToDelete) {
        for (int i = 0; i < taskNames.size(); i++) {
            if (taskNames.get(i).equalsIgnoreCase(taskNameToDelete)) {
                taskNames.remove(i);
                developerNames.remove(i);
                taskDurations.remove(i);
                taskStatuses.remove(i);
                taskIDs.remove(i);
                return "Task deleted successfully.";
            }
        }
        return "Task not found.";
    }

    // Method to generate a task report
   public static String TaskReport() {
    if (taskNames.isEmpty()) {
        return "No tasks available.";
    } else {
        String report = "Task Report:\n";
        for (int i = 0; i < taskNames.size(); i++) {
            report += "ID: " + taskIDs.get(i) + "\n"
                    + "Name: " + taskNames.get(i) + "\n"
                    + "Developer: " + developerNames.get(i) + "\n"
                    + "Duration: " + taskDurations.get(i) + " hours\n"
                    + "Status: " + taskStatuses.get(i) + "\n\n";
        }
        return report;
    }
}

}
