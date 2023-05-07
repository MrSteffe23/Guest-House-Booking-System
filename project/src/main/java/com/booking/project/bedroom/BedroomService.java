package com.booking.project.bedroom;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Class which implements the contract of the interface {@link IBedroomService}. <br>
 * It has the implementation of the methods for PostgreSQL database.
 * Here is all the logic of the application.
 */
@Service
public class BedroomService implements IBedroomService{

    /**
     * Attribute which represents the DataAccess layer.
     */
    private final BedroomRepository bedroomRepository;

    /**
     * Constructor which have the role to implement Dependency Injection for the bedroomRepository attribute.
     * @param bedroomRepository the reference to the DataAccess layer.
     */
    public BedroomService(BedroomRepository bedroomRepository) {
        this.bedroomRepository = bedroomRepository;
    }

    /**
     * This method is used for display purposes, or for statistics. You can see all the bedrooms in the database, for
     * every house you want.
     * @return all the houses in the database in a List.
     */
    @Override
    public List<Bedroom> getAllBedrooms() {
        return bedroomRepository.findAll();
    }

    /**
     * This method is used for display purposes, or for statistics. You can see all the bedrooms in the database, for
     * a specific house given by an id
     * @param idHouse the id of the House whose bedrooms you want to get
     * @return all the houses in the database in a List.
     */
    @Override
    public List<Bedroom> getBedrooms(Long idHouse) {
        return bedroomRepository.findByIdHouse(idHouse);
    }

    /**
     * Creates a Bedroom in the database, given the data in the parameter.
     * @param bedroom JSON with data for a bedroom.
     */
    @Override
    public void createBedroom(Bedroom bedroom) {
        checkUniqueIdHouseNumber(bedroom);
        bedroomRepository.save(bedroom);
    }

    /**
     * First, this method checks to see if the database have a bedroom with the specified id. If yes,
     * the bedroom is being deleted, otherwise, an exception is thrown.
     * @param id the id of the Bedroom to be deleted.
     * @throws IllegalStateException if the database doesn't have a bedroom with the specified id.
     */
    @Override
    public void deleteBedroom(Long id) {
        checkValidBedroom(id);
        bedroomRepository.deleteById(id);
    }

    /**
     * First, this method checks to see if a Bedroom with the specified id exists, so that it can be updated.<br>
     * Then, the Bedroom extracted with the specified id is updated and then saved in the database.
     * @param id the id of the Bedroom to be updated.
     * @param bedroom the Bedroom with new specifications.
     */
    @Override
    public void updateBedroom(Bedroom bedroom, Long id) {
        checkValidBedroom(id);
        Bedroom bedroomToUpdate = bedroomRepository.findById(id).get();
        if(!(bedroomToUpdate.getNumber()==bedroom.getNumber() && bedroomToUpdate.getIdHouse()==bedroom.getIdHouse())){
            checkUniqueIdHouseNumber(bedroom);
        }

        bedroomToUpdate.setColor(bedroom.getColor());
        bedroomToUpdate.setDetails(bedroom.getDetails());
        bedroomToUpdate.setBedsCount(bedroom.getBedsCount());
        bedroomToUpdate.setNumber(bedroom.getNumber());
        bedroomToUpdate.setTvsCount(bedroom.getTvsCount());
        bedroomToUpdate.setIdHouse(bedroom.getIdHouse());

        bedroomRepository.save(bedroomToUpdate);
    }

    /**
     * Function which verifies if the database has a bedroom with the specified id
     * @param id id of the bedroom that you want to look for
     */
    public void checkValidBedroom(Long id){
        Optional<Bedroom> bedroomOptional = bedroomRepository.findById(id);
        if(!bedroomOptional.isPresent()){
            throw new IllegalStateException(String.format("The Bedroom with id %s doesn't exist.", id));
        }
    }

    /**
     * Verifies if the database already has a bedroom with the same number for the same house
     * @param bedroom the bedroom I want to search for in the database
     */
    public void checkUniqueIdHouseNumber(Bedroom bedroom){
        List<Bedroom> bedrooms = bedroomRepository.findByNumberAndIdHouse(bedroom.getNumber(), bedroom.getIdHouse());
        if(!bedrooms.isEmpty()){
            throw new IllegalStateException(String.format("The Bedroom with number %s , associated with house id %s" +
                    "already exists.", bedroom.getNumber(), bedroom.getIdHouse()));
        }
    }
}
