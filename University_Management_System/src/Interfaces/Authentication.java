package Interfaces;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Authentication {
    private Connection conn;
    private Statement stmt;
    ResultSet rs;
    private String user_name;
    private  String password;
    private String str_password;
    private int id;
    private String Name;
    private int Age;

    public Authentication() {
    }
    public String getName() { return Name; }

    public void setName(String name) { Name = name; }

    public int getAge() { return Age; }

    public void setAge(int age) { Age = age; }

    public String getStr_password() {
        return str_password;
    }

    public void setStr_password(String str_password) {
        this.str_password = str_password;
    }

    public int getId() {
        return id;
    }

    public String getUser_name() {

        return user_name;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int compareUsernamePassword(String username, String password, String role) throws SQLException, ClassNotFoundException {
        Person person = new Person();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection(Main.url, Main.user, Main.pass);
            this.stmt = this.conn.createStatement();
            String sql = "SELECT id,name,age,password FROM " + role + " WHERE username='" + username + "'";
            this.rs = this.stmt.executeQuery(sql);

            while(this.rs.next()) {
                this.id = this.rs.getInt("id");
                person.setName(this.rs.getString("name"));
                this.setStr_password(this.rs.getString("password"));
            }
            this.rs.close();
            this.stmt.close();
            this.conn.close();
        } catch (ClassNotFoundException | SQLException var6) {
            JOptionPane.showMessageDialog(null,var6,"Error",0);
        }

        if (password.equals(this.getStr_password())) {
            return this.id;
        } else {
            return -1;
        }
    }
}

