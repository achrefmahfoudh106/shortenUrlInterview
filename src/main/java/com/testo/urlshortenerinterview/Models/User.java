package com.testo.urlshortenerinterview.Models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    private String email;
    private String password;
    private String fName;
    private String lName;
    @SuppressWarnings("JpaAttributeTypeInspection")
    private Set<String> roles;

    public User(){};

    public User(String fName, String lName, String email, String password) {
        super();
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = password;
    }

    public String getfName() {
        return fName;
    }
    public void setfName(String fName) {
        this.fName = fName;
    }
    public String getlName() {
        return lName;
    }
    public void setlName(String lName) {
        this.lName = lName;
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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
