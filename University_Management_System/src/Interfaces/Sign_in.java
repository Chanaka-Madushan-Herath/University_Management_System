package Interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sign_in extends JFrame{
    private JTextField User_name1;
    private JPasswordField Password1;
    private JButton signInButton;
    private JPanel rootpanel2;
    private JRadioButton lecturerRadioButton;
    private JRadioButton studentRadioButton;
    private JButton backButton;

    public Sign_in(){

        setTitle("University Management System");
        add(rootpanel2);
        setUndecorated(true);
        setSize(900,600);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        ButtonGroup bg=new ButtonGroup();
        bg.add(lecturerRadioButton);
        bg.add(studentRadioButton);

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student Student = new Student();
                Lecturer Lecturer = new Lecturer();
                if (User_name1.getText().isEmpty() || Password1.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all areas", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (lecturerRadioButton.isSelected()) {
                        Lecturer.login_lecturer(User_name1.getText(), Password1.getText());
                        dispose();
                    } else if (studentRadioButton.isSelected()) {
                        Student.login_student(User_name1.getText(), Password1.getText());
                        dispose();
                    }
                }
            }
        });

        User_name1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        Password1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        lecturerRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        studentRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Start_page start_page=new Start_page();
                start_page.setVisible(true);
            }
        });
    }

}
