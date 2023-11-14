package com.example.the_bloom_room.Class;

public class UserClass {

    private String UserName;
    private String Password;
    private String UserType;

    public UserClass() {

    }

    public UserClass(String userName, String password, String userType) {
        UserName = userName;
        Password = password;
        UserType = userType;
    }



    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }


}
