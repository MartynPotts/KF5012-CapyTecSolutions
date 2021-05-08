package CapyTecSolutions;

import javax.swing.*;
import java.awt.*;

public class CaretakerMainMenu extends JFrame {

    CapyTecSolutionsController caretakerMenuController;

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

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Caretaker Menu");
        setBounds(300,90,500,500);

        pnlMenuItems = new JPanel();
        pnlMenuItems.setLayout(null);

        lblTitle = new JLabel("Main Menu");
        lblTitle.setFont(new Font("Arial", Font.PLAIN, 20));
        lblTitle.setSize(300,30);
        pnlMenuItems.add(lblTitle);

        btnViewAllocateTasks = new JButton("View/Allocate Tasks");
        btnViewAllocateTasks.setFont(new Font("Arial", Font.PLAIN,15));
        btnViewAllocateTasks.setSize(100,20);
        btnViewAllocateTasks.setLocation(350,300);
        btnViewAllocateTasks.addActionListener(e ->{
            CapyTecSolutionsController taskAllocationController = new CapyTecSolutionsController();
            taskAllocationController.loadTaskAllocation();
        });
        pnlMenuItems.add(btnViewAllocateTasks);

    }

}
