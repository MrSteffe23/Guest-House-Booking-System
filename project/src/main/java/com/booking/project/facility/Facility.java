package com.booking.project.facility;

import jakarta.persistence.*;

/**
 * This is a data Class. It is used to match the attributes from the database.
 */
@Entity
@Table(name = "facilities")
public class Facility {
    @Id
    @SequenceGenerator(
            name = "facility_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "facility_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    @Column(name = "id_house")
    private Long idHouse;
    private String facilityName;
    private String details;

    public Facility(Long id, Long idHouse, String facilityName, String details) {
        this.id = id;
        this.idHouse = idHouse;
        this.facilityName = facilityName;
        this.details = details;
    }

    public Facility(){

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

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
