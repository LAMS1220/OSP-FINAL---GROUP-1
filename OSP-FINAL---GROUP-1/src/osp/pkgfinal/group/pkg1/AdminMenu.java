package osp.pkgfinal.group.pkg1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMenu extends JFrame implements ActionListener {
    private JButton btnViewRecords, btnReturn;

    public AdminMenu() {
        setTitle("Admin Menu");
        setSize(400, 200);
        setLayout(null);

        btnViewRecords = new JButton("View Records");
        btnViewRecords.setBounds(100, 50, 200, 30);
        btnViewRecords.addActionListener(this);

        btnReturn = new JButton("Return");
        btnReturn.setBounds(100, 100, 200, 30);
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