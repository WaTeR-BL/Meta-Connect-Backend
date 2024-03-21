package com.ned.metadata_tool.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class UserAccount {
    @EmbeddedId
    private UserId userId;
    private String firstName;
    private String lastName;
    private String password;

    public UserAccount(UserId userId, String firstName, String lastName, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public UserAccount() {
    }

    public UserId getUserId() {
        return userId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
