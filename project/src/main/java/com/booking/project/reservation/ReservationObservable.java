package com.booking.project.reservation;

/**
 * Interface used to define the methods used by an Observable in order to notify some observers.
 * In this case, the Observable will be ReservationService.
 */
public interface ReservationObservable {
    /**
     * Method used to notify some Observers
     * @param reservation the changed reservation
     * @param notificationType the type of reservation (new/delete/update)
     */
    void notifyAdmins(Reservation reservation, String notificationType);
}
