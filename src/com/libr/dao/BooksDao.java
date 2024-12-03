package com.libr.dao;

import com.libr.model.Books;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BooksDao {
    private Connection con;

    public BooksDao(Connection con) {
        this.con = con;
    }

    public boolean insertBooks(Books bk)
    {
        boolean result = false;

        try{
            String query = "insert into book(ID,TITLE,AUTHOR,PUBLISHER,PUBLISHYEAR,GENRE,LANGUAGE,AVAILABILITY) value(?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = this.con.prepareStatement(query);
            pstmt.setInt(1,bk.getBookId());
            pstmt.setString(2,bk.getBookTitle());
            pstmt.setString(3,bk.getBookAuthor());
            pstmt.setString(4,bk.getBookPublisher());
            pstmt.setInt(5,bk.getBookPublicationYear());
            pstmt.setString(6,bk.getGenre());
            pstmt.setString(7,bk.getLanguage());
            pstmt.setInt(8,bk.getAvailability());
            pstmt.executeUpdate();
            result = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
}
