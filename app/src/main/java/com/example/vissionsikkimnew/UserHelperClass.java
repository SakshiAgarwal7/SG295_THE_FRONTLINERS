package com.example.vissionsikkimnew;

public class UserHelperClass {

    String aadhar, phoneno, name;

    public UserHelperClass(String aadhar, String phoneno, String name) {
        this.aadhar = aadhar;
        this.phoneno = phoneno;
        this.name = name;
    }

    public UserHelperClass() {
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}





