package osp.pkgfinal.group.pkg1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminRegister extends JFrame implements ActionListener {

    private JLabel lblUsername, lblPassword, lblTitle;
    private JTextField txtfldUsername;
    private JPasswordField txtfldPassword;
    private JButton btnRegister, btnBack;
    private Connection conn;

    public AdminRegister() {
        initializeDBConnection();

        setTitle("Admin Registration");
        setSize(400, 300);
        setLayout(null);
        getContentPane().setBackground(new Color(255, 204, 153)); // Setting background color

        lblTitle = new JLabel("ADMIN REGISTRATION");
        lblTitle.setBounds(70, 20, 250, 30);
        lblTitle.setFont(new Font("Georgia", Font.BOLD, 19)); // Using a bold font with a similar style
        lblTitle.setForeground(new Color(102, 51, 0)); // Darker shade for better contrast
       
        lblUsername = new JLabel("Username:");
        lblUsername.setBounds(50, 80, 100, 25);
        lblUsername.setFont(new Font("Arial", Font.BOLD, 14));
        lblUsername.setForeground(new Color(102, 51, 0)); // Darker shade for better contrast

        lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 120, 100, 25);
        lblPassword.setFont(new Font("Arial", Font.BOLD, 14));
        lblPassword.setForeground(new Color(102, 51, 0)); // Darker shade for better contrast

        txtfldUsername = new JTextField();
        txtfldUsername.setBounds(150, 80, 180, 25);
        txtfldUsername.setFont(new Font("Arial", Font.PLAIN, 14));

        txtfldPassword = new JPasswordField();
        txtfldPassword.setBounds(150, 120, 180, 25);
        txtfldPassword.setFont(new Font("Arial", Font.PLAIN, 14));

        btnRegister = new JButton("Register");
        btnRegister.setBounds(50, 180, 100, 30);
        btnRegister.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegister.setBackground(Color.WHITE);
        btnRegister.setForeground(new Color(102, 51, 0)); // Darker shade for better contrast
        btnRegister.addActionListener(this);

        btnBack = new JButton("Back");
        btnBack.setBounds(230, 180, 100, 30);
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));
        btnBack.setBackground(Color.WHITE);
        btnBack.setForeground(new Color(102, 51, 0)); // Darker shade for better contrast
        btnBack.addActionListener(this);

        add(lblTitle);
        add(lblUsername);
        add(lblPassword);
        add(txtfldUsername);
        add(txtfldPassword);
        add(btnRegister);
        add(btnBack);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void initializeDBConnection() {
        try {
            String URL = "jdbc:mysql://localhost:3306/osp";
            String USER = "lance";
            String PASSWORD = "12345";
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connection successful");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegister) {
            registerAdmin(txtfldUsername.getText(), new String(txtfldPassword.getPassword()));
            JOptionPane.showMessageDialog(this, "Admin registered successfully!");
            dispose();
            new AdminMenu().setVisible(true);
        } else if (e.getSource() == btnBack) {
            dispose();
            new Menu().setVisible(true);
        }
    }

    private void registerAdmin(String username, String password) {
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO admins (username, password) VALUES (?, ?)")) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new AdminRegister();
    }
}