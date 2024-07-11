package osp.pkgfinal.group.pkg1;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Receipt extends JFrame {
    private JTextArea receiptArea;
    private JScrollPane scrollPane;

    public Receipt(String name, String address, String contact, String paymentMethod, List<String> items, double totalPrice) {
        setTitle("Receipt");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        receiptArea = new JTextArea();
        receiptArea.setEditable(false);
        scrollPane = new JScrollPane(receiptArea);

        StringBuilder receiptText = new StringBuilder();
        receiptText.append("RECEIPT\n");
        receiptText.append("===============\n");
        receiptText.append("Customer Name: ").append(name).append("\n");
        receiptText.append("Address: ").append(address).append("\n");
        receiptText.append("Contact No: ").append(contact).append("\n");
        receiptText.append("Payment Method: ").append(paymentMethod).append("\n");
        receiptText.append("\nItems:\n");

        for (String item : items) {
            receiptText.append(item).append("\n");
        }

        receiptText.append("\nTotal Price: $").append(totalPrice).append("\n");
        receiptText.append("===============\n");

        receiptArea.setText(receiptText.toString());
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
