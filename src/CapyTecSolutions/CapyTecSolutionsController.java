package CapyTecSolutions;

public class CapyTecSolutionsController {

    private DBController data;

    TaskAllocationGUI taskAllocationGUI;
    TaskEntryGUI taskEntryForm;

    public static void main(String[] args){
        CapyTecSolutionsController cts = new CapyTecSolutionsController();
    }

    public CapyTecSolutionsController() {
       // loginGUI = new LoginGUI(this);
       // loginGUI.setVisible(true);
    }


    // Load the Task Allocation GUI
    void loadTaskAllocation(){
        data = new DBController();
        taskAllocationGUI = new TaskAllocationGUI(this);
        taskAllocationGUI.setVisible(true);
        taskAllocationGUI.displayTableData(data.getAllTasks());
        taskAllocationGUI.displayComboboxData(data.getCaretakers());
    }

    void assignTask(int taskID, String caretakerName){

        Task assignedTask = new Task();
        assignedTask.setCaretaker(caretakerName);
        data.assignTask(taskID,assignedTask);
    }

    void refreshTable(){
        taskAllocationGUI.displayTableData(data.getAllTasks());
    }

    void editTask(int taskID,String title, String location, int timeRequired, String priority, String description, String frequency, String submittedBy, String assignedTo, int completed){
        Task newTask = new Task();
        newTask.setTitle(title);
        newTask.setLocation(location);
        newTask.setTimeRequired(timeRequired);
        newTask.setPriority(priority);
        newTask.setDescription(description);
        newTask.setFrequency(frequency);
        newTask.setSubmittedBy(submittedBy);
        newTask.setCaretaker(assignedTo);
        newTask.setCompleted(completed);
        data.editTask(taskID,newTask);
    }

    // Load task entry form
    void loadTaskEntry(){
        data = new DBController();
        taskEntryForm = new TaskEntryGUI();
        taskEntryForm.setVisible(true);
        taskEntryForm.displayComboboxData(data.getAdministrators());
    }
}
