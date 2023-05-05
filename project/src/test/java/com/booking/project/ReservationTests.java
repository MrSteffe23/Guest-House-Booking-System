package com.booking.project;

import com.booking.project.admin.Admin;
import com.booking.project.admin.AdminRepository;
import com.booking.project.reservation.IReservationService;
import com.booking.project.reservation.Reservation;
import com.booking.project.reservation.ReservationRepository;
import com.booking.project.reservation.ReservationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * This class is used to test the implementation of the class {@link com.booking.project.reservation.ReservationService}
 */
@SpringBootTest
public class ReservationTests {
    @Mock
    private ReservationRepository reservationRepositoryMock;
    @Mock
    private AdminRepository adminRepositoryMock;

    @Test
    void testGetAllReservations() {
        List<Reservation> reservationsResult = new ArrayList<>();
        reservationsResult.add(new Reservation(1L, 1L, 2L, LocalDate.of(2022,6,4),
                                LocalDate.of(2022,6,24)));
        reservationsResult.add(new Reservation(1L, 1L, 3L, LocalDate.of(2023,3,4),
                                LocalDate.of(2023,3,14)));
        List<Reservation> reservationsToBeReturned = new ArrayList<>(reservationsResult);
        when(reservationRepositoryMock.findAll()).thenReturn(reservationsToBeReturned);

        IReservationService reservationService = new ReservationService(reservationRepositoryMock, adminRepositoryMock);
        assertEquals(reservationService.getAllReservations(), reservationsResult);
        verify(reservationRepositoryMock).findAll();
    }

    @Test
    void testGetReservations() {
        Long id = 2L;
        List<Reservation> reservationsResult = new ArrayList<>();
        reservationsResult.add(new Reservation(1L, 1L, 2L, LocalDate.of(2022,6,4),
                LocalDate.of(2022,6,24)));
        reservationsResult.add(new Reservation(1L, 1L, 3L, LocalDate.of(2023,3,4),
                LocalDate.of(2023,3,14)));
        List<Reservation> reservationsToBeReturned = new ArrayList<>(reservationsResult);
        when(reservationRepositoryMock.findByIdHouse(id)).thenReturn(reservationsToBeReturned);

        IReservationService reservationService = new ReservationService(reservationRepositoryMock, adminRepositoryMock);
        assertEquals(reservationService.getReservations(id), reservationsResult);
        verify(reservationRepositoryMock).findByIdHouse(id);
    }

    @Test
    void testCreateReservation() {
        Reservation reservation = new Reservation(1L, 1L, 2L, LocalDate.of(2022,6,4),
                LocalDate.of(2022,6,24));

        IReservationService reservationService = new ReservationService(reservationRepositoryMock, adminRepositoryMock);
        reservationService.createReservation(reservation);
        verify(reservationRepositoryMock).save(reservation);
    }

    @Test
    void testDeleteReservation() {
        Long id = 1L;
        Reservation reservation = new Reservation(1L, 1L, 2L, LocalDate.of(2022,6,4),
                LocalDate.of(2022,6,24));
        Optional<Reservation> reservationOptional = Optional.ofNullable(reservation);
        when(reservationRepositoryMock.findById(id)).thenReturn(reservationOptional);

        IReservationService reservationService = new ReservationService(reservationRepositoryMock, adminRepositoryMock);
        reservationService.deleteReservation(id);
        verify(reservationRepositoryMock, times(2)).findById(id);
        verify(reservationRepositoryMock).deleteById(id);
    }

    @Test
    void testUpdateReservation() {
        Long id = 1L;
        Reservation reservationToBeUpdated = new Reservation(1L, 1L, 1L, LocalDate.of(2022,6,4),
                LocalDate.of(2022,6,24));
        Optional<Reservation> reservationOptional = Optional.ofNullable(reservationToBeUpdated);
        Reservation newReservation = new Reservation(1L, 1L, 2L, LocalDate.of(2022,6,4),
                LocalDate.of(2022,6,30));
        when(reservationRepositoryMock.findById(id)).thenReturn(reservationOptional);

        IReservationService reservationService = new ReservationService(reservationRepositoryMock, adminRepositoryMock);
        reservationService.updateReservation(newReservation, id);
        assertEquals(reservationToBeUpdated, newReservation);//verifying if the new reservation was updated
        verify(reservationRepositoryMock, times(2)).findById(id);
        verify(reservationRepositoryMock).save(reservationToBeUpdated);
    }

