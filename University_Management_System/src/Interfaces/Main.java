package Interfaces;

import javax.swing.*;

public class Main{
    static String url = "jdbc:mysql://localhost:3306/university_management_system";
    static String user = "root";
    static String pass = "";

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(() -> {
            Start_page Start_page =new Start_page();
            Start_page.setVisible(true);
        });
    }
}
