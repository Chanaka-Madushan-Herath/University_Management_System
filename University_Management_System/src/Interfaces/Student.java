package Interfaces;

import javax.swing.*;
import java.sql.SQLException;

public class Student extends Person {
    public Student() {
    }

    public void register_student(String name,int age ,String username,String password) {
        super.AddPersonData("student", "student_subject",name,age,username,password);
    }

    public void login_student(String Username,String Password) {
        try {
            super.LoginPerson("student", "student_subject",Username,Password);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null,e,"Error",0);
        }
    }
}
