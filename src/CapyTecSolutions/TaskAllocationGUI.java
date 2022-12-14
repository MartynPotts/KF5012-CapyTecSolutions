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

        JButton btnClearFilter = new JButton("Clear Filter");
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
            theTaskAllocationHandler.refreshAllocationTable();
        });
        pnlAssign.add(btnRefresh);

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

                theTaskAllocationHandler.refreshAllocationTable();
            } catch (Exception ex) {
                ex.printStackTrace();

            }
        });
        pnlAssign.add(btnAssign);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.PLAIN, 15));
        btnBack.setSize(150, 20);
        btnBack.addActionListener(e -> {
            theTaskAllocationHandler.loadCaretakerMainMenu();
            theTaskAllocationHandler.caretakerMainMenu.setVisible(true);
            setVisible(false);

        });
        pnlAssign.add(btnBack);

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
