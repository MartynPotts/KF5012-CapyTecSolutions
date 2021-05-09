package CapyTecSolutions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddNewCaretakerDialog extends JDialog {

    private final JPanel pnlContent = new JPanel();

    public JPanel pnlButtons;

    public JTextField tCaretakerFName;
    public JTextField tCaretakerSName;
    public JTextField tCaretakerUsername;
    public JTextField tCaretakerPassword;

    public JButton btnAdd;

    public static void main(String[] args){
        AddNewCaretakerDialog dialog = new AddNewCaretakerDialog();
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }

    public AddNewCaretakerDialog(){
        setModal(true);
        setBounds(100,100,500,500);
        getContentPane().setLayout(new BorderLayout());
        setTitle("Add new caretaker");
        getContentPane().add(pnlContent,BorderLayout.CENTER);

        JLabel lblCaretakerFName = new JLabel("Caretaker First Name:");
        pnlContent.add(lblCaretakerFName);

        tCaretakerFName = new JTextField();
        tCaretakerFName.setColumns(10);
        pnlContent.add(tCaretakerFName);

        JLabel lblCaretakerSName = new JLabel("Caretaker Surname:");
        pnlContent.add(lblCaretakerSName);

        tCaretakerSName = new JTextField();
        tCaretakerSName.setColumns(10);
        pnlContent.add(tCaretakerSName);

        JLabel lblCaretakerUsername = new JLabel("Caretaker Username:");
        pnlContent.add(lblCaretakerUsername);

        tCaretakerUsername = new JTextField();
        tCaretakerUsername.setColumns(10);
        pnlContent.add(tCaretakerUsername);

        JLabel lblCaretakerPassword = new JLabel("Caretaker Password:");
        pnlContent.add(lblCaretakerPassword);

        tCaretakerPassword = new JTextField();
        tCaretakerPassword.setColumns(10);
        pnlContent.add(tCaretakerPassword);

        pnlButtons = new JPanel();
        pnlButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(pnlButtons, BorderLayout.SOUTH);

        btnAdd = new JButton("Add Caretaker");
        btnAdd.setActionCommand("Add");
        pnlButtons.add(btnAdd);
        getRootPane().setDefaultButton(btnAdd);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setModal(false);
                dispose();
            }
        });

    }
}
