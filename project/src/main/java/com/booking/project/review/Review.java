package com.booking.project.review;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * This is a data Class. It is used to match the attributes from the database.
 */
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @SequenceGenerator(
            name = "review_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "review_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    @Column(name = "id_house")
    private Long idHouse;
    @Column(name = "id_user")
    private Long idUser;
    private String description;

    private Long starsCount;

    public Review(Long id, Long idHouse, Long idUser, String description, Long starsCount) {
        this.id = id;
        this.idHouse = idHouse;
        this.idUser = idUser;
        this.description = description;
        this.starsCount = starsCount;
    }

    public Review(){

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

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStarsCount() {
        return starsCount;
    }

    public void setStarsCount(Long starsCount) {
        this.starsCount = starsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return  Objects.equals(idHouse, review.idHouse)
                && Objects.equals(idUser, review.idUser) && Objects.equals(description, review.description)
                && Objects.equals(starsCount, review.starsCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idHouse, idUser, description, starsCount);
    }
}
