package com.libr.model;

public class Students implements User {
    private int studentId;
    private String studentName;
    private int studentAge;
    private String studentPhoneNo;
    private String studentEmailId;
    private String studentBranch;
    private String studentPassword;


    public Students() {
    }

    public Students(int studentId, String studentName, int studentAge, String studentPhoneNo, String studentEmailId, String studentBranch, String studentPassword) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentPhoneNo = studentPhoneNo;
        this.studentEmailId = studentEmailId;
        this.studentBranch = studentBranch;
        this.studentPassword = studentPassword;
    }

    public int getId() {
        return studentId;
    }

    public void setId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return studentName;
    }

    public void setName(String studentName) {
        this.studentName = studentName;
    }

    public int getAge() {
        return studentAge;
    }

    public void setAge(int studentAge) {
        this.studentAge = studentAge;
    }

    public String getMobileNo() {
        return studentPhoneNo;
    }

    public void setMobileNo(String studentPhoneNo) {
        this.studentPhoneNo = studentPhoneNo;
    }

    public String getEmailId() {
        return studentEmailId;
    }

    public void setEmailId(String studentEmailId) {
        this.studentEmailId = studentEmailId;
    }

    public String getPassword() {
        return studentPassword;
    }

    public void setPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public String getDepartment() {
        return studentBranch;
    }

    public void setDepartment(String studentBranch) {
        this.studentBranch = studentBranch;
    }
}
