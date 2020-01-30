package Interfaces;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Sign_up extends JFrame {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private JTextField name;
    private JTextField Age;
    private JTextField User_name;
    private JPasswordField password;
    private JButton signUpButton;
    private JPanel rootpanel1;
    private JRadioButton lecturerRadioButton;
    private JRadioButton studentRadioButton;
    private JTable subjectTable;
    private JButton backButton;

    public Sign_up() throws ClassNotFoundException, SQLException {
        Student Student = new Student();
        Lecturer Lecturer = new Lecturer();

        setTitle("University Management System");
        add(rootpanel1);
        setUndecorated(true);
        setSize(900,600);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        ButtonGroup bg=new ButtonGroup();
        bg.add(lecturerRadioButton);
        bg.add(studentRadioButton);
        DefaultTableModel model = new DefaultTableModel() {
            public boolean isCellEditable(int row, int col) {
                switch (col) {
                    case 0:
                        return false;
                    case 1:
                        return false;
                    case 2:
                        return true;
                    default:
                        return false;
                }
            }
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return Boolean.class;
                    default:
                        return String.class;
                }

            }
        };
        subjectTable.setModel(model);

        model.addColumn("Subject Code");
        model.addColumn("Subject Name");
        model.addColumn("Enroll");

        Class.forName("com.mysql.jdbc.Driver");
        this.conn = DriverManager.getConnection(Main.url, Main.user, Main.pass);
        this.stmt = this.conn.createStatement();
        String sql = "SELECT * FROM subject";
        this.rs = this.stmt.executeQuery(sql);
        while(this.rs.next()) {
            String subject_code = rs.getString("subject_code");
            String subject_name = rs.getString("subject_name");
            boolean selection=false;
            model.addRow(new Object[]{subject_code,subject_name,selection});
        }

        this.rs.close();
        this.stmt.close();
        this.conn.close();

        signUpButton.addActionListener(new ActionListener() {
            private Connection conn;

            @Override
            public void actionPerformed(ActionEvent e) {
                if(name.getText().isEmpty()|| Age.getText().isEmpty()|| User_name.getText().isEmpty()|| password.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Please fill all areas");
                }
                else {

                        int jml = Integer.parseInt(Age.getText());
                        if (studentRadioButton.isSelected()) {
                            Student.register_student(name.getText(), jml, User_name.getText(), password.getText());
                            Boolean checked = null;
                            String sub_code = null;
                            String sub_name = null;
                            int count = 0;
                            for (int i = 0; i < subjectTable.getRowCount(); i++) {
                                checked = Boolean.valueOf(subjectTable.getValueAt(i, 2).toString());


                                if (checked) {
                                    try {
                                        Class.forName("com.mysql.jdbc.Driver");
                                        this.conn = DriverManager.getConnection(Main.url, Main.user, Main.pass);
                                        stmt = conn.createStatement();
                                        String sql = "SELECT id FROM  student WHERE username='" + User_name.getText() + "'";
                                        rs = stmt.executeQuery(sql);
                                        int person_id = 0;
                                        while (rs.next()) {
                                            person_id = rs.getInt("id");
                                        }
                                        sql = "INSERT INTO " + "student_subject VALUES (" + person_id + "," + (i + 1) + ")";
                                        stmt.executeUpdate(sql);
                                        rs.close();
                                        stmt.close();
                                        conn.close();
                                    } catch (ClassNotFoundException | SQLException b) {
                                        JOptionPane.showMessageDialog(null, b);
                                    }
                                    count++;
                                }
                            }
                            JOptionPane.showMessageDialog(null, "Registration Successful...");
                        }
                        if (lecturerRadioButton.isSelected()) {
                            Lecturer.register_lecturer(name.getText(), jml, User_name.getText(), password.getText());
                            Boolean checked = null;
                            String sub_code = null;
                            String sub_name = null;
                            int count = 0;
                            for (int i = 0; i < subjectTable.getRowCount(); i++) {
                                checked = Boolean.valueOf(subjectTable.getValueAt(i, 2).toString());

                                if (checked) {
                                    try {
                                        Class.forName("com.mysql.jdbc.Driver");
                                        this.conn = DriverManager.getConnection(Main.url, Main.user, Main.pass);
                                        stmt = conn.createStatement();
                                        String sql = "SELECT id FROM  lecturer WHERE username='" + User_name.getText() + "'";
                                        rs = stmt.executeQuery(sql);
                                        int person_id = 0;
                                        while (rs.next()) {
                                            person_id = rs.getInt("id");
                                        }
                                        sql = "INSERT INTO " + "lecturer_subject VALUES (" + person_id + "," + (i + 1) + ")";
                                        stmt.executeUpdate(sql);
                                        rs.close();
                                        stmt.close();
                                        conn.close();
                                    } catch (ClassNotFoundException | SQLException b) {
                                        JOptionPane.showMessageDialog(null, "please try again...","Error",0);
                                    }
                                    count++;
                                }


                            }
                            JOptionPane.showMessageDialog(null, "Registration Successful...");
                        }
                        dispose();
                        Start_page start_page = new Start_page();
                        start_page.setVisible(true);

                }
            }
        });
        name.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        Age.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        User_name.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        password.addActionListener(new ActionListener() {
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