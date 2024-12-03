package com.libr.controller;

import com.libr.model.Teachers;
import com.libr.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.Scanner;

public class BookSubmitController {

    public static void bookSubmit(Connection con, Teachers td, User user, char des)
    {
        String designation = "";
        String fineQuery = "";
        String deleteQuery = "";
        String insertSubmitQuery = "";
        if(des == 's')
        {
            designation = "Student";
        }
        else{
            designation = "Teacher";
        }

        Scanner sc  = new Scanner(System.in);
        System.out.println();
        System.out.printf("Enter the %s id to view issued books and check fine:- ",designation);
        int userId = sc.nextInt();
        boolean search = SearchBooks.checkIssuedBook(con,userId,des);
        if(!search){
            return;
        }
        System.out.println();
        System.out.print("Enter the book id for issue:- ");
        int bookId = sc.nextInt();

        if(des == 's'){
            fineQuery = "Select TotalFine from studentbookissue where StudentId = ? and BookId = ?";
            deleteQuery = "Delete from studentbookissue where StudentId = ? and BookId = ?";
            insertSubmitQuery = "Insert into studentbooksubmit (StudentId,BookId,FinePaid,LibrarianId) values(?,?,?,?)";
        }
        else {
            fineQuery = "Select TotalFine from teacherbookissue where TeacherId = ? and BookId = ?";
            deleteQuery = "Delete from teacherbookissue where TeacherId = ? and BookId = ?";
            insertSubmitQuery = "Insert into teacherbooksubmit (TeacherId,BookId,FinePaid,LibrarianId) values(?,?,?,?)";
        }
        try {
            PreparedStatement fineStmt = con.prepareStatement(fineQuery);
            fineStmt.setInt(1,userId);
            fineStmt.setInt(2,bookId);
            ResultSet fineRs = fineStmt.executeQuery();
            if(fineRs.next())
            {
                int totalFine = fineRs.getInt("TotalFine");
                if(totalFine>0)
                {
                    System.out.printf("%s has a fine on a book. The total fine is: %d",designation,totalFine);
                    System.out.println("Please take fine before submit book");
                    System.out.print("Fine paid yes or no:- ");
                    String answer = sc.next();
                    if(answer.equalsIgnoreCase("yes")){
                        System.out.println("You can now submit the book");
                    }
                    else{
                     return;
                    }
                }
                else{
                    System.out.println("No fine exists. You can submit the book.");
                }
                PreparedStatement deleteStmt = con.prepareStatement(deleteQuery);
                deleteStmt.setInt(1,userId);
                deleteStmt.setInt(2,bookId);
                int rowsAffected = deleteStmt.executeUpdate();
                if(rowsAffected>0)
                {
                    PreparedStatement insertStmt = con.prepareStatement(insertSubmitQuery);
                    insertStmt.setInt(1,userId);
                    insertStmt.setInt(2,bookId);
                    insertStmt.setInt(3,totalFine);
                    insertStmt.setInt(4,td.getId());
                    insertStmt.executeUpdate();
                    System.out.println("Book is submitted");
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }
}