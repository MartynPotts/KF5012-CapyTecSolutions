package CapyTecSolutions;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CaretakerMainMenu extends JFrame {

    CapyTecSolutionsController caretakerMenuController;

    private JPanel pnlMenu;
    private JPanel pnlMenuItems;

    private JLabel lblTitle;

    private JButton btnViewAllocateTasks;
    private JButton btnLogout;

    public CaretakerMainMenu(){

    }

    public static void main(String[] args){
        CaretakerMainMenu menu = new CaretakerMainMenu();
    }

    public CaretakerMainMenu(CapyTecSolutionsController caretakerController){

        caretakerMenuController = caretakerController;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Caretaker Menu");
        setBounds(300,90,500,500);

        pnlMenu = new JPanel();
        pnlMenu.setLayout(new BorderLayout(0,0));
        pnlMenu.setBorder(new EmptyBorder(25,25,25,25));
        setContentPane(pnlMenu);

        pnlMenuItems = new JPanel();
        pnlMenu.add(pnlMenuItems);

        lblTitle = new JLabel("Main Menu");
        lblTitle.setFont(new Font("Arial", Font.PLAIN, 20));
        lblTitle.setSize(300,30);
        pnlMenuItems.add(lblTitle);

        btnViewAllocateTasks = new JButton("View/Allocate Tasks");
        btnViewAllocateTasks.setFont(new Font("Arial", Font.PLAIN,15));
        btnViewAllocateTasks.setSize(100,20);
        btnViewAllocateTasks.setLocation(350,300);
        btnViewAllocateTasks.addActionListener(e ->{
            CapyTecSolutionsController caretakerMenuController = new CapyTecSolutionsController();
            caretakerMenuController.loadTaskAllocation();
        });
        pnlMenuItems.add(btnViewAllocateTasks);

        btnLogout = new JButton("Log Out");
        btnLogout.setFont(new Font("Arial", Font.PLAIN,15));
        btnLogout.setSize(100,20);
        btnLogout.setLocation(350,300);
        btnLogout.addActionListener(e ->{
            CapyTecSolutionsController caretakerMenuController = new CapyTecSolutionsController();
            caretakerMenuController.logout();
            setVisible(false);
        });
        pnlMenuItems.add(btnLogout);

    }

}
