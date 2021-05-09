package CapyTecSolutions;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CaretakerManagementMenu extends JFrame {

    CapyTecSolutionsController caretakerManagementMenuController;



    private JTable tblCaretakers;


    public CaretakerManagementMenu() {

    }

    public static void main(String[] args) {
        CaretakerMainMenu menu = new CaretakerMainMenu();
    }

    public CaretakerManagementMenu(CapyTecSolutionsController caretakerManagementController) {


        caretakerManagementMenuController = caretakerManagementController;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Caretaker Management Menu");
        setBounds(300, 90, 900, 600);

        JPanel c = new JPanel();
        c.setLayout(new BorderLayout(0, 0));
        c.setBorder(new EmptyBorder(25, 15, 25, 15));
        setContentPane(c);

        JPanel pnlCaretakers = new JPanel();
        c.add(pnlCaretakers, BorderLayout.NORTH);
        pnlCaretakers.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JScrollPane spCaretakers = new JScrollPane();
        c.add(spCaretakers, BorderLayout.CENTER);

        tblCaretakers = new JTable();
        tblCaretakers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblCaretakers.setModel(new DefaultTableModel(
                new String[]{
                        "Caretaker ID", "Caretaker First Name", "Caretaker Surname", "Caretaker Username", "Caretaker Password"
                }, 0)
        );
        tblCaretakers.setAutoCreateRowSorter(true);
        spCaretakers.setViewportView(tblCaretakers);

        JPanel pnlManage = new JPanel();
        c.add(pnlManage, BorderLayout.SOUTH);

        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.setFont(new Font("Arial", Font.PLAIN, 15));
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                caretakerManagementMenuController.refreshCaretakerTable();
            }
        });
        pnlManage.add(btnRefresh);

        JButton btnAddCaretaker = new JButton("Add Caretaker");
        btnAddCaretaker.setFont(new Font("Arial", Font.PLAIN, 15));
        btnAddCaretaker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewCaretakerDialog dialog = new AddNewCaretakerDialog();
                dialog.setVisible(true);

                String caretakerFName = dialog.tCaretakerFName.getText();
                String caretakerSName = dialog.tCaretakerSName.getText();
                String caretakerUsername = dialog.tCaretakerUsername.getText();
                String caretakerPassword = dialog.tCaretakerPassword.getText();

                if ((!caretakerFName.isEmpty()) && (!caretakerSName.isEmpty()) && (!caretakerUsername.isEmpty()) && (!caretakerPassword.isEmpty())) {
                    caretakerManagementMenuController.addCaretaker(caretakerFName, caretakerSName, caretakerUsername, caretakerPassword);
                }
                caretakerManagementMenuController.refreshCaretakerTable();
            }
        });
        pnlManage.add(btnAddCaretaker);

        JButton btnEditCaretaker = new JButton("Edit Caretaker");
        btnEditCaretaker.setFont(new Font("Arial", Font.PLAIN, 15));
        btnEditCaretaker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int rowToChange = tblCaretakers.getSelectedRow();

                if (rowToChange >= 0) {
                    EditCaretakerDetailsDialog dialog = new EditCaretakerDetailsDialog();
                    dialog.lblCaretakerID.setText("Caretaker ID: " + tblCaretakers.getValueAt(rowToChange, 0));
                    dialog.tCaretakerFName.setText((String) tblCaretakers.getValueAt(rowToChange, 1));
                    dialog.tCaretakerSName.setText((String) tblCaretakers.getValueAt(rowToChange, 2));
                    dialog.tCaretakerUsername.setText((String) tblCaretakers.getValueAt(rowToChange, 3));
                    dialog.tCaretakerPassword.setText((String) tblCaretakers.getValueAt(rowToChange, 4));
                    dialog.setVisible(true);

                    int caretakerID = (Integer) tblCaretakers.getValueAt(rowToChange, 0);
                    String caretakerFName;
                    String caretakerSName;
                    String caretakerUsername;
                    String caretakerPassword;

                    try {
                        caretakerFName = dialog.tCaretakerFName.getText();
                        caretakerSName = dialog.tCaretakerSName.getText();
                        caretakerUsername = dialog.tCaretakerUsername.getText();
                        caretakerPassword = dialog.tCaretakerPassword.getText();
                    } catch (Exception ex){
                        caretakerFName = (String) tblCaretakers.getValueAt(rowToChange, 1);
                        caretakerSName = (String) tblCaretakers.getValueAt(rowToChange, 2);
                        caretakerUsername = (String) tblCaretakers.getValueAt(rowToChange, 3);
                        caretakerPassword = (String) tblCaretakers.getValueAt(rowToChange, 4);
                    }

                    if ((!caretakerFName.isEmpty()) && (!caretakerSName.isEmpty()) && (!caretakerUsername.isEmpty()) && (!caretakerPassword.isEmpty())) {
                        caretakerManagementMenuController.editCaretaker(caretakerID,caretakerFName, caretakerSName, caretakerUsername, caretakerPassword);
                    }
                    caretakerManagementMenuController.refreshCaretakerTable();
                }


            }
        });
        pnlManage.add(btnEditCaretaker);

        JButton btnDeleteCaretaker = new JButton("Delete Caretaker");
        btnDeleteCaretaker.setFont(new Font("Arial", Font.PLAIN, 15));
        btnDeleteCaretaker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowtoDelete = tblCaretakers.getSelectedRow();

                if(rowtoDelete >= 0){
                    int caretakerID = (Integer) tblCaretakers.getValueAt(rowtoDelete, 0);
                    caretakerManagementMenuController.deleteCaretaker(caretakerID);
                }
                caretakerManagementMenuController.refreshCaretakerTable();
            }
        });
        pnlManage.add(btnDeleteCaretaker);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.PLAIN, 15));
        btnBack.setSize(150, 20);
        btnBack.addActionListener(e -> {
            caretakerManagementMenuController.loadAdministratorMainMenu();
            caretakerManagementMenuController.administratorMainMenu.setVisible(true);
            setVisible(false);
        });
        pnlManage.add(btnBack);

    }

    void displayTableData(ArrayList<Caretaker> caretakerArrayList) {

        DefaultTableModel mdl = (DefaultTableModel) tblCaretakers.getModel();
        mdl.setRowCount(0);

        for (Caretaker c : caretakerArrayList) {
            mdl.addRow(new Object[]{c.getCaretakerID(), c.getCaretakerFName(), c.getCaretakerSName(), c.getCaretakerUsername(), c.getCaretakerPassword()});
        }
    }


}
