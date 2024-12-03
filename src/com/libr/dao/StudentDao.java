package com.libr.dao;
import com.libr.model.Students;

import java.sql.*;
public class StudentDoa {

    private Connection con;
    private PreparedStatement pstmt;

    public StudentDoa(Connection con)
    {
        this.con = con;
    }

    public boolean insertStudent(Students std)
    {
        boolean result = false;

        try{
            String query = "insert into student(ID,NAME,AGE,PHONENO,EMAILID,BRANCH,PASSWORD) value(?,?,?,?,?,?,?)";
            pstmt = this.con.prepareStatement(query);
            pstmt.setInt(1,std.getId());
            pstmt.setString(2,std.getName());
            pstmt.setInt(3,std.getAge());
            pstmt.setString(4,std.getMobileNo());
            pstmt.setString(5,std.getEmailId());
            pstmt.setString(6,std.getDepartment());
            pstmt.setString(7,std.getPassword());
            pstmt.executeUpdate();
            result = true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }



}
