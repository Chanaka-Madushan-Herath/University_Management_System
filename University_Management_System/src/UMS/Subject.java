package UMS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Subject {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private int subject_id;
    private int person_id;
    private String subject_code;
    private String subject_name;

    public Subject() {
    }

    public void showAllSubjects() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection(Main.url, Main.user, Main.pass);
            this.stmt = this.conn.createStatement();
            String sql = "SELECT * FROM subject";
            this.rs = this.stmt.executeQuery(sql);

            subjects_collection();

            this.rs.close();
            this.stmt.close();
            this.conn.close();
        } catch (ClassNotFoundException var2) {
            var2.printStackTrace();
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

    }

    public void selectSubjects(String sub_combination, String username, String role1, String role2) {
        String[] subject_ids = sub_combination.split(",");

        for(int i = 0; i < subject_ids.length; ++i) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                this.conn = DriverManager.getConnection(Main.url, Main.user, Main.pass);
                this.stmt = this.conn.createStatement();
                String sql = "SELECT subject_id, subject_code, subject_name FROM subject WHERE subject_id=" + subject_ids[i];
                this.rs = this.stmt.executeQuery(sql);

                subjects_collection();

                sql = "SELECT id FROM " + role1 + " WHERE username='" + username + "'";

                for(this.rs = this.stmt.executeQuery(sql); this.rs.next(); this.person_id = this.rs.getInt("id")) {
                }

                sql = "INSERT INTO " + role2 + " VALUES (" + this.person_id + "," + subject_ids[i] + ")";
                this.stmt.executeUpdate(sql);
                this.rs.close();
                this.stmt.close();
                this.conn.close();
            } catch (ClassNotFoundException var8) {
                var8.printStackTrace();
            } catch (SQLException var9) {
                var9.printStackTrace();
            }
        }

    }

    private void subjects_collection() throws SQLException {
        while(this.rs.next()) {
            this.subject_id = this.rs.getInt("subject_id");
            this.subject_code = this.rs.getString("subject_code");
            this.subject_name = this.rs.getString("subject_name");
            System.out.println("    [" + this.subject_id + "] " + this.subject_code + " " + this.subject_name);
        }
    }

    public void showSelectedSubjects(int id, String role1, String role2) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection(Main.url, Main.user, Main.pass);
            this.stmt = this.conn.createStatement();
            String sql = "SELECT A.subject_id,A.subject_code,A.subject_name FROM subject A," + role1 + " B," + role2 + " C Where B.id=" + id + " AND A.subject_id=C.subject_subject_id AND B.id=C." + role1 + "_" + role1 + "_id";
            this.rs = this.stmt.executeQuery(sql);

            subjects_collection();
        } catch (ClassNotFoundException var5) {
            var5.printStackTrace();
        } catch (SQLException var6) {
            var6.printStackTrace();
        }

    }
}
