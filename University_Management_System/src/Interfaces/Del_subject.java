package Interfaces;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Del_subject extends JFrame {

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private JPanel rootpanel99;
    private JTable table1;
    private JButton deleteThisSubjectButton;
    private JButton backButton;

    public Del_subject(String Name, int age, String role1, String role2, Object id, String User_name) throws ClassNotFoundException, SQLException {
        setTitle("University Management System");
        add(rootpanel99);
        setUndecorated(true);
        setSize(900,600);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);


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
        table1.setModel(model);


        model.addColumn("Subject Code");
        model.addColumn("Subject Name");
        model.addColumn("Enroll");

        Class.forName("com.mysql.jdbc.Driver");
        this.conn = DriverManager.getConnection(Main.url, Main.user, Main.pass);
        this.stmt = this.conn.createStatement();
        this.stmt = this.conn.createStatement();
        String sql = "select * from subject";
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
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Profile profile= null;
                try {
                    profile = new Profile(Name,age,role1,role2,id,User_name);
                } catch (ClassNotFoundException | SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex);
                }
                profile.setVisible(true);
            }
        });
        deleteThisSubjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean checked = null;
                String sub_code = null;
                String sub_name = null;
                int count = 0;
                for (int i = 0; i < table1.getRowCount(); i++) {
                    checked = Boolean.valueOf(table1.getValueAt(i, 2).toString());


                    if (checked) {
                        try {
                            Class.forName("com.mysql.jdbc.Driver");
                            conn = DriverManager.getConnection(Main.url, Main.user, Main.pass);
                            stmt = conn.createStatement();
                            String sql = "SELECT id FROM  " + role1 + " WHERE username='" + User_name + "'";
                            rs = stmt.executeQuery(sql);
                            int id = 0;
                            while (rs.next()) {
                                id = rs.getInt("id");
                            }
                            sql= "DELETE FROM `"+role2+"` WHERE `"+role2+"`.`"+role1+"_"+role1+"_id` ="+id+" AND `"+role2+"`.`subject_subject_id` = " +(i + 1) ;
                            stmt.executeUpdate(sql);
                            rs.close();
                            stmt.close();
                            conn.close();
                        } catch (ClassNotFoundException | SQLException b) {
                        }
                        count++;
                    }


                }
                dispose();
                JOptionPane.showMessageDialog(null, "Successfully update");
                Profile profile = null;
                try {
                    profile = new Profile(Name, age, role1, role2, id, User_name);
                } catch (ClassNotFoundException | SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
                profile.setVisible(true);
                        }
                    });
    }
}
