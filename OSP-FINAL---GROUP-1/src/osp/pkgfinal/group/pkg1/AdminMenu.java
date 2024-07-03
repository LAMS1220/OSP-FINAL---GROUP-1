package osp.pkgfinal.group.pkg1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMenu extends JFrame implements ActionListener {
    private JButton btnViewRecords, btnReturn;

    public AdminMenu() {
        setTitle("Admin Menu");
        setSize(400, 220);
        setLayout(null);     
        getContentPane().setBackground(new Color(255, 204, 153));
        
        btnViewRecords = new JButton("View Records");
        btnViewRecords.setBounds(100, 50, 200, 30);
        btnViewRecords.setBackground(Color.WHITE); 
        btnViewRecords.setForeground(new Color(102, 51, 0)); 
        btnViewRecords.addActionListener(this);

        btnReturn = new JButton("Return");
        btnReturn.setBounds(100, 100, 200, 30);
        btnReturn.setBackground(Color.WHITE); 
        btnReturn.setForeground(new Color(102, 51, 0)); 
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