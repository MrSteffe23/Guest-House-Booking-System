package com.booking.project.reservation;
public interface ReservationObservable {
    void notifyAdmins(Reservation reservation, String notificationType);
}
