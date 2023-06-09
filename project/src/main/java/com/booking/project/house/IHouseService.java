package com.booking.project.house;

import java.util.List;

/**
 * Interface which separates the database from the java logic. <br>
 * Here are all the methods used to interact with the lower level of database
 */
public interface IHouseService {
    /**
     * Get all the Houses in the database
     * @return The list of the Houses found in the database
     */
    List<House> getHouses();

    /**
     * Get a House by an id
     * @return The House with the specified id
     */
    House getHouse(Long id);

    /**
     * Method used to insert a new House in the database
     * @param house a new House to insert in the database
     */
    void createHouse(House house);

    /**
     * Method used to delete a House from the database (if the House exists).
     * @param id the id of the House to be deleted.
     */
    void deleteHouse(Long id);

    /**
     * Method used to update a House with different details (if the House exists).
     * @param id the id of the House to be updated.
     * @param house the House new specifications.
     */
    void updateHouse(Long id, House house);
}
