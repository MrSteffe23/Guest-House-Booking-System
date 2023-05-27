package com.booking.project.bathroom;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * This Interface is responsible for the lower level logic.<br>
 * Here are all the methods used for interacting directly with the database.
 * Spring does all the work behind this methods.<br>
 * Here you can define queries, or use some predefined methods.
 */
public interface BathroomRepository extends JpaRepository<Bathroom, Long> {
    /**
     *  This method is named to match the name of the idHouse field in the Bathroom class. This is because Spring Data JPA
     *  uses naming conventions to automatically generate queries based on the names of the methods defined in the
     *  repository interface. In this case, Spring Data JPA will generate a query that looks for bathrooms based on the
     *  idHouse field in the Bathroom class.
     * @param idHouse the id of the house that I need to find the bathrooms
     * @return the list of the Bathrooms associated with a specified House by that 'idHouse'
     */
    List<Bathroom> findByIdHouse(Long idHouse);

    /**
     * This method uses Spring Data JPA's method naming convention to generate a query that searches for Bathroom
     * entities where the number and idHouse match the given parameters
     * @param number the number for a specific bathroom
     * @param idHouse the id of the house a bathroom belongs
     * @return a list of all the bathrooms in the database with a specific number and idHouse
     */
    List<Bathroom> findByNumberAndIdHouse(Integer number, Long idHouse);
}
