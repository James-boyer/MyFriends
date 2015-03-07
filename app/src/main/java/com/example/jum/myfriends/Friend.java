package com.example.jum.myfriends;

/**
 * Created by jum on 23/02/15.
 */
public class Friend {
    private String name;
    private String email;
    private String phone;
    public Friend(){
        name = "";
        email = "";
        phone = "";
    }
    public void setName(String n){
        name = n;
    }
    public void setEmail(String e){
        email = e;
    }
    public void setPhone(String p){
        phone = p;
    }
    public String getName(){return name;}
    public String getEmail(){return email;}
    public String getPhone(){return phone;}
    @Override
    public String toString(){
        return name;
    }
}

