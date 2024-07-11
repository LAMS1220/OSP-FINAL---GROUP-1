package osp.pkgfinal.group.pkg1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame implements ActionListener {
    private JButton btnHome, btnPersonal, btnWork, btnBack;
    private JLabel title, imglogo;

    public HomePage() {
        setTitle("Main Menu");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(255, 204, 153));

        imglogo = new JLabel();
        imglogo.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\Lanzdrei Salas\\Documents\\NetBeansProjects\\New Folder\\OSP-FINAL---GROUP-11\\OSP-FINAL---GROUP-1\\src\\osp\\pkgfinal\\group\\pkg1\\logo.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        add(imglogo);

        title = new JLabel("Welcome to Our Online Shopping Platform");
        title.setFont(new Font("Georgia", Font.BOLD, 20));
        title.setForeground(new Color(102, 51, 0));
        add(title);

        btnHome = new JButton("Home Items");
        btnHome.setFont(new Font("Arial", Font.BOLD, 14));
        btnHome.setBackground(Color.WHITE);
        btnHome.setForeground(new Color(102, 51, 0));
        add(btnHome);

        btnPersonal = new JButton("Personal Items");
        btnPersonal.setFont(new Font("Arial", Font.BOLD, 14));
        btnPersonal.setBackground(Color.WHITE);
        btnPersonal.setForeground(new Color(102, 51, 0));
        add(btnPersonal);

        btnWork = new JButton("Work Items");
        btnWork.setFont(new Font("Arial", Font.BOLD, 14));
        btnWork.setBackground(Color.WHITE);
        btnWork.setForeground(new Color(102, 51, 0));
        add(btnWork);

        btnBack = new JButton("Return");
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));
        btnBack.setBackground(Color.WHITE);
        btnBack.setForeground(new Color(102, 51, 0));
        add(btnBack);

        btnHome.addActionListener(this);
        btnPersonal.addActionListener(this);
        btnWork.addActionListener(this);
        btnBack.addActionListener(this);

        setComponentBounds();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                setComponentBounds();
            }
        });

        setVisible(true);
    }

    private void setComponentBounds() {
        int frameWidth = getWidth();
        int frameHeight = getHeight();

        imglogo.setBounds((frameWidth - 50) / 2 - 310, (frameHeight - 50) / 2 - 170, 50, 50);
        title.setBounds((frameWidth - 470) / 2, (frameHeight - 30) / 2 - 160, 500, 30);
        btnHome.setBounds((frameWidth - 120) / 2 - 200, (frameHeight - 40) / 2 - 70, 120, 40);
        btnPersonal.setBounds((frameWidth - 140) / 2, (frameHeight - 40) / 2 - 70, 140, 40);
        btnWork.setBounds((frameWidth - 120) / 2 + 200, (frameHeight - 40) / 2 - 70, 120, 40);
        btnBack.setBounds((frameWidth - 100) / 2 + 220, (frameHeight - 30) / 2 - 0, 100, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
        if (e.getSource() == btnHome) {
            HomeCateg h = new HomeCateg();
            h.setVisible(true);
        } else if (e.getSource() == btnPersonal) {
            PersonalCateg p = new PersonalCateg();
            p.setVisible(true);
        } else if (e.getSource() == btnWork) {
            WorkCateg of = new WorkCateg();
            of.setVisible(true);
        } else if (e.getSource() == btnBack) {
            Menu login = new Menu();
            login.setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomePage());
    }
}
