package com.booking.project;

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
     * Method used to insert a new House in the database
     * @param house a new House to insert in the database
     */
    void createHouse(House house);
}
