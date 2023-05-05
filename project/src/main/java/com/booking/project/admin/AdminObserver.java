package com.booking.project.admin;

import com.booking.project.reservation.Reservation;

/**
 * Interface which defines the methods used by an Observer.
 * It is used to make the Admin an Observer
 */
public interface AdminObserver {
    /**
     * @param changedReservation the changed reservation
     * @param oldReservation the old reservation in case of an update, otherwise is null
     * @param notificationType the type of reservation(add/delete/update)
     */
    void update(Reservation changedReservation, Reservation oldReservation, String notificationType);
}
