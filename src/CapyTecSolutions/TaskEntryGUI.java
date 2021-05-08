package CapyTecSolutions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TaskEntryGUI extends JFrame implements ActionListener {

    CapyTecSolutionsController theTaskEntryHandler;

    // Variables which will act as the components of form
    private Container c;

    private JLabel lblTitle;
    private JLabel lblTaskName;
    private JLabel lblLocation;
    private JLabel lblPriority;
    private JLabel lblTimeRequired;
    private JLabel lblDescription;
    private JLabel lblFrequency;
    private JLabel lblCreator;

    private JTextField tTaskName;
    private JTextField tLocation;
    private JTextField tPriority;
    private JTextField tTimeRequired;
    private JTextField tFrequency;
    private JTextField tDescription;

    private JComboBox cbCreator;

    private JButton btnAdd;

    public TaskEntryGUI() {

    }


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TaskEntryGUI frame = new TaskEntryGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public TaskEntryGUI(CapyTecSolutionsController taskEntryController) {

        theTaskEntryHandler = taskEntryController;

        setTitle("Add Task");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 90, 900, 600);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        /* Title of form section */
        // Add Task label
        lblTitle = new JLabel("Add Task");
        lblTitle.setFont(new Font("Arial", Font.PLAIN, 30));
        lblTitle.setSize(300, 30);
        lblTitle.setLocation(300, 30);
        c.add(lblTitle);


        /* First Line of Form */
        /* Task Name Section */
        // Task Name label
        lblTaskName = new JLabel("Task Title: ");
        lblTaskName.setFont(new Font("Arial", Font.PLAIN, 20));
        lblTaskName.setSize(150, 20);
        lblTaskName.setLocation(50, 100);
        c.add(lblTaskName);

        // Task name text field
        tTaskName = new JTextField();
        tTaskName.setFont(new Font("Arial", Font.PLAIN, 15));
        tTaskName.setSize(200, 20);
        tTaskName.setLocation(250, 100);
        c.add(tTaskName);

        /* Location Section */
        // Location label
        lblLocation = new JLabel("Location: ");
        lblLocation.setFont(new Font("Arial", Font.PLAIN, 20));
        lblLocation.setSize(150, 20);
        lblLocation.setLocation(475, 100);
        c.add(lblLocation);

        // Location Text field
        tLocation = new JTextField();
        tLocation.setFont(new Font("Arial", Font.PLAIN, 15));
        tLocation.setSize(200, 20);
        tLocation.setLocation(565, 100);
        c.add(tLocation);


        /* Second Line of Form */
        /* Priority Section */
        // Priority label
        lblPriority = new JLabel("Priority: ");
        lblPriority.setFont(new Font("Arial", Font.PLAIN, 20));
        lblPriority.setSize(150, 20);
        lblPriority.setLocation(475, 150);
        c.add(lblPriority);

        // Priority Text field
        tPriority = new JTextField();
        tPriority.setFont(new Font("Arial", Font.PLAIN, 15));
        tPriority.setSize(200, 20);
        tPriority.setLocation(565, 150);
        c.add(tPriority);

        /* Time Required Section */
        // Time Required Label
        lblTimeRequired = new JLabel("Time Required (Mins): ");
        lblTimeRequired.setFont(new Font("Arial", Font.PLAIN, 20));
        lblTimeRequired.setSize(225, 20);
        lblTimeRequired.setLocation(50, 150);
        c.add(lblTimeRequired);

        // Time Required Text field
        tTimeRequired = new JTextField();
        tTimeRequired.setFont(new Font("Arial", Font.PLAIN, 15));
        tTimeRequired.setSize(200, 20);
        tTimeRequired.setLocation(250, 150);
        c.add(tTimeRequired);

        /* Third Line of Form */
        /* Frequency Section */
        // Frequency label
        lblFrequency = new JLabel("Frequency: ");
        lblFrequency.setFont(new Font("Arial", Font.PLAIN, 20));
        lblFrequency.setSize(150, 20);
        lblFrequency.setLocation(50, 200);
        c.add(lblFrequency);

        // Time Required text field
        tFrequency = new JTextField();
        tFrequency.setFont(new Font("Arial", Font.PLAIN, 15));
        tFrequency.setSize(200, 20);
        tFrequency.setLocation(250, 200);
        c.add(tFrequency);

        /* creator  Section */
        // Created By label
        lblCreator = new JLabel("Creator: ");
        lblCreator.setFont(new Font("Arial", Font.PLAIN, 20));
        lblCreator.setSize(150, 20);
        lblCreator.setLocation(475, 200);
        c.add(lblCreator);

        // creator text field
        cbCreator = new JComboBox();
        cbCreator.setFont(new Font("Arial", Font.PLAIN, 15));
        cbCreator.setSize(200, 20);
        cbCreator.setLocation(565, 200);
        c.add(cbCreator);

        /* Forth Line of form */
        /* Task Description Section */
        // Task Description label
        lblDescription = new JLabel("Description:");
        lblDescription.setFont(new Font("Arial", Font.PLAIN, 20));
        lblDescription.setSize(150, 20);
        lblDescription.setLocation(50, 250);
        c.add(lblDescription);

        // Task Description textarea
        tDescription = new JTextField();
        tDescription.setFont(new Font("Arial", Font.PLAIN, 15));
        tDescription.setSize(515, 20);
        tDescription.setLocation(250, 250);
        c.add(tDescription);

        /* Final Line of Form */
        /* Button Section */
        // Add Task Button
        btnAdd = new JButton("Add Task");
        btnAdd.setFont(new Font("Arial", Font.PLAIN, 15));
        btnAdd.setSize(100, 20);
        btnAdd.setLocation(350, 300);
        btnAdd.addActionListener(e -> {
            String newTaskTitle = tTaskName.getText();
            String newTaskLocation = tLocation.getText();
            String newTaskTimeRequiredString = tTimeRequired.getText();
            int newTaskTimeRequired = Integer.parseInt(newTaskTimeRequiredString);
            String newTaskPriority = tPriority.getText();
            String newTaskFrequency = tFrequency.getText();
            String newTaskCreator = (String) cbCreator.getSelectedItem();
            String newTaskDescription = tDescription.getText();
            int completed = 0;

            if ((!newTaskTitle.isEmpty()) && (!newTaskLocation.isEmpty()) && (!newTaskPriority.isEmpty()) &&
                    (!newTaskFrequency.isEmpty()) && (!newTaskDescription.isEmpty())) {

                theTaskEntryHandler.addTask(newTaskTitle, newTaskLocation, newTaskTimeRequired, newTaskPriority, newTaskDescription
                        , newTaskFrequency, newTaskCreator, completed);
            }

            JOptionPane.showMessageDialog(null, "Task has been added.");

            tTaskName.setText("");
            tLocation.setText("");
            tTimeRequired.setText("");
            tTimeRequired.setText("");
            tPriority.setText("");
            tFrequency.setText("");
            tDescription.setText("");
        });

        c.add(btnAdd);
        btnAdd = new JButton("Clear");
        btnAdd.setFont(new Font("Arial", Font.PLAIN, 15));
        btnAdd.setSize(100, 20);
        btnAdd.setLocation(475, 300);
        btnAdd.addActionListener(e -> {

            JOptionPane.showMessageDialog(null, "Form Cleared.");
            tTaskName.setText("");
            tLocation.setText("");
            tTimeRequired.setText("");
            tTimeRequired.setText("");
            tPriority.setText("");
            tFrequency.setText("");
            tDescription.setText("");
        });
        c.add(btnAdd);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    void displayComboboxData(ArrayList<Administrator> administratorArrayList) {
        DefaultComboBoxModel<String> mdl = (DefaultComboBoxModel<String>) cbCreator.getModel();
        mdl.setSelectedItem(null);

        for (Administrator a : administratorArrayList) {
            String s = a.getAdministratorFName() + " " + a.getAdministratorSName();
            cbCreator.addItem(s);
        }
    }
}