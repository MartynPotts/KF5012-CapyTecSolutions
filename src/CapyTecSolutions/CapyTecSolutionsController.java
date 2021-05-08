package CapyTecSolutions;

public class CapyTecSolutionsController {

    private DBController data;

    LoginGUI loginGUI;
    AdministratorMainMenu administratorMainMenu;
    CaretakerMainMenu caretakerMainMenu;
    TaskAllocationGUI taskAllocationGUI;
    TaskEntryGUI taskEntryGUI;

    public static void main(String[] args) {
        CapyTecSolutionsController cts = new CapyTecSolutionsController();
    }

    public CapyTecSolutionsController() {
        data = new DBController();
        loginGUI = new LoginGUI(this);
        loginGUI.setVisible(true);
    }


    /*  Login Section */
    // Caretaker login
    public void loginCaretaker(String username, String password) {
        data.validateCaretaker(username, password);
    }

    // Admin Login
    public void loginAdmin(String username, String password) {
        data.validateAdmin(username, password);
    }

    // Logout
    public void logout() {
        loginGUI = new LoginGUI();
        loginGUI.setVisible(true);
    }

    /* Load Main Menu */
    // Load caretaker menu
    public void loadCaretakerMainMenu() {
        caretakerMainMenu = new CaretakerMainMenu(this);
        caretakerMainMenu.setVisible(true);
        System.out.println("Load Caretaker menu");
        loginGUI.setVisible(false);
    }

    // Load Admin menu
    public void loadAdministratorMainMenu() {
        administratorMainMenu = new AdministratorMainMenu(this);
        administratorMainMenu.setVisible(true);
        System.out.println("Load Admin menu");
        loginGUI.setVisible(false);
    }

    /* Task Allocation */
    // Load the Task Allocation GUI
    void loadTaskAllocation() {
        taskAllocationGUI = new TaskAllocationGUI(this);
        taskAllocationGUI.setVisible(true);
        taskAllocationGUI.displayTableData(data.getAllTasks());
        taskAllocationGUI.displayComboboxData(data.getCaretakers());
        loginGUI.setVisible(false);
    }

    void assignTask(int taskID, String caretakerName) {
        Task assignedTask = new Task();
        assignedTask.setCaretaker(caretakerName);
        data.assignTask(taskID, assignedTask);
    }

    void refreshTable() {
        taskAllocationGUI.displayTableData(data.getAllTasks());
    }

    void editTask(int taskID, String title, String location, int timeRequired, String priority, String description, String frequency, String submittedBy, String assignedTo, int completed) {
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
        data.editTask(taskID, newTask);
    }

    /* Task Entry */
    // Load task entry form
    void loadTaskEntry() {
        taskEntryGUI = new TaskEntryGUI(this);
        taskEntryGUI.setVisible(true);
        loginGUI.setVisible(false);
        taskEntryGUI.displayComboboxData(data.getAdministrators());
        loginGUI.setVisible(false);
    }

    void addTask(String title, String location, int timeRequired, String priority, String description, String frequency, String submittedBy, int completed) {
        /* create Task - Task newTask = new Task();
         *  set the details for the task - newTask.setTitle, newTask.setLocation etc...
         *  add to the database -  insert newTask into task list
         */
        Task newTask = new Task();
        newTask.setTitle(title);
        newTask.setLocation(location);
        newTask.setTimeRequired(timeRequired);
        newTask.setPriority(priority);
        newTask.setDescription(description);
        newTask.setFrequency(frequency);
        newTask.setSubmittedBy(submittedBy);
        newTask.setCompleted(completed);

        data.AddTask(newTask);
    }
}
