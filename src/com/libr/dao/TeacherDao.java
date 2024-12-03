package com.libr.dao;
import com.libr.model.Teachers;

import java.sql.*;
public class TeacherDao {

    private Connection con;

    public TeacherDao(Connection con)
    {
        this.con = con;
    }

    public boolean insertTeacher(Teachers tch)
    {
        boolean result = false;
        try {

            String query = "insert into teacher(ID,NAME,AGE,PHONENO,EMAILID,BRANCH,PASSWORD) value(?,?,?,?,?,?,?)";
            PreparedStatement pstmt = this.con.prepareStatement(query);
            pstmt.setInt(1,tch.getId());
            pstmt.setString(2,tch.getName());
            pstmt.setInt(3,tch.getAge());
            pstmt.setString(4, tch.getMobileNo());
            pstmt.setString(5,tch.getEmailId());
            pstmt.setString(6,tch.getDepartment());
            pstmt.setString(7,tch.getPassword());
            pstmt.executeUpdate();
            result = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();;
        }
        return result;
    }

}
