package com.booking.project.bedroom;

import jakarta.persistence.*;

/**
 * This is a data Class. It is used to match the attributes from the database.
 */
@Entity
@Table(name = "bedrooms")
public class Bedroom {
    @Id
    @SequenceGenerator(
            name = "bedroom_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "bedroom_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private Long idHouse;
    private int number;
    private int bedsCount;
    private int tvsCount;
    private String color;
    private String details;

    public Bedroom(Long id, Long idHouse, int number, int bedsCount, int tvsCount, String color, String details) {
        this.id = id;
        this.idHouse = idHouse;
        this.number = number;
        this.bedsCount = bedsCount;
        this.tvsCount = tvsCount;
        this.color = color;
        this.details = details;
    }

    public Bedroom(){

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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getBedsCount() {
        return bedsCount;
    }

    public void setBedsCount(int bedsCount) {
        this.bedsCount = bedsCount;
    }

    public int getTvsCount() {
        return tvsCount;
    }

    public void setTvsCount(int tvsCount) {
        this.tvsCount = tvsCount;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
