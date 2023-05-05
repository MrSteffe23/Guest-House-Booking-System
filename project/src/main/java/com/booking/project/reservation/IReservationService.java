package com.booking.project.reservation;

import java.util.List;

/**
 * Interface which separates the database from the java logic. <br>
 * Here are all the methods used to interact with the lower level of database
 */
public interface IReservationService {

    /**
     * Get all Reservations in the database, regardless of the house they belong to.
     * @return The list of the Reservations found in the database
     */
    List<Reservation> getAllReservations();

    /**
     * Get all Reservations in the database for a specific house given by an id
     * @param id_house the id of the House whose reservations you want to get
     * @return The list of the Reservations for a specific house
     */
    List<Reservation> getReservations(Long id_house);

    /**
     * Method used to insert a new Reservation in the database
     * @param reservation a new Reservation to insert in the database
     */
    void createReservation(Reservation reservation);

    /**
     * Method used to update a Reservation with different details (if the Reservation exists).
     * @param id the id of the Reservation to be updated.
     * @param reservation the Reservation new specifications.
     */
    void updateReservation(Reservation reservation, Long id);

    /**
     * Method used to delete a Reservation from the database (if the Reservation exists).
     * @param id the id of the Reservation to be deleted.
     */
    void deleteReservation(Long id);
}
