package com.libr.model;

public class Teachers implements User{

    private int tId;
    private String tName;
    private int tAge;
    private String tMobileNo;
    private String tEmailId;
    private String department;
    private String tPassword;

    public Teachers(int tId, String tName, int tAge, String tMobileNo, String tEmailId, String department,String tPassword) {
        this.tId = tId;
        this.tName = tName;
        this.tAge = tAge;
        this.tMobileNo = tMobileNo;
        this.tEmailId = tEmailId;
        this.department = department;
        this.tPassword = tPassword;
    }

    public Teachers() {
    }

    public int getId() {
        return tId;
    }

    public void setId(int tId) {
        this.tId = tId;
    }

    public String getName() {
        return tName;
    }

    public void setName(String tName) {
        this.tName = tName;
    }

    public int getAge() {
        return tAge;
    }

    public void setAge(int tAge) {
        this.tAge = tAge;
    }

    public String getMobileNo() {
        return tMobileNo;
    }

    public void setMobileNo(String tMobileNo) {
        this.tMobileNo = tMobileNo;
    }

    public String getEmailId() {
        return tEmailId;
    }

    public void setEmailId(String tEmailId) {
        this.tEmailId = tEmailId;
    }

    public String getPassword() {
        return tPassword;
    }

    public void setPassword(String tPassword) {
        this.tPassword = tPassword;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
