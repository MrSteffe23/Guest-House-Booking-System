package com.booking.project.admin;

import com.booking.project.reservation.Reservation;

/**
 * Interface which defines the methods used by an Observer.
 * It is used to make the Admin an Observer
 */
public interface AdminObserver {
    /**
     * @param reservation the changed reservation
     * @param notificationType the type of reservation(add/delete/update)
     */
    void update(Reservation reservation, String notificationType);
}
