package com.booking.project.reservation;

import jakarta.persistence.*;

import java.util.Date;

/**
 * This is a data Class. It is used to match the attributes from the database.
 */
@Entity
@Table(name  = "reservations")
public class Reservation {
    @Id
    @SequenceGenerator(
            name = "reservation_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "reservation_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private Long idHouse;
    private Long idClient;

    private Date startDate;
    private Date endDate;

    public Reservation(Long id, Long idHouse, Long idClient, Date startDate, Date endDate) {
        this.id = id;
        this.idHouse = idHouse;
        this.idClient = idClient;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Reservation(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdHouse() {
        return idHouse;
    }

    public void setIdHouse(Long idHouse) {
        this.idHouse = idHouse;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
