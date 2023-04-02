package com.booking.project.reservation;

import jakarta.persistence.*;

import java.time.LocalDate;

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

    private LocalDate startDate;
    private LocalDate endDate;

    public Reservation(Long id, Long idHouse, Long idClient, LocalDate startDate, LocalDate endDate) {
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", idHouse=" + idHouse +
                ", idClient=" + idClient +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
