package osp.pkgfinal.group.pkg1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkCateg extends JFrame implements ActionListener {
    private JButton btnHome, btnPersonal, btnWork, btnPayment;
    private JLabel lblTitle;
    private List<ItemPanel> pnlItems;
    private Connection conn;
    private List<Item> lstItems;
    private double totalPrice;

    private static final String URL = "jdbc:mysql://localhost:3306/osp";
    private static final String USER = "lance";
    private static final String PASSWORD = "12345";
    private static final String IMAGE_DIR = "images"; // Directory where images are stored

    public WorkCateg() {
        setTitle("WORK ITEMS CATEGORY");
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(255, 204, 153)); // Setting background color similar to PersonalCateg

        lblTitle = new JLabel("WORK CATEGORY");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(280, 10, 250, 30);
        lblTitle.setForeground(new Color(102, 51, 0)); // Darker shade for better contrast
        add(lblTitle);

        btnPayment = new JButton("PAYMENT");
        btnPayment.setBounds(660, 10, 110, 30);
        btnPayment.setFont(new Font("Arial", Font.BOLD, 14));
        btnPayment.setBackground(new Color(255, 153, 0)); // Orange background color
        btnPayment.setForeground(Color.WHITE); // White text color
        btnPayment.addActionListener(this);
        add(btnPayment);

        btnHome = new JButton("HOME");
        btnPersonal = new JButton("PERSONAL");
        btnWork = new JButton("WORK");

        Dimension buttonSize = new Dimension(100, 30);
        btnHome.setBounds(150, 60, 100, 30);
        btnHome.setFont(new Font("Arial", Font.BOLD, 14));
        btnHome.setBackground(new Color(255, 153, 0)); // Orange background color
        btnHome.setForeground(Color.WHITE); // White text color
        btnHome.addActionListener(this);
        add(btnHome);

        btnPersonal.setBounds(300, 60, 110, 30);
        btnPersonal.setFont(new Font("Arial", Font.BOLD, 14));
        btnPersonal.setBackground(new Color(255, 153, 0)); // Orange background color
        btnPersonal.setForeground(Color.WHITE); // White text color
        btnPersonal.addActionListener(this);
        add(btnPersonal);

        btnWork.setBounds(450, 60, 100, 30);
        btnWork.setFont(new Font("Arial", Font.BOLD, 14));
        btnWork.setBackground(new Color(255, 153, 0)); // Orange background color
        btnWork.setForeground(Color.WHITE); // White text color
        btnWork.addActionListener(this);
        add(btnWork);

        pnlItems = new ArrayList<>();
        lstItems = new ArrayList<>();
        totalPrice = 0.0;

        establishConnection();

        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new GridLayout(0, 1, 0, 10)); // Adjusted to include vertical gap of 10 pixels
        itemsPanel.setBounds(50, 100, 660, 400); // Adjusted position and size

        // Add items with image file names
        addItem(itemsPanel, "ACER Aspire 3", 1000.00, "acer.jpg");
        addItem(itemsPanel, "AKARI Office Lamp", 150.00, "lamp.jfif");
        addItem(itemsPanel, "Chair", 30.00, "chair.png");

        add(itemsPanel);

        setVisible(true);
    }

    private void establishConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connection successful");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addItem(JPanel panel, String itemName, double itemPrice, String imageFileName) {
        ItemPanel itemPanel = new ItemPanel(new Item(itemName, itemPrice, imageFileName));
        pnlItems.add(itemPanel);
        panel.add(itemPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnHome) {
            dispose();
            new HomeCateg().setVisible(true);
        } else if (e.getSource() == btnPersonal) {
            dispose();
            new PersonalCateg().setVisible(true);
        } else if (e.getSource() == btnWork) {
            dispose();
            new WorkCateg().setVisible(true);
        } else if (e.getSource() == btnPayment) {
            new OSPPayment(getCartItems(), totalPrice).setVisible(true);
        }
    }

    private String getCartItems() {
        StringBuilder items = new StringBuilder();
        for (Item item : lstItems) {
            items.append(item.getName()).append(",").append(item.getPrice()).append("\n");
        }
        return items.toString();
    }

    private void updateCart() {
        // Implement if needed
    }

    private class ItemPanel extends JPanel implements ActionListener {
        private JLabel lblItemName;
        private JLabel lblItemPrice;
        private JButton btnAddToCart;
        private JButton btnSeeImage;
        private Item item;

        public ItemPanel(Item item) {
            this.item = item;

            setLayout(new BorderLayout());

            JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Panel for item name and price
            lblItemName = new JLabel(item.getName());
            lblItemPrice = new JLabel("$" + item.getPrice());
            textPanel.add(lblItemName);
            textPanel.add(lblItemPrice);

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Panel for buttons
            btnAddToCart = new JButton("Add to Cart");
            btnSeeImage = new JButton("See Product Image"); // Initialize button for viewing product image

            // Apply unified button style
            btnAddToCart.setFont(new Font("Arial", Font.BOLD, 14));
            btnAddToCart.setBackground(new Color(255, 153, 0)); // Orange background color
            btnAddToCart.setForeground(Color.WHITE); // White text color
            btnAddToCart.addActionListener(this);

            btnSeeImage.setFont(new Font("Arial", Font.PLAIN, 12)); // Adjusted font size for image button
            btnSeeImage.setBackground(Color.LIGHT_GRAY); // Light gray background for contrast
            btnSeeImage.addActionListener(this);

            buttonPanel.add(btnAddToCart);
            buttonPanel.add(btnSeeImage);

            add(textPanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.EAST);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnAddToCart) {
                addItemToCart(lblItemName.getText(), Double.parseDouble(lblItemPrice.getText().substring(1)));
            } else if (e.getSource() == btnSeeImage) {
                showProductImage(item.getImageFileName());
            }
        }

        private void addItemToCart(String itemName, double itemPrice) {
            lstItems.add(new Item(itemName, itemPrice, item.getImageFileName()));
            totalPrice += itemPrice;
            updateCart();

            try (PreparedStatement insertItemStmt = conn.prepareStatement("INSERT INTO items (name, price) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                insertItemStmt.setString(1, itemName);
                insertItemStmt.setDouble(2, itemPrice);
                insertItemStmt.executeUpdate();

                try (ResultSet generatedKeys = insertItemStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int itemId = generatedKeys.getInt(1);
                        try (PreparedStatement insertPaymentStmt = conn.prepareStatement("INSERT INTO payments (item_id, customer_name, customer_address, customer_phone, payment_status) VALUES (?, ?, ?, ?, ?)")) {
                            insertPaymentStmt.setInt(1, itemId);
                            insertPaymentStmt.setString(2, "Customer Name");
                            insertPaymentStmt.setString(3, "Customer Address");
                            insertPaymentStmt.setString(4, "Customer Phone");
                            insertPaymentStmt.setString(5, "Pending");
                            insertPaymentStmt.executeUpdate();
                        }
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(WorkCateg.this, "Failed to add item to cart: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void showProductImage(String imageFileName) {
            JDialog imageDialog = new JDialog(WorkCateg.this, "Product Image", true);
            imageDialog.setLayout(new BorderLayout());
            
            File imageFile = new File(IMAGE_DIR, imageFileName);
            if (imageFile.exists()) {
                ImageIcon originalIcon = new ImageIcon(imageFile.getPath());
                Image originalImage = originalIcon.getImage();

                // Define the maximum size of the dialog
                int maxWidth = 400;
                int maxHeight = 400;

                // Get original dimensions
                int originalWidth = originalIcon.getIconWidth();
                int originalHeight = originalIcon.getIconHeight();

                // Calculate the scaling factor to fit within the max dimensions
                double widthRatio = (double) maxWidth / originalWidth;
                double heightRatio = (double) maxHeight / originalHeight;
                double scalingFactor = Math.min(widthRatio, heightRatio);

                // Calculate new dimensions
                int newWidth = (int) (originalWidth * scalingFactor);
                int newHeight = (int) (originalHeight * scalingFactor);

                // Resize the image
                Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);

                JLabel lblImage = new JLabel(scaledIcon);
                lblImage.setHorizontalAlignment(JLabel.CENTER);
                lblImage.setVerticalAlignment(JLabel.CENTER);
                imageDialog.add(lblImage, BorderLayout.CENTER);

                // Set the dialog size to fit the image plus some padding
                imageDialog.setSize(newWidth + 50, newHeight + 50);
            } else {
                JLabel lblImage = new JLabel("Image not found");
                lblImage.setHorizontalAlignment(JLabel.CENTER);
                lblImage.setVerticalAlignment(JLabel.CENTER);
                imageDialog.add(lblImage, BorderLayout.CENTER);
                imageDialog.setSize(300, 300); // Set a default size if image not found
            }

            JButton btnClose = new JButton("Close");
            btnClose.addActionListener(ae -> imageDialog.dispose());
            imageDialog.add(btnClose, BorderLayout.SOUTH);

            imageDialog.setLocationRelativeTo(WorkCateg.this);
            imageDialog.setVisible(true);
        }
    }

    private class Item {
        private String name;
        private double price;
        private String imageFileName;

        public Item(String name, double price, String imageFileName) {
            this.name = name;
            this.price = price;
            this.imageFileName = imageFileName;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public String getImageFileName() {
            return imageFileName;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WorkCateg());
    }
}
