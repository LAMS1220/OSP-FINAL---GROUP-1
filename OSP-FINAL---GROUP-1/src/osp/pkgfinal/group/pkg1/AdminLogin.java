package osp.pkgfinal.group.pkg1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminLogin extends JFrame implements ActionListener {
    private JLabel lblTitle, lblUsername, lblPassword;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnBack;
    private Connection conn;

    public AdminLogin() {
        initializeDBConnection();

        setTitle("Admin Login");
        setLayout(null);       
        getContentPane().setBackground(new Color(255, 204, 153));
        
        
        lblTitle = new JLabel("ADMIN LOGIN");
        lblTitle.setFont(new Font("Georgia", Font.BOLD, 24));
        lblTitle.setBounds(90, 20, 250, 30);
        lblTitle.setForeground(new Color(102, 51, 0));

        lblUsername = new JLabel("Username:");
        lblUsername.setBounds(50, 80, 100, 25);
        lblUsername.setFont(new Font("Arial", Font.BOLD, 15));
        lblUsername.setForeground(new Color(102, 51, 0));

        lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 120, 100, 25);
        lblPassword.setFont(new Font("Arial", Font.BOLD, 15));
        lblPassword.setForeground(new Color(102, 51, 0));

        txtUsername = new JTextField();
        txtUsername.setBounds(150, 80, 150, 25);
        txtUsername.setFont(new Font("Arial", Font.PLAIN, 15));

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 120, 150, 25);
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 15));

        btnLogin = new JButton("Login");
        btnLogin.setBounds(50, 180, 100, 30);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 15));
        btnLogin.setBackground(Color.WHITE); 
        btnLogin.setForeground(new Color(102, 51, 0)); 
        btnLogin.addActionListener(this);

        btnBack = new JButton("Back");
        btnBack.setBounds(200, 180, 100, 30);
        btnBack.setFont(new Font("Arial", Font.BOLD, 15));
        btnBack.setBackground(Color.WHITE); 
        btnBack.setForeground(new Color(102, 51, 0)); ; 
        btnBack.addActionListener(this);

        add(lblTitle);
        add(lblUsername);
        add(lblPassword);
        add(txtUsername);
        add(txtPassword);
        add(btnLogin);
        add(btnBack);

        setSize(380, 300);
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
            System.out.println("Database connection failed");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());

            if (!loginAdmin(username, password)) {
                JOptionPane.showMessageDialog(this, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Login successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new Dashboard().setVisible(true);
            }
        } else if (e.getSource() == btnBack) {
            dispose();
            new Menu().setVisible(true);
        }
    }

    private boolean loginAdmin(String username, String password) {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM admins WHERE username = ? AND password = ?")) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }}
