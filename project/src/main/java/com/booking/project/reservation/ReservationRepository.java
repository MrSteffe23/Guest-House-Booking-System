package com.booking.project.reservation;

import com.booking.project.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * This Interface is responsible for the lower level logic.<br>
 * Here are all the methods used for interacting directly with the database.
 * Spring does all the work behind this methods.<br>
 * Here you can define queries, or use some predefined methods.
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    /**
     *  This method is named to match the name of the idHouse field in the Review class. This is because Spring Data JPA
     *  uses naming conventions to automatically generate queries based on the names of the methods defined in the
     *  repository interface. In this case, Spring Data JPA will generate a query that looks for reviews based on the
     *  idHouse field in the Review class.
     * @param idHouse the id of the house that I need to find the reviews
     * @return the list of the Reviews associated with a specified House by that 'idHouse'
     */
    List<Reservation> findByIdHouse(Long idHouse);
}
