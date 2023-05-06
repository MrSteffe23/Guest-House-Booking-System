package com.booking.project.house;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * This is a data Class. It is used to match the attributes from the database.
 */
@Entity
@Table(name = "houses")
public class House {
    @Id
    @SequenceGenerator(
            name = "house_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "house_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private String name;
    private String address;
    private String location;
    private Float price;

    public House(Long id, String name, String address, String location, Float price) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.location = location;
        this.price = price;
    }

    public House() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return Objects.equals(name, house.name) && Objects.equals(address, house.address) && Objects.equals(location, house.location) && Objects.equals(price, house.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, location, price);
    }
}
