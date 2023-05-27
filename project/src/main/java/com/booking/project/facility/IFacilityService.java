package com.booking.project.facility;

import java.util.List;

/**
 * Interface which separates the database from the java logic. <br>
 * Here are all the methods used to interact with the lower level of database
 */
public interface IFacilityService {

    /**
     * Get all Facilities in the database, regardless of which house it belongs to.
     * @return The list of the Facilities found in the database
     */
    List<Facility> getAllFacilities();

    /**
     * Get all Facilities in the database for a specific house given by an id
     * @param id_house the id of the House whose facilities you want to get
     * @return The list of the Facilities for a specific house
     */
    List<Facility> getFacilities(Long id_house);

    /**
     * Method used to insert a new Facility in the database
     * @param facility a new Facility to insert in the database
     */
    void createFacility(Facility facility);

    /**
     * Method used to delete a Facility from the database (if the Facility exists).
     * @param id the id of the Facility to be deleted.
     */
    void deleteFacility(Long id);

    /**
     * Method used to update a Facility with different details (if the Facility exists).
     * @param id the id of the Facility to be updated.
     * @param facility the Facility new specifications.
     */
    void updateFacility(Long id, Facility facility);

}
