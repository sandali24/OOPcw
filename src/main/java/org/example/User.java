package org.example;

//User Class
public class User {
    private String userName;
    private String password;
    //Constructor of User Class
    public User(String userName, String password){
        this.userName=userName;
        this.password=password;
    }
    //Getters and Setters
    public void setUserName(String userName){
        this.userName=userName;
    }
    public String getUserName(){
        return userName;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getPassword(){
        return password;
    }
}
