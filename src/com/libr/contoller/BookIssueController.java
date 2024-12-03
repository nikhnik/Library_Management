package com.libr.controller;

import com.libr.model.Students;
import com.libr.model.Teachers;
import com.libr.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class BookIssueController {

    public static void bookIssue(Connection con, Teachers td,User user,char des)
    {
        String designation = "";
        String tableName = "";
        String insertStudentQuery = "";
        String alreadyBook = "";
        if(des == 's')
        {
            designation = "Student";
            tableName = "studentbookissue";
            insertStudentQuery = "insert into "+tableName+" (StudentId,StudentName,StudentEmail,BookId,IssuerId,IssuerName) value(?,?,?,?,?,?)";
        }
        else if(des == 't')
        {
            designation = "Teacher";
            tableName = "teacherbookissue";
            insertStudentQuery = "insert into "+tableName+" (TeacherId,TeacherName,TeacherEmail,BookId,IssuerId,IssuerName) value(?,?,?,?,?,?)";
        }
        Scanner sc = new Scanner(System.in);
        System.out.printf("Please enter the %s ID to view the data:",designation);
        int id = sc.nextInt();
        String studentInfoQuery = "Select * from "+designation+" where ID = ?";
        try{
            PreparedStatement stmt = con.prepareStatement(studentInfoQuery);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){

                user.setId(rs.getInt("ID"));
                user.setName(rs.getString("NAME"));
                user.setAge(rs.getInt("AGE"));
                user.setMobileNo(rs.getString("PHONENO"));
                user.setEmailId(rs.getString("EMAILID"));
                user.setDepartment(rs.getString("BRANCH"));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.printf("%s Data:- ",designation);
        System.out.println();
        System.out.println("Id:- "+user.getId()+"\nName:- "+user.getName()+"\nAge:- "+user.getAge()+
                "\nPhone No.:- "+user.getMobileNo()+"\nEmail Id:- "+user.getEmailId()+
                "\nBranch:- "+user.getDepartment());

        System.out.println();
        System.out.println("Enter book id to issue");
        int bookId = sc.nextInt();
        String updateAvailability = "UPDATE book SET AVAILABILITY = AVAILABILITY - 1 WHERE ID = ? AND AVAILABILITY > 00000000";
        if(des == 's')
        {
            alreadyBook = "select * from studentbookissue where StudentId = ? and BookId = ?";
        }
        else{
            alreadyBook = "select * from teacherbookissue where TeacherId = ? and BookId = ?";
        }
        try {
            PreparedStatement checkStmt = con.prepareStatement(alreadyBook);
            checkStmt.setInt(1, user.getId());
            checkStmt.setInt(2, bookId);
            ResultSet checkRs = checkStmt.executeQuery();
            if (checkRs.next()) {
                System.out.printf("%s has already issued this book. We cannot provide another copy of the same book.",user.getName());
                System.out.println();
                return;
            } else {
                PreparedStatement updateStmt = con.prepareStatement(updateAvailability);
                updateStmt.setInt(1, bookId);
                int rowsUpdated = updateStmt.executeUpdate();
                if (rowsUpdated > 0) {
                    PreparedStatement ist = con.prepareStatement(insertStudentQuery);
                    ist.setInt(1, user.getId());
                    ist.setString(2, user.getName());
                    ist.setString(3, user.getEmailId());
                    ist.setInt(4, bookId);
                    ist.setInt(5, td.getId());
                    ist.setString(6, td.getName());
                    ist.executeUpdate();
                    System.out.println("Book issued to the " + user.getName());
                } else {
                    System.out.println("Failed to add the book to the database. The book might not be available.");
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
