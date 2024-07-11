package osp.pkgfinal.group.pkg1;

import java.awt.*;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {
    
    private JLabel lblTitle, lblSignUp, imglogo;                                                                                                                                                                                                                                                                                             
    private JButton btnRegister, btnAdmin, btncus; 
    
    public Menu() {
        setSize(500, 350);
        setTitle("Admin Portal");
        getContentPane().setBackground(new Color(255, 204, 153));
        
        imglogo = new JLabel();
        imglogo.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\Lanzdrei Salas\\Documents\\NetBeansProjects\\New Folder\\OSP-FINAL---GROUP-11\\OSP-FINAL---GROUP-1\\src\\osp\\pkgfinal\\group\\pkg1\\logo.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        imglogo.setBounds(40, 55, 50, 50);
        add(imglogo);

        lblTitle = new JLabel("ONLINE SHOPPING PLATFORM");
        lblTitle.setFont(new Font("Georgia", Font.BOLD, 20));
        
        lblSignUp = new JLabel("Register as Administrator");
        lblSignUp.setFont(new Font("Bell MT", Font.PLAIN, 14));

        btnRegister = new JButton("Sign Up");
        btnRegister.setBackground(Color.WHITE); 
        btnRegister.setForeground(new Color(102, 51, 0));
        btnAdmin = new JButton("ADMIN");
        btnAdmin.setBackground(Color.WHITE); 
        btnAdmin.setForeground(new Color(102, 51, 0));
        btncus = new JButton ("CUSTOMER");
        btncus.setBackground(Color.WHITE); 
        btncus.setForeground(new Color(102, 51, 0));
        
        
        add(lblTitle);
        add(btnRegister);
        add(btnAdmin);
        add(btncus);
        add(lblSignUp);
        
        lblTitle.setBounds(100, 40, 350, 80);
        lblSignUp.setBounds(200, 250, 250, 20);
        btnRegister.setBounds(360, 250, 100, 20);
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