package CapyTecSolutions;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBController {

    private final DBConnection database;

    public DBController() {
        database = new DBConnection();
        database.Connect("src\\CapyTecSolutions\\CapyTecSolutions.db");
    }

    public void validateCaretaker(String username, String password) {
        String sqlString = "SELECT Username, Password FROM Caretaker";
        ResultSet caretakerLoginList = database.runSQLQuery(sqlString);
        boolean success = false;

        try {
            while (caretakerLoginList.next()) {
                if ((caretakerLoginList.getString(1).equals(username)) && (caretakerLoginList.getString(2).equals(password))) {
                    success = true;
                    CapyTecSolutionsController loginSuccess = new CapyTecSolutionsController();
                    loginSuccess.loadCaretakerMainMenu();
                }
            }
        } catch (Exception ex) {
            System.out.println("Error occured " + ex.getMessage());
        }

        if (!success) {
            JOptionPane.showMessageDialog(null, "Login Failed. Check Username/Password and try again");
        }
    }

    public void validateAdmin(String username, String password) {
        String sqlString = "SELECT Username , Password FROM Administrator";
        ResultSet administratorLoginList = database.runSQLQuery(sqlString);
        boolean success = false;

        try {
            while (administratorLoginList.next()) {
                if ((administratorLoginList.getString(1).equals(username)) && (administratorLoginList.getString(2).equals(username))) {
                    success = true;
                    CapyTecSolutionsController loginSuccess = new CapyTecSolutionsController();
                    loginSuccess.loadAdministratorMainMenu();
                }
            }
        } catch (Exception ex) {
            System.out.println("Error occured " + ex.getMessage());
        }

        if (!success) {
            JOptionPane.showMessageDialog(null, "Login Failed. Check Username/Password and try again");
        }
    }

    public void AddTask(Task task) {
        String sqlString = ("INSERT INTO Task (Title,Location,Priority,Description,Frequency,SubmittedBy,TimeRequired,Completed)  VALUES('");
        sqlString = sqlString + task.getTitle() + "','";
        sqlString = sqlString + task.getLocation() + "','";
        sqlString = sqlString + task.getPriority() + "','";
        sqlString = sqlString + task.getDescription() + "','";
        sqlString = sqlString + task.getFrequency() + "','";
        sqlString = sqlString + task.getSubmittedBy() + "','";
        sqlString = sqlString + task.getTimeRequired() + "','";
        sqlString = sqlString + task.getCompleted() + "');";

        boolean success = database.runSQL(sqlString);

        if (!success) {
            JOptionPane.showMessageDialog(null, "This task could not be added. Option 1: the task already exists. Option 2: you missed some information");
            System.out.println("Failed to run query: " + sqlString);
        }

    }

    public void editTask(int taskID, Task task) {
        String sqlString = ("UPDATE Task SET ");
        sqlString = sqlString + "Title = '" + task.getTitle() + "',";
        sqlString = sqlString + "Location = '" + task.getLocation() + "',";
        sqlString = sqlString + "Priority = '" + task.getPriority() + "',";
        sqlString = sqlString + "Description = '" + task.getDescription() + "',";
        sqlString = sqlString + "Frequency = '" + task.getFrequency() + "',";
        sqlString = sqlString + "SubmittedBy = '" + task.getSubmittedBy() + "',";
        sqlString = sqlString + "AssignedTo = '" + task.getCaretaker() + "',";
        sqlString = sqlString + "TimeRequired = '" + task.getTimeRequired() + "',";
        sqlString = sqlString + "Completed = '" + task.getCompleted() + "' WHERE TaskID=" + taskID + ";";


        boolean success = database.runSQL(sqlString);

        if (!success) {
            JOptionPane.showMessageDialog(null, "This task could not be edited.");
            System.out.println("Failed to run query: " + sqlString);
        }
    }

    public void assignTask(int taskID, Task task) {
        String sqlString = ("UPDATE Task SET AssignedTo= '");
        sqlString = sqlString + task.getCaretaker() + "' WHERE taskID=" + taskID + " ;";

        boolean success = database.runSQL(sqlString);

        if (!success) {
            System.out.println("This task could not be assigned" + sqlString);
        }

    }

    public ArrayList<Task> getAllTasks() {
        String sqlString = "SELECT * FROM Task;";
        ResultSet taskList = database.runSQLQuery(sqlString);
        ArrayList<Task> answer = new ArrayList<>();

        try {
            while (taskList.next()) {
                Task newTask = new Task();
                newTask.setTaskId(taskList.getInt(1));
                newTask.setTitle(taskList.getString(2));
                newTask.setLocation(taskList.getString(3));
                newTask.setPriority(taskList.getString(4));
                newTask.setDescription(taskList.getString(5));
                newTask.setFrequency(taskList.getString(6));
                newTask.setSubmittedBy(taskList.getString(7));
                newTask.setCaretaker(taskList.getString(8));
                newTask.setTimeRequired(taskList.getInt(9));
                newTask.setCompleted(taskList.getInt(10));
                answer.add(newTask);
            }
        } catch (SQLException e) {
            System.out.println("Failed to process");
            System.out.println(e.getMessage());
        }
        return answer;
    }

    public ArrayList<Caretaker> getCaretakers() {
        String sqlString = "Select * FROM Caretaker;";
        ResultSet caretakerList = database.runSQLQuery(sqlString);
        ArrayList<Caretaker> caretakers = new ArrayList<>();

        try {
            while (caretakerList.next()) {
                Caretaker newCaretaker = new Caretaker();
                newCaretaker.setCaretakerID(caretakerList.getInt(1));
                newCaretaker.setCaretakerFName(caretakerList.getString(2));
                newCaretaker.setCaretakerSName(caretakerList.getString(3));
                newCaretaker.setCaretakerUsername(caretakerList.getString(4));
                newCaretaker.setCaretakerPassword(caretakerList.getString(5));
                caretakers.add(newCaretaker);

            }
        } catch (SQLException e) {
            System.out.println("Failed to process");
            System.out.println(e.getMessage());
        }
        return caretakers;
    }

    public ArrayList<Administrator> getAdministrators() {
        String sqlString = "Select * FROM Administrator;";
        ResultSet administratorList = database.runSQLQuery(sqlString);
        ArrayList<Administrator> administrators = new ArrayList<>();

        try {
            while (administratorList.next()) {
                Administrator newAdministrator = new Administrator();
                newAdministrator.setAdministratorID(administratorList.getInt(1));
                newAdministrator.setAdministratorFName(administratorList.getString(2));
                newAdministrator.setAdministratorSName(administratorList.getString(3));
                newAdministrator.setAdministratorUsername(administratorList.getString(4));
                newAdministrator.setAdministratorPassword(administratorList.getString(5));
                administrators.add(newAdministrator);
            }
        } catch (SQLException e) {
            System.out.println("Failed to process");
            System.out.println(e.getMessage());
        }
        return administrators;
    }
}
