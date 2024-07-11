package osp.pkgfinal.group.pkg1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Dashboard extends JFrame implements ActionListener {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnDelete, btnEdit, btnBack;
    private Connection conn;
    private static final String URL = "jdbc:mysql://localhost:3306/osp";
    private static final String USER = "lance";
    private static final String PASSWORD = "12345";

    public Dashboard() {
        setTitle("Dashboard");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(255, 204, 153));

        initializeDBConnection();
        initializeUIComponents();
        loadDataFromDatabase();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                setComponentBounds();
            }
        });

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initializeDBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connection successful");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Database connection failed");
        }
    }

    private void initializeUIComponents() {
        String[] columnNames = {"Item Name", "Item Price", "Customer Name", "Customer Address", "Customer Phone", "Payment Status"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5; 
            }
        };
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Arial", Font.BOLD, 14));
        btnDelete.setBackground(Color.WHITE);
        btnDelete.setForeground(new Color(102, 51, 0));
        btnDelete.addActionListener(this);
        add(btnDelete);

        btnEdit = new JButton("Edit");
        btnEdit.setFont(new Font("Arial", Font.BOLD, 14));
        btnEdit.setBackground(Color.WHITE);
        btnEdit.setForeground(new Color(102, 51, 0));
        btnEdit.addActionListener(this);
        add(btnEdit);

        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));
        btnBack.setBackground(Color.WHITE);
        btnBack.setForeground(new Color(102, 51, 0));
        btnBack.addActionListener(this);
        add(btnBack);

        setComponentBounds();
    }

    private void setComponentBounds() {
        int frameWidth = getWidth();
        int frameHeight = getHeight();
        int buttonHeight = 30;
        int buttonWidth = 100;
        int buttonSpacing = 20;

        JScrollPane scrollPane = (JScrollPane) getContentPane().getComponent(0);
        scrollPane.setBounds(20, 20, frameWidth - 40, frameHeight - 100);

        int buttonPanelY = frameHeight - buttonHeight - 30;
        int buttonPanelX = (frameWidth - (3 * buttonWidth + 2 * buttonSpacing)) / 2;

        btnDelete.setBounds(buttonPanelX, buttonPanelY, buttonWidth, buttonHeight);
        btnEdit.setBounds(buttonPanelX + buttonWidth + buttonSpacing, buttonPanelY, buttonWidth, buttonHeight);
        btnBack.setBounds(buttonPanelX + 2 * (buttonWidth + buttonSpacing), buttonPanelY, buttonWidth, buttonHeight);
    }

    private void loadDataFromDatabase() {
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT items.name, items.price, payments.customer_name, payments.customer_address, payments.customer_phone, payments.payment_status " +
                     "FROM payments INNER JOIN items ON payments.item_id = items.id")) {

            while (rs.next()) {
                String itemName = rs.getString("name");
                double itemPrice = rs.getDouble("price");
                String customerName = rs.getString("customer_name");
                String customerAddress = rs.getString("customer_address");
                String customerPhone = rs.getString("customer_phone");
                String paymentStatus = rs.getString("payment_status");
                tableModel.addRow(new Object[]{itemName, itemPrice, customerName, customerAddress, customerPhone, paymentStatus});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnDelete) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                deleteRowFromDatabase(selectedRow);
                tableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to delete");
            }
        } else if (e.getSource() == btnEdit) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                editRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to edit");
            }
        } else if (e.getSource() == btnBack) {
            new AdminMenu();
            dispose();
        }
    }

    private void deleteRowFromDatabase(int row) {
        String customerName = (String) tableModel.getValueAt(row, 2);
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM payments WHERE customer_name = ?")) {
            stmt.setString(1, customerName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void editRow(int row) {
        String itemName = (String) tableModel.getValueAt(row, 0);
        double itemPrice = (Double) tableModel.getValueAt(row, 1);
        String customerName = (String) tableModel.getValueAt(row, 2);
        String customerAddress = (String) tableModel.getValueAt(row, 3);
        String customerPhone = (String) tableModel.getValueAt(row, 4);
        String paymentStatus = (String) tableModel.getValueAt(row, 5);

        JTextField txtItemName = new JTextField(itemName);
        JTextField txtItemPrice = new JTextField(String.valueOf(itemPrice));
        JTextField txtCustomerName = new JTextField(customerName);
        JTextField txtCustomerAddress = new JTextField(customerAddress);
        JTextField txtCustomerPhone = new JTextField(customerPhone);
        JComboBox<String> cmbPaymentStatus = new JComboBox<>(new String[]{"Pending", "Paid", "Cancelled"});
        cmbPaymentStatus.setSelectedItem(paymentStatus);

        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("Item Name:"));
        panel.add(txtItemName);
        panel.add(new JLabel("Item Price:"));
        panel.add(txtItemPrice);
        panel.add(new JLabel("Customer Name:"));
        panel.add(txtCustomerName);
        panel.add(new JLabel("Customer Address:"));
        panel.add(txtCustomerAddress);
        panel.add(new JLabel("Customer Phone:"));
        panel.add(txtCustomerPhone);
        panel.add(new JLabel("Payment Status:"));
        panel.add(cmbPaymentStatus);

        int result = JOptionPane.showConfirmDialog(null, panel, "Edit Row", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try (PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE payments SET customer_name = ?, customer_address = ?, customer_phone = ?, payment_status = ? " +
                            "WHERE customer_name = ?")) {
                stmt.setString(1, txtCustomerName.getText());
                stmt.setString(2, txtCustomerAddress.getText());
                stmt.setString(3, txtCustomerPhone.getText());
                stmt.setString(4, (String) cmbPaymentStatus.getSelectedItem());
                stmt.setString(5, customerName);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            tableModel.setValueAt(txtItemName.getText(), row, 0);
            tableModel.setValueAt(Double.parseDouble(txtItemPrice.getText()), row, 1);
            tableModel.setValueAt(txtCustomerName.getText(), row, 2);
            tableModel.setValueAt(txtCustomerAddress.getText(), row, 3);
            tableModel.setValueAt(txtCustomerPhone.getText(), row, 4);
            tableModel.setValueAt(cmbPaymentStatus.getSelectedItem(), row, 5);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Dashboard::new);
    }
}
