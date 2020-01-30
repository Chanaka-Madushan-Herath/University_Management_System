package Interfaces;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Person extends Authentication {
    private Connection conn;
    private Statement stmt;


    public Person() {
    }
    public void LoginPerson(String role1, String role2, String Username,String Password) throws ClassNotFoundException, SQLException {
        super.setUser_name(Username);
        super.setPassword(Password);
        int getID = 0;
        try {
            getID = super.compareUsernamePassword(Username,Password, role1);
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,e,"Error",0);
        }
        if (getID == -1) {
            JOptionPane.showMessageDialog(null," Wrong Username or Password...","Error",0);
            Sign_in Sign_in=new Sign_in();
            Sign_in.setVisible(true);
        } else {
            Profile Profile = null;
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection(Main.url, Main.user, Main.pass);
            this.stmt = this.conn.createStatement();
            String sql = "SELECT id,name,age,password FROM " + role1 + " WHERE username='" + Username + "'";
            this.rs = this.stmt.executeQuery(sql);
            while(this.rs.next()) {
               setName(this.rs.getString("name"));
               setAge(this.rs.getInt("age"));
            }
            try {
                Profile = new Profile(getName(),getAge(),role1, role2,getId(), getUser_name());
            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(null,e,"Error",0);
            }
            if (Profile != null) {
                Profile.setVisible(true);
            }
        }
    }
    public void AddPersonData( String role1,String role2, String name, int age, String username, String password) {
        Person person = new Person();
        person.setName(name);
        person.setUser_name(username);
        person.setPassword(password);
        person.setAge(age);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection(Main.url, Main.user, Main.pass);
            this.stmt = this.conn.createStatement();
            String sql = "INSERT INTO " + role1 + " VALUES (NULL,'" + person.getName() + "'," + person.getAge() + ", '" + person.getUser_name() + "', '" + person.getPassword() + "')";
            this.stmt.executeUpdate(sql);
            this.stmt.close();
            this.conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,"User name  already exists please try again...","Error",0);
        }
    }
}

