package com.booking.project.admin;

import com.booking.project.reservation.Reservation;

public interface AdminObserver {
    void update(Reservation reservation, String notificationType);
}
