package UMS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static java.lang.System.exit;

public class Person extends Authentication {
    private String name;
    private int age;
    private Connection conn;
    private Statement stmt;

    public Person() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void AddPersonData(String role1, String role2) {
        Scanner en = new Scanner(System.in);
        Person person = new Person();
        Subject subject = new Subject();
        System.out.print("Name:");
        person.setName(en.nextLine());
        System.out.print("Username:");
        person.setUser_name(en.nextLine());
        System.out.print("Password:");
        person.setPassword(en.nextLine());
        System.out.print("Age:");
        person.setAge(en.nextInt());
        System.out.print("Select the Subjects:\n");
        subject.showAllSubjects();
        en.nextLine();
        String sub_combination = en.nextLine();
        System.out.println("You have select following Courses successfully:");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection(Main.url, Main.user, Main.pass);
            this.stmt = this.conn.createStatement();
            String sql = "INSERT INTO " + role1 + " VALUES (NULL,'" + person.getName() + "'," + person.getAge() + ", '" + person.getUser_name() + "', '" + person.getPassword() + "')";
            this.stmt.executeUpdate(sql);
            this.stmt.close();
            this.conn.close();
        } catch (ClassNotFoundException var8) {
            var8.printStackTrace();
        } catch (SQLException var9) {
            var9.printStackTrace();
        }

        subject.selectSubjects(sub_combination, person.getUser_name(), role1, role2);
    }

    public void LoginPerson(String role1, String role2) {
        Subject subject = new Subject();
        Scanner en = new Scanner(System.in);
        System.out.print("Username:");
        super.setUser_name(en.nextLine());
        System.out.print("Password:");
        super.setPassword(en.nextLine());
        int getID = super.compareUsernamePassword(super.getUser_name(), super.getPassword(), role1);
        if (getID == -1) {
            System.out.println("\n Wrong Username or Password...");
        } else {
            subject.showSelectedSubjects(getID, role1, role2);
            int k=0;
            while(k!=1){
        System.out.println("What did you want\n[1] Exit, [2] Add new subject");
        int choice1=en.nextInt();
        k=choice1;
        if (choice1==1){
            System.out.println("Bye!!");
            exit(0);
        }else{
            subject.showAllSubjects();
            en.nextLine();
            String sub_combination = en.nextLine();
            System.out.println("You have add following Courses successfully:");
            subject.selectSubjects(sub_combination,super.getUser_name(), role1, role2);
            System.out.println("\nYou have select following Courses successfully:");
            subject.showSelectedSubjects(getID,role1,role2);
            }}

        }

    }
}
