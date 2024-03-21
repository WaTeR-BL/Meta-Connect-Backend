package com.ned.metadata_tool.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserId implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String email;

    public UserId() {
    }

    public UserId(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserId userId = (UserId) o;
        return Objects.equals(username, userId.username) && Objects.equals(email, userId.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }
}
