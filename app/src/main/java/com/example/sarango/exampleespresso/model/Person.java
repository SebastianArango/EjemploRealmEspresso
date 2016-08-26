package com.example.sarango.exampleespresso.model;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sarango on 23/08/2016.
 */
public class Person extends RealmObject {

    @PrimaryKey
    private String userID;
    private String userName;
    private String userLastName;
    private String userAge;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }
}
