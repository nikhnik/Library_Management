import com.connection.Connect;
import com.libr.controller.LibrarianController;
import com.libr.controller.StudentController;
import com.libr.controller.TeacherController;

import java.sql.Connection;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //connection to the database
        Connection con = Connect.getConnection();
        Scanner sc = new Scanner(System.in);

        System.out.println("***** Are you Teacher or Student or Librarian? *****");
        System.out.println("***** Press 1 for Teacher *****");
        System.out.println("***** Press 2 for Student *****");
        System.out.println("***** Press 3 for Librarian *****");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                TeacherCollection(con);
                break;
            case 2:
                StudentCollection(con);
                break;
            case 3:
                LibrarianCollection(con);
                break;
            default:
                System.out.println("Please choose the correct option or try again later.");
        }
    }

    public static void LibrarianCollection(Connection con) {
        System.out.println("***** Welcome to the  Librarian Dashboard ******");
        System.out.println();
        System.out.println("***** Please Log in for library use ******");
        LibrarianController.loginLibrarian(con);
    }

    public static void TeacherCollection(Connection con) {
        Scanner sc = new Scanner(System.in);
        System.out.println("***** Welcome to the  Teacher Dashboard ******");
        System.out.println();
        System.out.println("***** Sign up or Login *****");
        System.out.println("***** Press 1 for Sign up *****");
        System.out.println("***** Press 2 for Login *****");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                TeacherController.insertTeacher(con);
//                System.out.println("Now you can login");
//                TeacherController.loginTeacher(con);
                break;
            case 2:
                TeacherController.loginTeacher(con);
                break;
            default:
                System.out.println("Please choose the correct option or try again later.");
        }
    }

    public static void StudentCollection(Connection con) {
        Scanner sc = new Scanner(System.in);
        System.out.println("***** Welcome to the Student dashboard ******");
        System.out.println();
        System.out.println("***** Sign up or Login *****");
        System.out.println("***** Press 1 for Sign up *****");
        System.out.println("***** Press 2 for Login *****");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                StudentController.insertStudent(con);
//                System.out.println("Now you can login");
//                StudentController.loginStudent(con);
                break;
            case 2:
                StudentController.loginStudent(con);
                break;
            default:
                System.out.println("Please choose the correct option or try again later.");
        }
    }
}