package com.booking.project.bedroom;

import java.util.List;

/**
 * Interface which separates the database from the java logic. <br>
 * Here are all the methods used to interact with the lower level of database
 */
public interface IBedroomService {
    /**
     * Get all Bedrooms in the database, regardless of which house it belongs to.
     * @return The list of the Bedrooms found in the database
     */
    List<Bedroom> getAllBedrooms();

    /**
     * Get all Bedrooms in the database for a specific house given by an id
     * @param idHouse the id of the House whose bedrooms you want to get
     * @return The list of the Bedrooms for a specific house
     */
    List<Bedroom> getBedrooms(Long idHouse);

    /**
     * Method used to insert a new Bedroom in the database
     * @param bedroom a new Bedroom to insert in the database
     */
    void createBedroom(Bedroom bedroom);

    /**
     * Method used to delete a Bedroom from the database (if the Bedroom exists).
     * @param id the id of the Bedroom to be deleted.
     */
    void deleteBedroom(Long id);

    /**
     * Method used to update a Bedroom with different details (if the Bedroom exists).
     * @param id the id of the Bedroom to be updated.
     * @param bedroom the new specifications for the bedroom.
     */
    void updateBedroom(Bedroom bedroom, Long id);
}
