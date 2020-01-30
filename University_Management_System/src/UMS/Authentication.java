package UMS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Authentication {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private String user_name;
    private String password;
    private String str_password;
    private int id;

    public Authentication() {
    }

    public String getStr_password() {
        return this.str_password;
    }

    public void setStr_password(String str_password) {
        this.str_password = str_password;
    }

    public String getUser_name() {
        return this.user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int compareUsernamePassword(String username, String password, String role) {
        Person person = new Person();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection(Main.url, Main.user, Main.pass);
            this.stmt = this.conn.createStatement();
            String sql = "SELECT id,name,password FROM " + role + " WHERE username='" + username + "'";
            this.rs = this.stmt.executeQuery(sql);

            while(this.rs.next()) {
                this.id = this.rs.getInt("id");
                person.setName(this.rs.getString("name"));
                this.setStr_password(this.rs.getString("password"));
            }

            this.rs.close();
            this.stmt.close();
            this.conn.close();
        } catch (ClassNotFoundException var6) {
            var6.printStackTrace();
        } catch (SQLException var7) {
            var7.printStackTrace();
        }

        if (password.equals(this.getStr_password())) {
            System.out.println("Welcome " + person.getName() +"...");
            System.out.println("Your Selected Subjects Are,");
            return this.id;
        } else {
            return -1;
        }
    }
}