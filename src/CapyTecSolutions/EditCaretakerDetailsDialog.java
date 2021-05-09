package CapyTecSolutions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditCaretakerDetailsDialog extends JDialog {

    private final JPanel pnlContent = new JPanel();

    public JPanel pnlButtons;

    public JLabel lblCaretakerID;
    public JLabel lblCaretakerFName;
    public JLabel lblCaretakerSName;
    public JLabel lblCaretakerUsername;
    public JLabel lblCaretakerPassword;

    public JTextField tCaretakerFName;
    public JTextField tCaretakerSName;
    public JTextField tCaretakerUsername;
    public JTextField tCaretakerPassword;

    public JButton btnUpdate;

    public static void main(String[] args) {
        try {
            EditCaretakerDetailsDialog dialog = new EditCaretakerDetailsDialog();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public EditCaretakerDetailsDialog (){
        setModal(true);
        setBounds(100, 100, 500, 500);
        getContentPane().setLayout(new BorderLayout());
        setTitle("View/Edit Task Details");
        getContentPane().add(pnlContent, BorderLayout.CENTER);

        lblCaretakerID = new JLabel("Caretaker ID: ");
        pnlContent.add(lblCaretakerID);

        lblCaretakerFName = new JLabel("First Name: ");
        lblCaretakerFName.setLocation(100, 50);
        pnlContent.add(lblCaretakerFName);

        tCaretakerFName = new JTextField();
        tCaretakerFName.setLocation(150, 50);
        pnlContent.add(tCaretakerFName);

        lblCaretakerSName = new JLabel("Surname: ");
        lblCaretakerSName.setLocation(100, 100);
        pnlContent.add(lblCaretakerSName);

        tCaretakerSName = new JTextField();
        tCaretakerSName.setLocation(150, 100);
        pnlContent.add(tCaretakerSName);

        lblCaretakerUsername = new JLabel("Username: ");
        lblCaretakerUsername.setLocation(100, 150);
        pnlContent.add(lblCaretakerUsername);

        tCaretakerUsername = new JTextField();
        tCaretakerUsername.setLocation(150, 200);
        pnlContent.add(tCaretakerUsername);

        lblCaretakerPassword = new JLabel("Password: ");
        lblCaretakerPassword.setLocation(100, 200);
        pnlContent.add(lblCaretakerPassword);

        tCaretakerPassword = new JTextField();
        tCaretakerPassword.setLocation(150, 150);
        pnlContent.add(tCaretakerPassword);


        pnlButtons = new JPanel();
        pnlButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(pnlButtons, BorderLayout.SOUTH);

        btnUpdate = new JButton("Update");
        btnUpdate.setActionCommand("Update");
        pnlButtons.add(btnUpdate);
        getRootPane().setDefaultButton(btnUpdate);
        btnUpdate.addActionListener(e -> {
            setModal(false);
            dispose();
        });
    }

}
