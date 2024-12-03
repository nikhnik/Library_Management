package com.libr.controller;

import com.libr.dao.TeacherDao;
import com.libr.model.Teachers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class TeacherController {
    public static void insertTeacher(Connection con)
    {
        System.out.println("****** Teacher Form ******");
        System.out.println("Enter your details below:");

        Scanner sc = new Scanner(System.in);

        // Collect teacher details
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Phone No: ");
        String phoneNo = sc.nextLine();

        System.out.print("Enter Email Id: ");
        String emailId = sc.nextLine();

        System.out.print("Enter Department: ");
        String department = sc.nextLine();

        System.out.print("Enter new Password: ");
        String password = sc.nextLine();

        // Create a new teacher object and pass it to DAO for insertion
        Teachers t1 = new Teachers(id,name,age,phoneNo,emailId,department,password);
        TeacherDao td = new TeacherDao(con);
        td.insertTeacher(t1);

        System.out.println("Teacher record has been successfully inserted.");
    }

    public static void loginTeacher(Connection con)
    {
        System.out.println("***** Please Log in for library use ******");
        System.out.println("Enter your details below:");

        Scanner sc = new Scanner(System.in);
        System.out.println();
        int i = 0;
        while (i < 3) {
            System.out.print("Email Id: ");
            String teacherEmail = sc.nextLine();

            System.out.print("Password: ");
            String teacherPass = sc.nextLine();

            try {
                String query = "select * from teacher where EMAILID=? and PASSWORD=?";
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, teacherEmail);
                pstmt.setString(2, teacherPass);
                ResultSet rs = pstmt.executeQuery();
//            System.out.println(rs);

                if (rs.next()) {
                    Teachers td1 = new Teachers();
                    td1.setId(rs.getInt("ID"));
                    td1.setName(rs.getString("NAME"));
                    td1.setAge(rs.getInt("AGE"));
                    td1.setMobileNo(rs.getString("PHONENO"));
                    td1.setEmailId(rs.getString("EMAILID"));
                    td1.setDepartment(rs.getString("BRANCH"));
                    System.out.println("You have successfully logged in.");
                    while (true) {
                        System.out.println();
                        System.out.println("Would you like to search for a book or view the ones you've issued?");
                        System.out.println("Press 1 for search book");
                        System.out.println("Press 2 for view issued books");
                        System.out.println("Press 3 for exit");
                        int search = sc.nextInt();
                        switch (search) {
                            case 1:
                                SearchBooks.readBook(con);
                                break;
                            case 2:
                                SearchBooks.checkIssuedBook(con,td1.getId(),'t');
                                break;
                            case 3:
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
}
