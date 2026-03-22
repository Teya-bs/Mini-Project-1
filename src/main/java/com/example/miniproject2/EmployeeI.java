package com.example.miniproject2;

public class EmployeeI  {

    private String address;
    private String userName;
    private String gender;
    private String degree;
    private String dob;
    private double salary;
    private String email;

    public EmployeeI(String address, String userName, String gender, String degree, String dob, String salary, String email) {
        this.address = address;
        this.userName = userName;
        this.gender = gender;
        this.degree = degree;
        this.dob = dob;
        this.salary = Double.parseDouble(salary);
        this.email = email;
    }



    public String getAddress() { return address; }
    public String getUserName() { return userName; }
    public String getGender() { return gender; }
    public String getDegree() { return degree; }
    public String getDob() { return dob; }
    public double getSalary() { return salary; }
    public String getEmail() { return email; }
}
