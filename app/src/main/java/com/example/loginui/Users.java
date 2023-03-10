package com.example.loginui;

public class Users {

    private  String name,email,password,confirmpass;

    public Users(String name, String email, String password, String confirmpass) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmpass = confirmpass;
    }

    public Users() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpass() {
        return confirmpass;
    }

    public void setConfirmpass(String confirmpass) {
        this.confirmpass = confirmpass;
    }
}
