package CapyTecSolutions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditTaskDetailsDialog extends JDialog {

    CapyTecSolutionsController editTaskDetailsController;

    private final JPanel pnlContent = new JPanel();

    public JPanel pnlButtons;

    public JLabel lblTaskID;
    public JLabel lblTaskName;
    public JLabel lblLocation;
    public JLabel lblPriority;
    public JLabel lblTimeRequired;
    public JLabel lblDescription;
    public JLabel lblFrequency;
    public JLabel lblCreator;
    public JLabel lblAssignedTo;

    public JTextField tTaskName;
    public JTextField tLocation;
    public JTextField tPriority;
    public JTextField tTimeRequired;
    public JTextField tFrequency;
    public JTextField tDescription;
    public JTextField tAssignedTo;

    public JButton btnUpdate;

    public static void main(String[] args) {
        try {
            EditTaskDetailsDialog dialog = new EditTaskDetailsDialog();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public EditTaskDetailsDialog() {
        setModal(true);
        setBounds(100, 100, 500, 500);
        getContentPane().setLayout(new BorderLayout());
        setTitle("View/Edit Task Details");
        getContentPane().add(pnlContent, BorderLayout.CENTER);

        lblTaskID = new JLabel("Task ID: ");
        pnlContent.add(lblTaskID);

        lblTaskName = new JLabel("Task Name: ");
        lblTaskName.setLocation(100, 50);
        pnlContent.add(lblTaskName);

        tTaskName = new JTextField();
        tTaskName.setLocation(150, 50);
        pnlContent.add(tTaskName);

        lblLocation = new JLabel("Location: ");
        lblLocation.setLocation(100, 100);
        pnlContent.add(lblLocation);

        tLocation = new JTextField();
        tLocation.setLocation(150, 100);
        pnlContent.add(tLocation);

        lblPriority = new JLabel("Priority: ");
        lblPriority.setLocation(100, 150);
        pnlContent.add(lblPriority);

        tPriority = new JTextField();
        tPriority.setLocation(150, 150);
        pnlContent.add(tPriority);

        lblTimeRequired = new JLabel("Time Required (Mins): ");
        lblTimeRequired.setLocation(100, 200);
        pnlContent.add(lblTimeRequired);

        tTimeRequired = new JTextField();
        tTimeRequired.setLocation(150, 200);
        pnlContent.add(tTimeRequired);

        lblDescription = new JLabel("Description: ");
        lblDescription.setLocation(100, 250);
        pnlContent.add(lblDescription);

        tDescription = new JTextField();
        tDescription.setLocation(150, 250);
        pnlContent.add(tDescription);

        lblFrequency = new JLabel("Frequency: ");
        lblFrequency.setLocation(100, 300);
        pnlContent.add(lblFrequency);

        tFrequency = new JTextField();
        tFrequency.setLocation(150, 300);
        pnlContent.add(tFrequency);

        lblCreator = new JLabel("Submitted By: ");
        lblCreator.setLocation(100, 350);
        pnlContent.add(lblCreator);

        lblAssignedTo = new JLabel("Assigned To: ");
        lblAssignedTo.setLocation(100, 400);
        pnlContent.add(lblAssignedTo);

        tAssignedTo = new JTextField();
        tAssignedTo.setLocation(150, 400);
        pnlContent.add(tAssignedTo);

        pnlButtons = new JPanel();
        pnlButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(pnlButtons, BorderLayout.SOUTH);

        btnUpdate = new JButton("Update");
        btnUpdate.setActionCommand("Update");
        pnlButtons.add(btnUpdate);
        getRootPane().setDefaultButton(btnUpdate);
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setModal(false);
                dispose();
            }
        });
    }
}