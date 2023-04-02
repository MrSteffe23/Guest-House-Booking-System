package com.booking.project.reservation;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This Class is the closest point from the user. It defines the end points of the
 * application and lets the {@link ReservationService} to deal with the logic behind this operations.<br>
 * All requests from the users are coming here first.
 */
@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {
    private final IReservationService reservationService;

    /**
     * Constructor which have the role to implement Dependency Injection for the reservationService attribute.
     * @param reservationService the reference to the Service layer.
     */
    public ReservationController(IReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /**
     * This method is used for display purposes, or for debugging.
     * @return a list with all the Reservations in the database, regardless of the house they belong to.
     */
    @GetMapping
    public List<Reservation> getAllReservations(){
        return reservationService.getAllReservations();
    }

    /**
     * Inserts a new Reservation in the database, if possible.
     * @param reservation JSON with all the data for a reservation
     */
    @PostMapping
    public void createReservation(@RequestBody Reservation reservation){
        reservationService.createReservation(reservation);
    }

    /**
     * Deletes a Reservation from the database, if possible.
     * @param id the id of the Reservation to be deleted.
     */
    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id){
        reservationService.deleteReservation(id);
    }

    /**
     * Updates a Reservation from the database with new specifications.
     * @param id the id of the Reservation to be updated.
     * @param reservation the Reservation with new specifications.
     */
    @PutMapping("/{id}")
    public void updateReservation(@PathVariable Long id, @RequestBody Reservation reservation){
        reservationService.updateReservation(reservation, id);
    }
}
