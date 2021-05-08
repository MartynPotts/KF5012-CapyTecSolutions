package CapyTecSolutions;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

public class TaskAllocationGUI extends JFrame implements ActionListener {

    CapyTecSolutionsController theTaskAllocationHandler;

    private JTable tblTasks;

    private JComboBox<String> cbCaretaker;


    public TaskAllocationGUI() {

    }

    /**
     * Launch the application
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TaskAllocationGUI frame = new TaskAllocationGUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    /**
     * Constructor to initialise the components and create the form
     *
     * @param taskAllocationController
     */
    public TaskAllocationGUI(CapyTecSolutionsController taskAllocationController) {

        theTaskAllocationHandler = taskAllocationController;

        setTitle("Add Task");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(300, 90, 900, 600);
        setResizable(false);

        JPanel c = new JPanel();
        c.setLayout(new BorderLayout(0, 0));
        c.setBorder(new EmptyBorder(25, 15, 25, 15));
        setContentPane(c);

        JPanel pnlTasks = new JPanel();
        c.add(pnlTasks, BorderLayout.NORTH);
        pnlTasks.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));


        /* Title of GUI section */
        // Assign Task label
        JLabel lblTitle = new JLabel("View and Assign Task");
        lblTitle.setFont(new Font("Arial", Font.PLAIN, 30));
        pnlTasks.add(lblTitle);

        /* Table of tasks */
        // Scrollpane
        JScrollPane spTask = new JScrollPane();
        c.add(spTask, BorderLayout.CENTER);

        // Table
        tblTasks = new JTable();
        tblTasks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblTasks.setModel(new DefaultTableModel(
                new String[]{
                        "Task ID", "Task Name", "Location", "Priority", "Description", "Frequency", "Submitted By", "Assigned To", "Duration (Mins)"
                }, 0)
        );
        tblTasks.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblTasks.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblTasks.getColumnModel().getColumn(3).setPreferredWidth(50);
        tblTasks.getColumnModel().getColumn(4).setPreferredWidth(125);
        tblTasks.setAutoCreateRowSorter(true);
        spTask.setViewportView(tblTasks);

        /* Button Section */
        // Assign Panel
        JPanel pnlAssign = new JPanel();
        c.add(pnlAssign, BorderLayout.SOUTH);

        //Filter Section
        final TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblTasks.getModel());
        tblTasks.setRowSorter(sorter);

        JLabel lblFilter = new JLabel("Filter: ");
        lblFilter.setFont(new Font("Arial", Font.PLAIN, 15));
        lblFilter.setSize(200, 20);
        pnlAssign.add(lblFilter);

        JTextField tFilter = new JTextField("");
        tFilter.setColumns(10);
        pnlAssign.add(tFilter);

        JButton btnFilter = new JButton("Filter");
        btnFilter.setFont(new Font("Arial", Font.PLAIN, 15));
        btnFilter.setSize(200, 20);
        pnlAssign.add(btnFilter);
        btnFilter.addActionListener(e -> {
            String filterTxt = tFilter.getText();
            if (filterTxt.length() == 0) {
                sorter.setRowFilter(null);
            } else {
                try {
                    sorter.setRowFilter(RowFilter.regexFilter(filterTxt));
                } catch (PatternSyntaxException pse) {
                    JOptionPane.showMessageDialog(null, "This item can't be found");
                }
            }
        });

        JButton btnClearFilter = new JButton("Clear");
        btnClearFilter.setFont(new Font("Arial", Font.PLAIN, 15));
        btnClearFilter.setSize(200, 20);
        pnlAssign.add(btnClearFilter);
        btnClearFilter.addActionListener(e -> {
            sorter.setRowFilter(null);
            tFilter.setText("");
        });


        // Refresh Button
        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.setFont(new Font("Arial", Font.PLAIN, 15));
        btnRefresh.setSize(200, 20);
        btnRefresh.addActionListener(e -> {
            theTaskAllocationHandler.refreshTable();
        });
        pnlAssign.add(btnRefresh);

        // View Details
        JButton btnEditDetails = new JButton("Edit Details");
        btnEditDetails.setFont(new Font("Arial", Font.PLAIN, 15));
        btnEditDetails.setSize(200, 20);
        btnEditDetails.addActionListener(e -> {
            int rowToView = tblTasks.getSelectedRow();

            if (rowToView >= 0) {
                EditTaskDetailsDialog dialog = new EditTaskDetailsDialog();
                dialog.lblTaskID.setText("Task ID: " +  tblTasks.getValueAt(rowToView, 0));
                dialog.lblTaskName.setText("Task Name: ");
                dialog.tTaskName.setText((String) tblTasks.getValueAt(rowToView, 1));
                dialog.lblLocation.setText("Location: ");
                dialog.tLocation.setText((String) tblTasks.getValueAt(rowToView, 2));
                dialog.lblPriority.setText("Priority: ");
                dialog.tPriority.setText((String) tblTasks.getValueAt(rowToView, 3));
                dialog.lblDescription.setText("Description: ");
                dialog.tDescription.setText((String) tblTasks.getValueAt(rowToView, 4));
                dialog.lblFrequency.setText("Frequency: ");
                dialog.tFrequency.setText((String) tblTasks.getValueAt(rowToView, 5));
                dialog.lblCreator.setText("Submitted By: " + tblTasks.getValueAt(rowToView, 6));
                if (tblTasks.getValueAt(rowToView, 7) == null) {
                    dialog.lblAssignedTo.setText("Assigned To: No one");
                } else {
                    dialog.lblAssignedTo.setText("Assigned To: ");
                    dialog.tAssignedTo.setText((String) tblTasks.getValueAt(rowToView, 7));
                }
                dialog.lblTimeRequired.setText("Duration (mins): ");
                dialog.tTimeRequired.setText(String.valueOf(tblTasks.getValueAt(rowToView, 8)));
                dialog.setVisible(true);

                int taskId = (Integer) tblTasks.getValueAt(rowToView, 0);
                String title;
                String location;
                int timeRequired;
                String priority;
                String description;
                String frequency;
                String submittedBy = (String) tblTasks.getValueAt(rowToView, 6);
                String caretaker;

                try {
                    title = dialog.tTaskName.getText();
                    location = dialog.tLocation.getText();
                    timeRequired = Integer.parseInt(dialog.tTimeRequired.getText());
                    priority = dialog.tPriority.getText();
                    description = dialog.tDescription.getText();
                    frequency = dialog.tFrequency.getText();
                    caretaker = dialog.tAssignedTo.getText();

                } catch (Exception ex) {
                    title = (String) tblTasks.getValueAt(rowToView, 1);
                    location = (String) tblTasks.getValueAt(rowToView, 2);
                    priority = (String) tblTasks.getValueAt(rowToView, 3);
                    description = (String) tblTasks.getValueAt(rowToView, 4);
                    frequency = (String) tblTasks.getValueAt(rowToView, 5);
                    submittedBy = (String) tblTasks.getValueAt(rowToView, 6);
                    caretaker = (String) tblTasks.getValueAt(rowToView, 7);
                    timeRequired = (Integer) tblTasks.getValueAt(rowToView, 8);

                }
                if (!title.isEmpty()) {
                    taskAllocationController.editTask(taskId, title, location, timeRequired, priority, description, frequency, submittedBy, caretaker, 0);
                }
                theTaskAllocationHandler.refreshTable();

            }
        });
        pnlAssign.add(btnEditDetails);

        // Assign Combobox
        cbCaretaker = new JComboBox<>();
        pnlAssign.add(cbCaretaker);

        // Add Task Button
        JButton btnAssign = new JButton("Assign Task");
        btnAssign.setFont(new Font("Arial", Font.PLAIN, 15));
        btnAssign.setSize(150, 20);
        btnAssign.addActionListener(e -> {

            try {
                String selectedCaretaker = (String) cbCaretaker.getSelectedItem();
                System.out.println(selectedCaretaker);
                int rowToAssign = tblTasks.getSelectedRow();
                int selectedTask = (Integer) tblTasks.getValueAt(rowToAssign, 0);


                if (selectedCaretaker != null && selectedTask >= 0) {

                    theTaskAllocationHandler.assignTask(selectedTask, selectedCaretaker);
                    System.out.println(theTaskAllocationHandler);
                }

                JOptionPane.showMessageDialog(null, "Task Assigned");

                theTaskAllocationHandler.refreshTable();
            } catch (Exception ex) {
                ex.printStackTrace();

            }
        });
        pnlAssign.add(btnAssign);

        JButton btnBack = new JButton("Back");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


    void displayTableData(ArrayList<Task> taskArrayList) {

        DefaultTableModel mdl = (DefaultTableModel) tblTasks.getModel();
        mdl.setRowCount(0);

        for (Task t : taskArrayList) {
            mdl.addRow(new Object[]{t.getTaskId(), t.getTitle(), t.getLocation(), t.getPriority(), t.getDescription(),
                    t.getFrequency(), t.getSubmittedBy(), t.getCaretaker(), t.getTimeRequired()});
        }
    }

    void displayComboboxData(ArrayList<Caretaker> caretakerArrayList) {

        DefaultComboBoxModel<String> mdl = (DefaultComboBoxModel<String>) cbCaretaker.getModel();
        mdl.setSelectedItem(null);

        for (Caretaker c : caretakerArrayList) {
            String s = c.getCaretakerFName() + " " + c.getCaretakerSName();
            cbCaretaker.addItem(s);
        }
    }
}
