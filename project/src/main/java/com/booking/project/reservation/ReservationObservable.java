package com.booking.project.reservation;

/**
 * Interface used to define the methods used by an Observable in order to notify some observers.
 * In this case, the Observable will be ReservationService.
 */
public interface ReservationObservable {
    /**
     * Method used to notify some Observers
     * @param changedReservation the changed reservation
     * @param oldReservation the old reservation in case notificationType is "update"
     * @param notificationType the type of reservation (new/delete/update)
     */
    void notifyAdmins(Reservation changedReservation, Reservation oldReservation, String notificationType);
}
