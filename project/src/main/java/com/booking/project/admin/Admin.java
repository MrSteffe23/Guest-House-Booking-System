package com.booking.project.admin;

import jakarta.persistence.*;

/**
 * This is a data Class. It is used to match the attributes from the database.
 */
@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @SequenceGenerator(
            name = "admin_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "admin_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private String username;
    private String password;

    public Admin(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Admin(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
