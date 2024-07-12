package osp.pkgfinal.group.pkg1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMenu extends JFrame implements ActionListener {
    private JButton btnViewRecords, btnReturn;

    public AdminMenu() {
        setTitle("Admin Menu");
        setSize(800, 600);
        setLayout(null);  
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(255, 204, 153));
        
        btnViewRecords = new JButton("VIEW RECORDS");
        btnViewRecords.setBounds(260, 170, 250, 60);
        btnViewRecords.setBackground(Color.WHITE); 
        btnViewRecords.setForeground(new Color(102, 51, 0)); 
        btnViewRecords.setFont(new Font("Arial", Font.BOLD, 20));
        btnViewRecords.addActionListener(this);

        btnReturn = new JButton("RETURN");
        btnReturn.setBounds(260, 270, 250, 60);
        btnReturn.setBackground(Color.WHITE); 
        btnReturn.setForeground(new Color(102, 51, 0)); 
        btnReturn.setFont(new Font("Arial", Font.BOLD, 20));
        btnReturn.addActionListener(this);

        add(btnViewRecords);
        add(btnReturn);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnViewRecords) {
            new Dashboard();
            dispose();
        } else if (e.getSource() == btnReturn) {
            Menu login = new Menu();
            login.setVisible(true);
            dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdminMenu::new);
    }
}