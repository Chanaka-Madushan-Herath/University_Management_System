package UMS;

import java.util.Scanner;

public class Main {
    static String url = "jdbc:mysql://localhost:3306/university_management_system";
    static String user = "root";
    static String pass = "";

    public Main() {
    }

    public static void main(String[] args) {
        Student student = new Student();
        Lecturer lecturer = new Lecturer();
        System.out.println("University Management System....");
        System.out.println("Select, [1] Sign up, [2] Sign in.");
        Scanner en = new Scanner(System.in);
        int choice = en.nextInt();
        if (choice == 1) {
            System.out.println("Select Your Registration Role [1] Lecturer, [2] Student.");
            choice = en.nextInt();
            if (choice == 1) {
                lecturer.register_lecturer();
                System.out.println("Registration Successful...");
            } else if (choice == 2) {
                student.register_student();
                System.out.println("Registration Successful...");
            } else {
                System.out.println("Wrong choice...");
                main(args);
            }
        } else if (choice == 2) {
            System.out.println("Select Your Login Role [1] Lecturer, [2] Student.");
            choice = en.nextInt();
            if (choice == 1 ) {
                lecturer.login_lecturer();
            }
            else if (choice ==2){
                student.login_student();
            }
            else{
                System.out.println("Wrong Choice...");
                main(args);
            }
        } else {
            System.out.println("Wrong Choice...");
            main(args);
        }

    }
}