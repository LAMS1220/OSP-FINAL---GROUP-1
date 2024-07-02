package osp.pkgfinal.group.pkg1;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {
    
    private JLabel lblTitle, lblSignUp;                                                                                                                                                                                                                                                                                             
    private JButton btnRegister, btnAdmin, btncus; 
    
    public Menu() {
        setSize(500, 500);
        setTitle("Admin Portal");

        lblTitle = new JLabel("ONLINE SHOPPING PLATFORM");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        
        lblSignUp = new JLabel("Register as Administrator");
        lblSignUp.setFont(new Font("Bell MT", Font.PLAIN, 14));

        btnRegister = new JButton("Sign Up");
        btnAdmin = new JButton("ADMIN");
        btncus = new JButton ("CUSTOMER");
        
        
        add(lblTitle);
        add(btnRegister);
        add(btnAdmin);
        add(btncus);
        add(lblSignUp);
        
        lblTitle.setBounds(100, 40, 300, 80);
        lblSignUp.setBounds(220, 400, 250, 20);
        btnRegister.setBounds(380, 400, 100, 20);
        btnAdmin.setBounds(80, 150, 150, 30);
        btncus.setBounds(270, 150, 150, 30);
        
        btnRegister.addActionListener(this);
        btnAdmin.addActionListener(this);
        btncus.addActionListener(this);
        
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegister) {
            JOptionPane.showMessageDialog(this, "Welcome.", "SIGNUP", JOptionPane.INFORMATION_MESSAGE);
            AdminRegister adminRegister = new AdminRegister();
            adminRegister.setVisible(true);
            dispose();
        } else if (e.getSource() == btnAdmin) {
            JOptionPane.showMessageDialog(this, "Welcome Back.", "ADMIN", JOptionPane.INFORMATION_MESSAGE);
            AdminLogin adminLogin = new AdminLogin();
            adminLogin.setVisible(true);
            dispose();
        }
        else if (e.getSource() == btncus) {
            JOptionPane.showMessageDialog(this, "Welcome Customer!", "SHOPPING", JOptionPane.INFORMATION_MESSAGE);
            HomePage home = new HomePage();
            home.setVisible(true);
            dispose();
        }
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Menu login = new Menu();
                login.setVisible(true);
            }
        });
    }
}