    @Test
    void testCheckValidIdReservation(){
        Long id = 1L;
        Reservation reservation = new Reservation(1L, 1L, 2L, LocalDate.of(2022,6,4),
                LocalDate.of(2022,6,24));
        Optional<Reservation> reservationOptional = Optional.ofNullable(reservation);
        when(reservationRepositoryMock.findById(id)).thenReturn(reservationOptional);

        ReservationService reservationService = new ReservationService(reservationRepositoryMock, adminRepositoryMock);
        reservationService.checkValidIdReservation(id);
        verify(reservationRepositoryMock).findById(id);
    }

    @Test
    void testNotifyAdminsNewReservation(){
        Reservation reservation = new Reservation(1L, 1L, 2L, LocalDate.of(2022,6,4),
                LocalDate.of(2022,6,24));
        List<Admin> admins = new ArrayList<>();
        Admin admin1 = mock(Admin.class);
        Admin admin2 = mock(Admin.class);
        admins.add(admin1);
        admins.add(admin2);
        when(adminRepositoryMock.findAll()).thenReturn(admins);

        IReservationService reservationService = new ReservationService(reservationRepositoryMock, adminRepositoryMock);
        reservationService.createReservation(reservation);
        verify(reservationRepositoryMock).save(reservation);
        verify(adminRepositoryMock).findAll();
        verify(admin1).update(reservation, null, "new");
        verify(admin2).update(reservation, null, "new");
    }

    @Test
    void testNotifyAdminsUpdateReservation(){
        Long id = 1L;
        Reservation reservationToBeUpdated = new Reservation(1L, 1L, 2L, LocalDate.of(2022,6,4),
                LocalDate.of(2022,6,24));
        Reservation oldReservation;
        try {
            oldReservation = (Reservation) reservationToBeUpdated.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        Optional<Reservation> reservationOptional = Optional.ofNullable(reservationToBeUpdated);
        Reservation newReservation = new Reservation(1L, 1L, 2L, LocalDate.of(2022,6,4),
                LocalDate.of(2022,6,30));
        when(reservationRepositoryMock.findById(id)).thenReturn(reservationOptional);
        List<Admin> admins = new ArrayList<>();
        Admin admin1 = mock(Admin.class);
        Admin admin2 = mock(Admin.class);
        admins.add(admin1);
        admins.add(admin2);
        when(adminRepositoryMock.findAll()).thenReturn(admins);

        IReservationService reservationService = new ReservationService(reservationRepositoryMock, adminRepositoryMock);
        reservationService.updateReservation(newReservation, id);
        assertEquals(reservationToBeUpdated, newReservation);//verifying if the new reservation was updated
        verify(reservationRepositoryMock, times(2)).findById(id);
        verify(reservationRepositoryMock).save(reservationToBeUpdated);
        verify(adminRepositoryMock).findAll();
        verify(admin1).update(reservationToBeUpdated, oldReservation, "update");
        verify(admin2).update(reservationToBeUpdated, oldReservation, "update");
    }

    @Test
    void testNotifyAdminsDeleteReservation(){
        Long id = 1L;
        Reservation reservation = new Reservation(1L, 1L, 2L, LocalDate.of(2022,6,4),
                LocalDate.of(2022,6,24));
        Optional<Reservation> reservationOptional = Optional.ofNullable(reservation);
        when(reservationRepositoryMock.findById(id)).thenReturn(reservationOptional);
        List<Admin> admins = new ArrayList<>();
        Admin admin1 = mock(Admin.class);
        Admin admin2 = mock(Admin.class);
        admins.add(admin1);
        admins.add(admin2);
        when(adminRepositoryMock.findAll()).thenReturn(admins);

        IReservationService reservationService = new ReservationService(reservationRepositoryMock, adminRepositoryMock);
        reservationService.deleteReservation(id);
        verify(reservationRepositoryMock, times(2)).findById(id);
        verify(reservationRepositoryMock).deleteById(id);
        verify(adminRepositoryMock).findAll();
        verify(admin1).update(reservation, null, "delete");
        verify(admin2).update(reservation, null, "delete");
    }
}
