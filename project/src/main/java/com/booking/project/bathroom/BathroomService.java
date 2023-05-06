package com.booking.project.bathroom;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Class which implements the contract of the interface {@link IBathroomService}. <br>
 * It has the implementation of the methods for PostgreSQL database.
 * Here is all the logic of the application.
 */
@Service
public class BathroomService implements IBathroomService{

    /**
     * Attribute which represents the DataAccess layer.
     */
    private final BathroomRepository bathroomRepository;

    /**
     * Constructor which have the role to implement Dependency Injection for the bathroomRepository attribute.
     * @param bathroomRepository the reference to the DataAccess layer.
     */
    public BathroomService(BathroomRepository bathroomRepository) {
        this.bathroomRepository = bathroomRepository;
    }

    /**
     * This method is used for display purposes, or for statistics. You can see all the bathrooms in the database, for
     * every house you want.
     * @return all the houses in the database in a List.
     */
    @Override
    public List<Bathroom> getAllBathrooms() {
        return bathroomRepository.findAll();
    }

    /**
     * This method is used for display purposes, or for statistics. You can see all the bathrooms in the database, for
     * a specific house given by an id
     * @param idHouse the id of the House whose bathrooms you want to get
     * @return all the houses in the database in a List.
     */
    @Override
    public List<Bathroom> getBathrooms(Long idHouse) {
        return bathroomRepository.findByIdHouse(idHouse);
    }

    /**
     * Creates a Bathroom in the database, given the data in the parameter.
     * @param bathroom JSON with data for a bathroom.
     */
    @Override
    public void createBathroom(Bathroom bathroom) {
        bathroomRepository.save(bathroom);
    }

    /**
     * First, this method checks to see if the database have a bathroom with the specified id. If yes,
     * the bathroom is being deleted, otherwise, an exception is thrown.
     * @param id the id of the Bathroom to be deleted.
     * @throws IllegalStateException if the database doesn't have a bathroom with the specified id.
     */
    @Override
    public void deleteBathroom(Long id) {
        checkValidBathroom(id);
        bathroomRepository.deleteById(id);
    }

    /**
     * First, this method checks to see if a Bathroom with the specified id exists, so that it can be updated.<br>
     * Then, the Bathroom extracted with the specified id is updated and then saved in the database.
     * @param id the id of the Bathroom to be updated.
     * @param bathroom the Bathroom with new specifications.
     */
    @Override
    public void updateBathroom(Bathroom bathroom, Long id) {
        checkValidBathroom(id);
        Bathroom bathroomToUpdate = bathroomRepository.findById(id).get();

        bathroomToUpdate.setDetails(bathroom.getDetails());
        bathroomToUpdate.setBathtub(bathroom.isBathtub());
        bathroomToUpdate.setIdHouse(bathroom.getIdHouse());
        bathroomToUpdate.setShower(bathroom.isShower());
        bathroomToUpdate.setSink(bathroom.isSink());
        bathroomToUpdate.setSize(bathroom.getSize());
        bathroomToUpdate.setWc(bathroom.isWc());
        bathroomToUpdate.setMirrorsCount(bathroom.getMirrorsCount());

        bathroomRepository.save(bathroomToUpdate);
    }

    /**
     * Function which verifies if the database has a bathroom with the specified id
     * @param id id of the bathroom that you want to look for
     */
    public void checkValidBathroom(Long id){
        Optional<Bathroom> bathroomOptional = bathroomRepository.findById(id);
        if(!bathroomOptional.isPresent()){
            throw new IllegalStateException(String.format("The Bathroom with id %s doesn't exist.", id));
        }
    }

}
