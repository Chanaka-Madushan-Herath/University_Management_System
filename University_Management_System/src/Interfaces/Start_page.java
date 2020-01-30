package Interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Start_page extends JFrame{
    private JPanel rootpanel;
    private JButton signUpButton;
    private JButton signInButton;
    private JButton exit;

    public Start_page(){

        setTitle("University Management System");
        add(rootpanel);
        setUndecorated(true);
        setSize(900,600);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sign_up Sign_up= null;
                try {
                    Sign_up = new Sign_up();
                } catch (ClassNotFoundException | SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex,"Error",0);
                }
                Sign_up.setVisible(true);
                dispose();
            }
        });
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sign_in Sign_in =new Sign_in();
                Sign_in.setVisible(true);
                dispose();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }




}
