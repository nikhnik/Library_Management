package com.libr.controller;

import com.libr.model.Students;
import com.libr.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SearchBooks {

    public static void readBook(Connection con)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("You can now search for books");
        System.out.print("Enter the first 3 letters of the book genre: ");
        String searchQuery = sc.next();
        searchQuery = searchQuery+"%";
        try{
            String query = "Select * from book where GENRE LIKE ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1,searchQuery);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                System.out.println("-------------------------------------");
                System.out.println("Book Id: " + rs.getInt("ID") + "\nBook Title: " + rs.getString("TITLE") +
                        "\nBook Author: " + rs.getString("AUTHOR") + "\nBook PUBLISHER: " + rs.getString("PUBLISHER") +
                        "\nBook Publish Year: " + rs.getString("PUBLISHYEAR") + "\nBook Language: " + rs.getString("LANGUAGE") +
                        "\nBook Availability: " + rs.getInt("AVAILABILITY"));
                while (rs.next()) {
                    System.out.println("-------------------------------------");
                    System.out.println("Book Id: " + rs.getInt("ID") + "\nBook Title: " + rs.getString("TITLE") +
                            "\nBook Author: " + rs.getString("AUTHOR") + "\nBook PUBLISHER: " + rs.getString("PUBLISHER") +
                            "\nBook Publish Year: " + rs.getString("PUBLISHYEAR") + "\nBook Language: " + rs.getString("LANGUAGE") +
                            "\nBook Availability: " + rs.getInt("AVAILABILITY"));

                }
                System.out.println("-------------------------------------");
            }
            else{
                System.out.println("Please enter correct keyword");
                return;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();;
        }
    }

    public static boolean checkIssuedBook(Connection con, int userId,char des)
    {
        String selectQuery = "";
        String updateQuery = "";
        if(des == 's')
        {
            selectQuery = "Select * from studentbookissue where StudentId = ?";
            updateQuery = "update studentbookissue set TotalFine = ? where BookId = ? and StudentId = ?";
        }
        else if(des == 't'){
            selectQuery = "Select * from teacherbookissue where TeacherId = ?";
            updateQuery = "update teacherbookissue set TotalFine = ? where BookId = ? and TeacherId = ?";
        }
        System.out.println();
        try {
            PreparedStatement selectStmt = con.prepareStatement(selectQuery,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY );
            selectStmt.setInt(1,userId);
            ResultSet rs = selectStmt.executeQuery();
            System.out.println("Issued books");
            System.out.println("----------------------");
            if (!rs.next()) {
                System.out.println("No issued books found for the given user.");
                return false;
            }
            rs.beforeFirst();
            while(rs.next())
            {
                String issueDate = rs.getString("IssueDate");
                int bookId = rs.getInt("BookId");
                long issueTime = unixTimeConverter(issueDate);
                String currentDate = currentDateTime();
                long currentTime = unixTimeConverter(currentDate);
                int totalDays = (int) ((currentTime-issueTime)/86400);
                int totalFine = 0;
                if(totalDays>12)
                {
                    int fineDays = totalDays-12;
                    totalFine = fineDays*10;
                }
                updateFine(con,totalFine,bookId,userId,updateQuery);

                System.out.println("Book Id:- "+bookId);
                System.out.println("Book Name:-"+searchBookName(con,bookId));
                System.out.println("Issue Date:- "+issueDate);
                System.out.println("Total Fine:- "+totalFine);
                if(totalFine>0)
                {
                    System.out.println("Please return the book promptly to avoid a daily fine of ₹10 as the due date has passed.");
                }
                else{
                    System.out.println("Return your book by "+returnDate(issueTime)+" to avoid a fine of ₹10 per day.");
                }
                System.out.println("----------------------");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return true;
    }

    public static long unixTimeConverter(String issueDate)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(issueDate,formatter);

        return dateTime.toEpochSecond(ZoneOffset.UTC);
    }
    public static String currentDateTime()
    {
        LocalDateTime now  = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
    public static void updateFine(Connection con,int totalFine,int bookId,int userId,String updateQuery)
    {
        try{
            PreparedStatement stmt = con.prepareStatement(updateQuery);
            stmt.setInt(1,totalFine);
            stmt.setInt(2,bookId);
            stmt.setInt(3,userId);

            stmt.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static String returnDate(long issueTime)
    {
        long returnUnixTime = issueTime+1036800;
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(returnUnixTime), ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }
    public static String searchBookName(Connection con,int bookId)
    {
        String query = "Select TITLE from book where ID = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1,bookId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            {
                return rs.getString("TITLE");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "";
    }
}
