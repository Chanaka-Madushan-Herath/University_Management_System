package Interfaces;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Profile extends JFrame {

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private JPanel rootpanel9;
    private JTable ShowSubject;
    private JButton exitButton;
    private JButton AddSubjectBt;
    private JLabel Name1;
    private JLabel Age1;
    private JButton deleteSubjectSButton;

    public Profile(String Name, int age, String role1, String role2, Object id, String User_name) throws ClassNotFoundException, SQLException {
        setTitle("University Management System");
        add(rootpanel9);
        setUndecorated(true);
        setSize(600,700);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        Name1.setText("Name: "+Name);
        String age1=Integer.toString(age);
        Age1.setText("Age: "+age1+" ");



        DefaultTableModel model = new DefaultTableModel() {
            public boolean isCellEditable(int row, int col) {
                switch (col) {
                    case 0:
                        return false;
                    case 1:
                        return false;
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
                    default:
                        return String.class;
                }

            }
        };
        ShowSubject.setModel(model);


        model.addColumn("Subject Code");
        model.addColumn("Subject Name");

        Class.forName("com.mysql.jdbc.Driver");
        this.conn = DriverManager.getConnection(Main.url, Main.user, Main.pass);
        stmt = conn.createStatement();
        this.stmt = this.conn.createStatement();
        String sql = "SELECT A.subject_id,A.subject_code,A.subject_name FROM subject A," + role1 + " B," + role2 + " C Where B.id=" + id + " AND A.subject_id=C.subject_subject_id AND B.id=C." + role1 + "_" + role1 + "_id";
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
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Start_page start_page=new Start_page();
                start_page.setVisible(true);
            }
        });
        AddSubjectBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Interfaces.Subject subject= null;
                try {
                    subject = new Subject(Name,age,role1,role2,id,User_name);
                } catch (ClassNotFoundException | SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex);
                }
                subject.setVisible(true);
            }
        });
        deleteSubjectSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Del_subject del_subject= null;
                try {
                    del_subject = new Del_subject(Name,age,role1,role2,id,User_name);
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                del_subject.setVisible(true);
            }
        });
    }

}