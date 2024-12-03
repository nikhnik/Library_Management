package com.libr.controller;

import com.libr.dao.BooksDao;
import com.libr.model.Books;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class BookController {

    public static void addBook(Connection con)
    {
        System.out.println("Please add book");

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Book Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Book Author: ");
        String author = sc.nextLine();

        System.out.print("Enter Book Publisher: ");
        String publisher = sc.nextLine();

        System.out.print("Enter Publish Year: ");
        int publishYear = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Book Genre: ");
        String genre = sc.nextLine();

        System.out.print("Enter Language: ");
        String language = sc.nextLine();

        System.out.print("Enter book available: ");
        int available = sc.nextInt();

        Books b1 = new Books(id,title,author,publisher,publishYear,genre,language,available);
        BooksDao bd = new BooksDao(con);
        bd.insertBooks(b1);

        System.out.println("Book has been successfully inserted.");
    }

    public static void updateBook(Connection con)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("You can delete book and update quantity of books");
        System.out.println();
        System.out.println("Press 1 for delete book");
        System.out.println("Press 2 for update quantity of book");
        int ch = sc.nextInt();

        switch (ch){
            case 1:
                //delete the book
                System.out.println("Enter the Book id to delete the book");
                int id = sc.nextInt();
                try{
                    String query = "Delete from Book where ID = ?";
                    PreparedStatement stmt = con.prepareStatement(query);
                    stmt.setInt(1,id);
                    int rowsAffected = stmt.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Row deleted successfully.");
                    } else {
                        System.out.println("No rows found with the given condition.");
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                break;
            case 2:
                //Increament the book quantity
                System.out.println("Enter the Book id to change the quantity");
                int id1 = sc.nextInt();
                System.out.println("Enter the Book quantity");
                int quantity = sc.nextInt();
                try{
                    String updateQuery = "Update book set AVAILABILITY = ? where ID = ?";
                    PreparedStatement stmt = con.prepareStatement(updateQuery);
                    stmt.setInt(1,quantity);
                    stmt.setInt(2,id1);

                    int rowsAffected = stmt.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Row updated successfully.");
                    } else {
                        System.out.println("No row found with the specified id.");
                    }

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                break;
        }
    }
}
