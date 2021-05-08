package CapyTecSolutions;

import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JFrame {

    CapyTecSolutionsController theLoginHandler;

    private final JPanel pnlContent = new JPanel();

    private JPanel pnlButtons;

    private JLabel lblUsername;
    private JLabel lblPassword;

    public JTextField tUsername;
    public JPasswordField tPassword;

    public JButton btnLogin;
    public JButton btnAdminLogin;

    public LoginGUI() {

    }

    public static void main(String[] args) {
        try {
            LoginGUI gui = new LoginGUI();
            gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            gui.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public LoginGUI(CapyTecSolutionsController loginHandler) {

        theLoginHandler = loginHandler;

        setBounds(100, 100, 500, 500);
        getContentPane().setLayout(new BorderLayout());
        setTitle("CapyTec Solutions - Log in");
        getContentPane().add(pnlContent, BorderLayout.CENTER);

        lblUsername = new JLabel("Username : ");
        pnlContent.add(lblUsername);

        tUsername = new JTextField();
        tUsername.setColumns(10);
        pnlContent.add(tUsername);

        lblPassword = new JLabel("Password :");
        pnlContent.add(lblPassword);

        tPassword = new JPasswordField();
        tPassword.setColumns(10);
        pnlContent.add(tPassword);

        pnlButtons = new JPanel();
        pnlButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(pnlButtons, BorderLayout.SOUTH);

        btnLogin = new JButton("Login");
        btnLogin.setActionCommand("Login");
        pnlButtons.add(btnLogin);
        btnLogin.addActionListener(e -> {
            String username = tUsername.getText();
            String password = String.valueOf(tPassword.getPassword());
            theLoginHandler.loginCaretaker(username, password);
            dispose();
        });

        btnAdminLogin = new JButton("Admin Login");
        btnAdminLogin.setActionCommand("Login");
        pnlButtons.add(btnAdminLogin);
        btnAdminLogin.addActionListener(e -> {
            String username = tUsername.getText();
            String password = String.valueOf(tPassword.getPassword());
            theLoginHandler.loginAdmin(username, password);
            dispose();
        });

    }
}
