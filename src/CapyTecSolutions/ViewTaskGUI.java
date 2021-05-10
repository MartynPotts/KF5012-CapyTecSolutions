package CapyTecSolutions;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

public class ViewTaskGUI extends JFrame {

    CapyTecSolutionsController theViewTaskHandler;

    private JTable tblTasks;

    public ViewTaskGUI() {

    }

    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            try {
                ViewTaskGUI frame = new ViewTaskGUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ViewTaskGUI(CapyTecSolutionsController viewTaskHandler){

        theViewTaskHandler = viewTaskHandler;

        setTitle("View Task");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(300,90,900,600);
        setResizable(false);

        JPanel c = new JPanel();
        c.setLayout(new BorderLayout(0,0));
        c.setBorder(new EmptyBorder(25,15,25,15));
        setContentPane(c);

        JPanel pnlTasks = new JPanel();
        c.add(pnlTasks,BorderLayout.NORTH);
        pnlTasks.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));

        JLabel lblTitle = new JLabel("View Tasks");
        lblTitle.setFont(new Font("Arial",Font.PLAIN,30));
        pnlTasks.add(lblTitle);

        JScrollPane spTask = new JScrollPane();
        c.add(spTask, BorderLayout.CENTER);

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

        JPanel pnlButtons = new JPanel();
        c.add(pnlButtons,BorderLayout.SOUTH);

        //Filter Section
        final TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblTasks.getModel());
        tblTasks.setRowSorter(sorter);

        JLabel lblFilter = new JLabel("Filter: ");
        lblFilter.setFont(new Font("Arial", Font.PLAIN, 15));
        lblFilter.setSize(200, 20);
        pnlButtons.add(lblFilter);

        JTextField tFilter = new JTextField("");
        tFilter.setColumns(10);
        pnlButtons.add(tFilter);

        JButton btnFilter = new JButton("Filter");
        btnFilter.setFont(new Font("Arial", Font.PLAIN, 15));
        btnFilter.setSize(200, 20);
        pnlButtons.add(btnFilter);
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
        pnlButtons.add(btnClearFilter);
        btnClearFilter.addActionListener(e -> {
            sorter.setRowFilter(null);
            tFilter.setText("");
        });

        // Refresh Button
        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.setFont(new Font("Arial", Font.PLAIN, 15));
        btnRefresh.setSize(200, 20);
        btnRefresh.addActionListener(e -> {
            theViewTaskHandler.refreshViewTaskTable();
        });
        pnlButtons.add(btnRefresh);

        JButton btnEditDetails = new JButton("Edit Details");
        btnEditDetails.setFont(new Font("Arial", Font.PLAIN, 15));
        btnEditDetails.setSize(200, 20);
        btnEditDetails.addActionListener(e -> {
            int rowToEdit = tblTasks.getSelectedRow();

            if (rowToEdit >= 0) {
                EditTaskDetailsDialog dialog = new EditTaskDetailsDialog();
                dialog.lblTaskID.setText("Task ID: " +  tblTasks.getValueAt(rowToEdit, 0));
                dialog.lblTaskName.setText("Task Name: ");
                dialog.tTaskName.setText((String) tblTasks.getValueAt(rowToEdit, 1));
                dialog.lblLocation.setText("Location: ");
                dialog.tLocation.setText((String) tblTasks.getValueAt(rowToEdit, 2));
                dialog.lblPriority.setText("Priority: ");
                dialog.tPriority.setText((String) tblTasks.getValueAt(rowToEdit, 3));
                dialog.lblDescription.setText("Description: ");
                dialog.tDescription.setText((String) tblTasks.getValueAt(rowToEdit, 4));
                dialog.lblFrequency.setText("Frequency: ");
                dialog.tFrequency.setText((String) tblTasks.getValueAt(rowToEdit, 5));
                dialog.lblCreator.setText("Submitted By: " + tblTasks.getValueAt(rowToEdit, 6));
                if (tblTasks.getValueAt(rowToEdit, 7) == null) {
                    dialog.lblAssignedTo.setText("Assigned To: No one");
                } else {
                    dialog.lblAssignedTo.setText("Assigned To: ");
                    dialog.tAssignedTo.setText((String) tblTasks.getValueAt(rowToEdit, 7));
                }
                dialog.lblTimeRequired.setText("Duration (mins): ");
                dialog.tTimeRequired.setText(String.valueOf(tblTasks.getValueAt(rowToEdit, 8)));
                dialog.setVisible(true);

                int taskId = (Integer) tblTasks.getValueAt(rowToEdit, 0);
                String title;
                String location;
                int timeRequired;
                String priority;
                String description;
                String frequency;
                String submittedBy = (String) tblTasks.getValueAt(rowToEdit, 6);
                String caretaker;

                if(dialog.btnUpdate.getModel().isPressed()) {
                    try {
                        title = dialog.tTaskName.getText();
                        location = dialog.tLocation.getText();
                        timeRequired = Integer.parseInt(dialog.tTimeRequired.getText());
                        priority = dialog.tPriority.getText();
                        description = dialog.tDescription.getText();
                        frequency = dialog.tFrequency.getText();
                        caretaker = dialog.tAssignedTo.getText();

                    } catch (Exception ex) {
                        title = (String) tblTasks.getValueAt(rowToEdit, 1);
                        location = (String) tblTasks.getValueAt(rowToEdit, 2);
                        priority = (String) tblTasks.getValueAt(rowToEdit, 3);
                        description = (String) tblTasks.getValueAt(rowToEdit, 4);
                        frequency = (String) tblTasks.getValueAt(rowToEdit, 5);
                        submittedBy = (String) tblTasks.getValueAt(rowToEdit, 6);
                        caretaker = (String) tblTasks.getValueAt(rowToEdit, 7);
                        timeRequired = (Integer) tblTasks.getValueAt(rowToEdit, 8);

                    }
                    if (!title.isEmpty()) {
                        theViewTaskHandler.editTask(taskId, title, location, timeRequired, priority, description, frequency, submittedBy, caretaker, 0);
                    }
                    theViewTaskHandler.refreshAllocationTable();
                }

            }
        });
        pnlButtons.add(btnEditDetails);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.PLAIN, 15));
        btnBack.setSize(150, 20);
        btnBack.addActionListener(e -> {
            setVisible(false);
            theViewTaskHandler.loadAdministratorMainMenu();
            theViewTaskHandler.administratorMainMenu.setVisible(true);
        });
        pnlButtons.add(btnBack);
    }

    void displayTableData(ArrayList<Task> taskArrayList) {

        DefaultTableModel mdl = (DefaultTableModel) tblTasks.getModel();
        mdl.setRowCount(0);

        for (Task t : taskArrayList) {
            mdl.addRow(new Object[]{t.getTaskId(), t.getTitle(), t.getLocation(), t.getPriority(), t.getDescription(),
                    t.getFrequency(), t.getSubmittedBy(), t.getCaretaker(), t.getTimeRequired()});
        }
    }

}
