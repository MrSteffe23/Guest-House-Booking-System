package com.booking.project.bathroom;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * This is a data Class. It is used to match the attributes from the database.
 */
@Entity
@Table(name = "bathrooms")
public class Bathroom {
    @Id
    @SequenceGenerator(
            name = "bathroom_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "bathroom_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    @Column(name = "id_house")
    private Long idHouse;
    @Column(name = "mirrors_count")
    private int mirrorsCount;
    private boolean shower;
    private boolean bathtub;
    private String size;
    private String details;
    private boolean wc;
    private boolean sink;

    public Bathroom(Long id, Long idHouse, int mirrorsCount, boolean shower, boolean bathtub, String size,
                    String details, boolean wc, boolean sink) {
        this.id = id;
        this.idHouse = idHouse;
        this.mirrorsCount = mirrorsCount;
        this.shower = shower;
        this.bathtub = bathtub;
        this.size = size;
        this.details = details;
        this.wc = wc;
        this.sink = sink;
    }

    public Bathroom(){

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

    public int getMirrorsCount() {
        return mirrorsCount;
    }

    public void setMirrorsCount(int mirrorsCount) {
        this.mirrorsCount = mirrorsCount;
    }

    public boolean isShower() {
        return shower;
    }

    public void setShower(boolean shower) {
        this.shower = shower;
    }

    public boolean isBathtub() {
        return bathtub;
    }

    public void setBathtub(boolean bathtub) {
        this.bathtub = bathtub;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isWc() {
        return wc;
    }

    public void setWc(boolean wc) {
        this.wc = wc;
    }

    public boolean isSink() {
        return sink;
    }

    public void setSink(boolean sink) {
        this.sink = sink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bathroom bathroom = (Bathroom) o;
        return mirrorsCount == bathroom.mirrorsCount && shower == bathroom.shower && bathtub == bathroom.bathtub
                && wc == bathroom.wc && sink == bathroom.sink
                && Objects.equals(idHouse, bathroom.idHouse) && Objects.equals(size, bathroom.size)
                && Objects.equals(details, bathroom.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idHouse, mirrorsCount, shower, bathtub, size, details, wc, sink);
    }
}
