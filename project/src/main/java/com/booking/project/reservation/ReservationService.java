package com.booking.project.reservation;

import com.booking.project.admin.Admin;
import com.booking.project.admin.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Class which implements the contract of the interface {@link IReservationService}. <br>
 * It has the implementation of the methods for PostgreSQL database.
 * Here is all the logic of the application.
 */
@Service
public class ReservationService implements IReservationService, ReservationObservable{
    /**
     * Attributes which represents the DataAccess layer.
     */
    private final ReservationRepository reservationRepository;
    private final AdminRepository adminRepository;

    /**
     * Constructor which have the role to implement Dependency Injection for the reservationRepository attribute.
     * @param reservationRepository the reference to the DataAccess layer.
     */
    public ReservationService(ReservationRepository reservationRepository, AdminRepository adminRepository) {
        this.reservationRepository = reservationRepository;
        this.adminRepository = adminRepository;
    }

    /**
     * This method is used for display purposes. You can see all the reservations in the database, regardless of the house they belong to.
     * @return all the reservations in the database in a List.
     */
    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    /**
     * Get all Reservations in the database for a specific house given by an id
     * @param id_house the id of the House whose reservations you want to get
     * @return The list of the Reservations for a specific house
     */
    @Override
    public List<Reservation> getReservations(Long id_house) {
        return reservationRepository.findByIdHouse(id_house);
    }

    /**
     * Creates a Reservation in the database, given the data in the parameter.
     * @param reservation JSON with data for a reservation.
     */
    @Override
    public void createReservation(Reservation reservation) {
        //reservation.setStartDate(LocalDate.of(2023,2,4));
        //reservation.setEndDate(LocalDate.of(2023,2,23));
        reservationRepository.save(reservation);
        notifyAdmins(reservation, null, "new");
    }

    /**
     * First, this method checks to see if a Reservation with the specified id exists, so that it can be updated.<br>
     * Then, the Reservation extracted with the specified id is updated and then saved in the database.
     * @param id the id of the Reservation to be updated.
     * @param reservation the Reservation with new specifications.
     */
    @Override
    public void updateReservation(Reservation reservation, Long id) {
        checkValidIdReservation(id);
        Reservation reservationToUpdate = reservationRepository.findById(id).get();
        Reservation oldReservation = null;
        try {
            oldReservation = (Reservation) reservationToUpdate.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        reservationToUpdate.setIdClient(reservation.getIdClient());
        reservationToUpdate.setIdHouse(reservation.getIdHouse());
        reservationToUpdate.setStartDate(reservation.getStartDate());
        reservationToUpdate.setEndDate(reservation.getEndDate());

        notifyAdmins(reservationToUpdate, oldReservation,"update");
        reservationRepository.save(reservationToUpdate);
    }

    /**
     * First, this method checks to see if the database have a reservation with the specified id. If yes,
     * the reservation is being deleted, otherwise, an exception is thrown.
     * @param id the id of the Reservation to be deleted.
     * @throws IllegalStateException if the database doesn't have a reservation with the specified id.
     */
    @Override
    public void deleteReservation(Long id) {
        checkValidIdReservation(id);
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        reservationRepository.deleteById(id);
        notifyAdmins(reservationOptional.get(), null,"delete");
    }

    /**
     * Functions which verifies if the database has a reservation with the specified id
     * @param id id of the reservation that you want to look for
     */
    public void checkValidIdReservation(Long id){
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if(!reservationOptional.isPresent()){
            throw new IllegalStateException(String.format("The Reservation with id %s doesn't exist.", id));
        }
    }

    /**
     * This method represents the Observable from the design pattern called Observable.
     * Once a reservation is added, removed or changed, all the admins are notified via an email.
     * @param changedReservation the changed reservation which is added or deleted
     * @param oldReservation the old reservation in case notificationType = "update", otherwise it is null
     * @param notificationType the type of the change: new/delete/update
     */
    @Override
    public void notifyAdmins(Reservation changedReservation, Reservation oldReservation, String notificationType) {
        List<Admin> admins = adminRepository.findAll();
        for(Admin admin: admins)
            admin.update(changedReservation, oldReservation, notificationType);
    }
}
