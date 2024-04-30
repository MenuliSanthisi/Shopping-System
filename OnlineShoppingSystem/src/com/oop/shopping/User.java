package com.oop.shopping;
import java.util.Arrays;

public class User {
    private String username;
    private char[] password;
    public User(String username,char[] password){
        this.username = username;
        this.password = password;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public char[] getPassword(){
        return password;
    }
    public void setPassword(char[] password){
        this.password = password;
    }
    @Override     //method that is already inherited from the parent class(method already created in the parent class)
    //parent class is the object class
    public String toString(){
        return "com.oop.shopping.User( " +
                "username: " + username +
                ", password: " + Arrays.toString(password)+
                " )";
    }
}
