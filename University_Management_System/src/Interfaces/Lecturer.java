package Interfaces;

import javax.swing.*;
import java.sql.SQLException;

public class Lecturer extends Person {
    public Lecturer() {
    }

    public void register_lecturer(String name,int age ,String username,String password) {
        super.AddPersonData("lecturer", "lecturer_subject",name,age,username,password);
    }

    public void login_lecturer(String Username,String Password) {
        try {
            super.LoginPerson("lecturer", "lecturer_subject",Username,Password);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null,e,"Error",0);
        }
    }
}

