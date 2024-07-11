package osp.pkgfinal.group.pkg1;

import java.awt.*;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {

    private JLabel lblTitle, lblSignUp, imglogo;
    private JButton btnRegister, btnAdmin, btncus;

    public Menu() {
        setSize(800, 600);
        setTitle("Admin Portal");
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(255, 204, 153));

        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        imglogo = new JLabel();
        imglogo.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\Lanzdrei Salas\\Documents\\NetBeansProjects\\New Folder\\OSP-FINAL---GROUP-11\\OSP-FINAL---GROUP-1\\src\\osp\\pkgfinal\\group\\pkg1\\logo.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        add(imglogo);

        lblTitle = new JLabel("ONLINE SHOPPING PLATFORM");
        lblTitle.setFont(new Font("Georgia", Font.BOLD, 20));
        add(lblTitle);

        lblSignUp = new JLabel("Register as Administrator");
        lblSignUp.setFont(new Font("Bell MT", Font.PLAIN, 14));
        add(lblSignUp);

        btnRegister = new JButton("SIGN UP");
        btnRegister.setBackground(Color.WHITE);
        btnRegister.setForeground(new Color(102, 51, 0));
        add(btnRegister);

        btnAdmin = new JButton("ADMIN");
        btnAdmin.setBackground(Color.WHITE);
        btnAdmin.setForeground(new Color(102, 51, 0));
        add(btnAdmin);

        btncus = new JButton("CUSTOMER");
        btncus.setBackground(Color.WHITE);
        btncus.setForeground(new Color(102, 51, 0));
        add(btncus);

        btnRegister.addActionListener(this);
        btnAdmin.addActionListener(this);
        btncus.addActionListener(this);

        // Set initial bounds
        setComponentBounds();

        // Add a component listener to adjust component sizes when the frame is resized
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                setComponentBounds();
            }
        });
    }

    private void setComponentBounds() {
        int frameWidth = getWidth();
        int frameHeight = getHeight();

        imglogo.setBounds((frameWidth - 50) / 2 - 30, (frameHeight - 50) / 2 - 210, 50, 50);
        lblTitle.setBounds((frameWidth - 350) / 2, (frameHeight - 80) / 2 - 90, 350, 80);
        lblSignUp.setBounds((frameWidth - 250) / 2 - 50, (frameHeight - 20) / 2 + 50, 250, 20);
        btnRegister.setBounds((frameWidth - 100) / 2 + 150, (frameHeight - 20) / 2 + 50, 100, 20);
        btnAdmin.setBounds((frameWidth - 150) / 2 - 100, (frameHeight - 30) / 2 - 10, 150, 30);
        btncus.setBounds((frameWidth - 150) / 2 + 100, (frameHeight - 30) / 2 - 10, 150, 30);
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
        } else if (e.getSource() == btncus) {
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
