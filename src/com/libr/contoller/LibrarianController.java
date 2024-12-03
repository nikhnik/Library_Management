package com.libr.controller;

import com.libr.model.Students;
import com.libr.model.Teachers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class LibrarianController {
    public static void loginLibrarian(Connection con)
    {
        boolean result = false;
        System.out.println("Enter your details below:");
        Scanner sc = new Scanner(System.in);
        System.out.println();
        Teachers td1 = new Teachers();
        int i = 0;
        while (i < 3) {
            System.out.print("Email Id: ");
            String teacherEmail = sc.nextLine();

            System.out.print("Password: ");
            String teacherPass = sc.nextLine();

            try {
                String query = "select * from teacher where EMAILID=? and PASSWORD=? and BRANCH = 'Library'";
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, teacherEmail);
                pstmt.setString(2, teacherPass);
                ResultSet rs = pstmt.executeQuery();
//            System.out.println(rs);

                if (rs.next()) {
                    td1.setId(rs.getInt("ID"));
                    td1.setName(rs.getString("NAME"));
                    td1.setAge(rs.getInt("AGE"));
                    td1.setMobileNo(rs.getString("PHONENO"));
                    td1.setEmailId(rs.getString("EMAILID"));
                    td1.setDepartment(rs.getString("BRANCH"));
                    System.out.println("You have successfully logged in.");

                    while(true) {
                        System.out.println();
                        System.out.println("Press 1 to manage books (Add, View, Update, Delete).");
                        System.out.println("Press 2 for book issue");
                        System.out.println("Press 3 for submit book");
                        System.out.println("Press 4 for exit");

                        int choice = sc.nextInt();
                        switch (choice) {
                            case 1:
                                bookCrud(con);
                                break;
                            case 2:
                                issueBook(con, td1);
                                break;
                            case 3:
                                //submit book
                                submitBook(con,td1);
                                break;
                            case 4:
                                return;
                        }
                    }
                } else {
                    int attempts = 2-i;
                    if(i==2)
                    {
                        System.out.println("Your profile is locked for 4 hours. Please try again after 4 hours.");
                    }
                    else {
                        System.out.println("Incorrect details. Please try again.");
                        System.out.println("You have "+attempts+" attempt(s) left out of 3");
                    }
                    i++;
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }

    public static void bookCrud(Connection con)
    {
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            System.out.println("Press 1 for Add Books");
            System.out.println("Press 2 for Update Book");
            System.out.println("Press 3 for View Books");
            System.out.println("Press 4 for Exit");

            System.out.println("Enter your choice");
            int ch = sc.nextInt();
            switch (ch){
                case 1:
                    // add books
                    BookController.addBook(con);
                    break;
                case 2:
                    //update books
                    BookController.updateBook(con);
                    break;
                case 3:
                    //view books
                    SearchBooks.readBook(con);
                    break;
                case 4:
                    return;
            }
        }
    }

    public static void issueBook(Connection con,Teachers td){
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Are you teacher or student");
        String designation = sc.next();
        char des = Character.toLowerCase(designation.charAt(0));
        if(des == 's') {
            Students students = new Students();
            BookIssueController.bookIssue(con, td, students, des);
        } else if (des == 't') {
            Teachers teachers = new Teachers();
            BookIssueController.bookIssue(con, td, teachers, des);
        }
    }

    public static void submitBook(Connection con,Teachers td)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Are you teacher or student");
        String designation = sc.next();
        char des = Character.toLowerCase(designation.charAt(0));
        if(des == 's'){
            Students students = new Students();
            BookSubmitController.bookSubmit(con,td, students, des);
        }
        else if(des == 't'){
            Teachers teachers = new Teachers();
            BookSubmitController.bookSubmit(con,td, teachers, des);
        }


    }
}
