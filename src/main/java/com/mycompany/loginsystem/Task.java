package com.mycompany.loginsystem;  // Ensure the package name matches the LoginSystem class

import java.util.List;
import java.util.*;

import javax.swing.*;

class Task {
   private String taskName;
    private int taskNumber;
    private String taskDescription;
    private String developerDetails;
    private int taskDuration; // in hours
    private String taskStatus;

    public Task(int taskNumber, String developerDetails, String taskName, String taskDescription, int taskduration, String taskstatus) {
        this.taskNumber = taskNumber;
        this.developerDetails = developerDetails;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskDuration = taskduration;
        this.taskStatus = taskstatus;
    }

   public boolean checkTaskDescription(String description) {
    if (description.length() <= 50) {
        this.taskDescription = description; // Set the task description
        JOptionPane.showMessageDialog(null, "Task successfully captured");
        return true; // Indicate success
    } else {
        JOptionPane.showMessageDialog(null, " Please enter a  description of less than 50 characters ");
        return false; // Indicate failure
    }
}


    public String createTaskID() {
     // Get the first two letters of the task name
   String letters;
if (taskName.length() >= 2) {
    letters = taskName.substring(0, 2).toUpperCase();
} else {
    letters = taskName.toUpperCase();
}

    // Get the task number as a string
    String taskNum = Integer.toString(taskNumber);
    
    // Get the last three letters of the developer details
String threeLetters;
if (developerDetails.length() < 3) {
    threeLetters = developerDetails.toUpperCase();
} else {
    threeLetters = developerDetails.substring(developerDetails.length() - 3).toUpperCase();
}

       // Combine everything into the Task ID
    return letters + ":" + taskNum + ":" + threeLetters;
    }

   public String printTaskDetails() {
    String details = "Task Number: " + taskNumber + "\n" +
                     "Task Name: " + taskName + "\n" +
                     "Developer: " + developerDetails + "\n" +
                     "Description: " + taskDescription + "\n" +
                     "Duration: " + taskDuration + " hrs\n" +
                     "Status: " + taskStatus + "\n" +
                     "Task ID: " + createTaskID();
    return details;
}

 public static int returnTotalHours(List<Task> tasks) {
        int totalHours = 0;
        for (Task task : tasks) {
            totalHours += task.taskDuration; // Directly accessing the field
        }
        return totalHours;
    
}
}
