package com.example.loginui.model;

public class User {
    private String email;

    public User(String email){
        this.email = email;
    }

    public String getEmail() {
        if (email == null) {
            return "";
        }
        return email;
    }

    public boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public boolean isValidPhoneNumber() {
        return getEmail().length() >= 10;
    }
}
