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
            setSize(800, 600);
            setLayout(null);
            setLocationRelativeTo(null);
            getContentPane().setBackground(new Color(255, 204, 153));

            lblTitle = new JLabel("ADMIN REGISTRATION");
            lblTitle.setFont(new Font("Georgia", Font.BOLD, 20)); 
            lblTitle.setForeground(new Color(102, 51, 0));
            add(lblTitle);

            lblUsername = new JLabel("Username:");
            lblUsername.setFont(new Font("Arial", Font.BOLD, 14));
            lblUsername.setForeground(new Color(102, 51, 0)); 
            add(lblUsername);

            lblPassword = new JLabel("Password:");
            lblPassword.setFont(new Font("Arial", Font.BOLD, 14));
            lblPassword.setForeground(new Color(102, 51, 0)); 
            add(lblPassword);

            txtfldUsername = new JTextField();
            txtfldUsername.setFont(new Font("Arial", Font.PLAIN, 14));
            add(txtfldUsername);

            txtfldPassword = new JPasswordField();
            txtfldPassword.setFont(new Font("Arial", Font.PLAIN, 14));
            add(txtfldPassword);

            btnRegister = new JButton("Register");
            btnRegister.setFont(new Font("Arial", Font.BOLD, 14));
            btnRegister.setBackground(Color.WHITE); 
            btnRegister.setForeground(new Color(102, 51, 0)); 
            btnRegister.addActionListener(this);
            add(btnRegister);

            btnBack = new JButton("Back");
            btnBack.setFont(new Font("Arial", Font.BOLD, 14));
            btnBack.setBackground(Color.WHITE); 
            btnBack.setForeground(new Color(102, 51, 0)); 
            btnBack.addActionListener(this);
            add(btnBack);

            setComponentBounds();

            addComponentListener(new java.awt.event.ComponentAdapter() {
                @Override
                public void componentResized(java.awt.event.ComponentEvent e) {
                    setComponentBounds();
                }
            });

            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);
        }

        private void setComponentBounds() {
            int frameWidth = getWidth();
            int frameHeight = getHeight();

            lblTitle.setBounds((frameWidth - 250) / 2, (frameHeight - 30) / 2 - 170, 280, 30);
            lblUsername.setBounds((frameWidth - 100) / 2 - 100, (frameHeight - 25) / 2 - 100, 100, 25);
            lblPassword.setBounds((frameWidth - 100) / 2 - 100, (frameHeight - 25) / 2 - 60, 100, 25);
            txtfldUsername.setBounds((frameWidth - 180) / 2 + 50, (frameHeight - 25) / 2 - 100, 180, 25);
            txtfldPassword.setBounds((frameWidth - 180) / 2 + 50, (frameHeight - 25) / 2 - 60, 180, 25);
            btnRegister.setBounds((frameWidth - 100) / 2 - 90, (frameHeight - 30) / 2, 100, 30);
            btnBack.setBounds((frameWidth - 100) / 2 + 90, (frameHeight - 30) / 2, 100, 30);
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
                String username = txtfldUsername.getText();
                String password = new String(txtfldPassword.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Username and Password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (isUsernameTaken(username)) {
                        JOptionPane.showMessageDialog(this, "Username already taken.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        registerAdmin(username, password);
                        JOptionPane.showMessageDialog(this, "Admin registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        new AdminMenu().setVisible(true);
                    }
                }
            } else if (e.getSource() == btnBack) {
                dispose();
                new Menu().setVisible(true);
            }
        }

        private boolean isUsernameTaken(String username) {
            String query = "SELECT username FROM admins WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                try (ResultSet rs = stmt.executeQuery()) {
                    return rs.next();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return true;
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
