package com.libr.controller;

import com.libr.dao.StudentDoa;
import com.libr.model.Students;

import java.sql.*;
import java.util.Scanner;

public class StudentController {
    public static void insertStudent(Connection con)
    {
        System.out.println("****** Student Form ******");
        System.out.println("Enter your details below:");

        // Collect student details from user
        Scanner sc = new Scanner(System.in);
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

        System.out.print("Enter Branch: ");
        String branch = sc.nextLine();

        System.out.print("Enter new Password: ");
        String password = sc.nextLine();

        // Create a Students object with the provided data
        Students s1 = new Students(id,name,age,phoneNo,emailId,branch,password);

        // Initialize DAO and insert student into database
        StudentDoa sd = new StudentDoa(con);
        sd.insertStudent(s1);

        System.out.println("Student record has been successfully inserted.");
    }

    public static void loginStudent(Connection con) {
        System.out.println("***** Please Log in for library use ******");
        System.out.println("Enter your details below:");

        Scanner sc = new Scanner(System.in);
        System.out.println();
        int i = 0;
        while (i < 3) {
            System.out.print("Email Id: ");
            String studentEmail = sc.nextLine();

            System.out.print("Password: ");
            String studentPass = sc.nextLine();

            try {
                String query = "select * from student where EMAILID=? and PASSWORD=?";
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, studentEmail);
                pstmt.setString(2, studentPass);
                ResultSet rs = pstmt.executeQuery();
//            System.out.println(rs);

                if (rs.next()) {
                    Students st1 = new Students();
                    st1.setId(rs.getInt("ID"));
                    st1.setName(rs.getString("NAME"));
                    st1.setAge(rs.getInt("AGE"));
                    st1.setMobileNo(rs.getString("PHONENO"));
                    st1.setEmailId(rs.getString("EMAILID"));
                    st1.setDepartment(rs.getString("BRANCH"));
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
                                SearchBooks.checkIssuedBook(con,st1.getId(),'s');
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
