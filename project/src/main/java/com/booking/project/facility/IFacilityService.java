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
     * Method used to insert a new Facility in the database
     * @param facility a new Facility to insert in the database
     * @param id_house id for the house which gets a new facility (this "id" must come from a valid house)
     */
    void createFacility(Facility facility, Long id_house);

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
