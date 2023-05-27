package com.booking.project.admin;

import com.booking.project.reservation.Reservation;
import jakarta.persistence.*;

import java.util.Objects;

/**
 * This is a data Class. It is used to match the attributes from the database.
 */
@Entity
@Table(name = "admin")
public class Admin implements AdminObserver{
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
    private String email;

    public Admin(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(username, admin.username) && Objects.equals(password, admin.password) && Objects.equals(email, admin.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email);
    }

    /**
     * Method which notifies each admin about a change in Reservation table
     * @param changedReservation the changed reservation
     * @param oldReservation the old reservation in case of an update, otherwise is null
     * @param notificationType the change type (new/delete/update)
     */
    @Override
    public void update(Reservation changedReservation, Reservation oldReservation, String notificationType) {
        if(notificationType.equals("update")){
            System.out.printf("New notification: " + notificationType + " --> " + " From: " + oldReservation.toString()
                    + "\nTo: " + changedReservation.toString() +"\n");
        }
        else {
            System.out.printf("New notification: " + notificationType + " --> " + changedReservation.toString() + "\n");
        }
    }
}
