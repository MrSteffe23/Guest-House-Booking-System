package com.booking.project.bathroom;

import java.util.List;

/**
 * Interface which separates the database from the java logic. <br>
 * Here are all the methods used to interact with the lower level of database
 */
public interface IBathroomService {
    /**
     * Get all Bathrooms in the database, regardless of which house it belongs to.
     *
     * @return The list of the Bathrooms found in the database
     */
    List<Bathroom> getAllBathrooms();

    /**
     * Get all Bathrooms in the database for a specific house given by an id
     *
     * @param idHouse the id of the House whose bathrooms you want to get
     * @return The list of the Bathrooms for a specific house
     */
    List<Bathroom> getBathrooms(Long idHouse);

    /**
     * Method used to insert a new Bathroom in the database
     *
     * @param bathroom a new Bathroom to insert in the database
     */
    void createBathroom(Bathroom bathroom);

    /**
     * Method used to delete a Bathroom from the database (if the Bathroom exists).
     *
     * @param id the id of the Bathroom to be deleted.
     */
    void deleteBathroom(Long id);

    /**
     * Method used to update a Bathroom with different details (if the Bathroom exists).
     *
     * @param id      the id of the Bathroom to be updated.
     * @param bathroom the new specifications for the bathroom.
     */
    void updateBathroom(Bathroom bathroom, Long id);
}
