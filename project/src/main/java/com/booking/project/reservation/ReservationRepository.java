package com.booking.project.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This Interface is responsible for the lower level logic.<br>
 * Here are all the methods used for interacting directly with the database.
 * Spring does all the work behind this methods.<br>
 * Here you can define queries, or use some predefined methods.
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
