package com.booking.project.reservation;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

/**
 * This is a data Class. It is used to match the attributes from the database.
 */
@Entity
@Table(name  = "reservations")
public class Reservation implements Cloneable{
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
    @Column(name = "id_house")
    private Long idHouse;
    @Column(name = "id_client")
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(idHouse, that.idHouse) && Objects.equals(idClient, that.idClient) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idHouse, idClient, startDate, endDate);
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

    /**
     * Method used to make a deep copy of a Reservation
     * @return A deep copy of a Reservation
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        Reservation cloned = (Reservation) super.clone();
        return cloned;
    }
}
