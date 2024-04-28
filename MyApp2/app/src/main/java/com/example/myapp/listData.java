package com.example.myapp;

public class listData {

    String studentId;
    String name;
    String surname;
    String rollNumber;
    String className;
    String schoolName;

    public listData(String studentId, String name, String surname, String rollNumber, String className, String schoolName) {
        this.studentId = studentId;
        this.name = name;
        this.surname = surname;
        this.rollNumber = rollNumber;
        this.className = className;
        this.schoolName = schoolName;
    }




    public String getStudentId() {
        return studentId;
    }



    public String getName() {
        return name;
    }



    public String getSurname() {
        return surname;
    }



    public String getRollNumber() {
        return rollNumber;
    }



    public String getClassName() {
        return className;
    }



    public String getSchoolName() {
        return schoolName;
    }


}
