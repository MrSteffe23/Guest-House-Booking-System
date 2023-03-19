package com.booking.project;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

/**
 * Class which implements the contract of the interface {@link IHouseService}. <br>
 * It has the implementation of the methods for PostgreSQL database.
 * Here is all the logic of the application.
 */
@Service
public class HouseService implements IHouseService{
    /**
     * Attribute which represents the DataAccess layer.
     */
    private final HouseRepository houseRepository;

    /**
     * Constructor which have the role to implement Dependency Injection for the houseRepository attribute.
     * @param houseRepository the reference to the DataAccess layer.
     */
    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    /**
     * This method is used for display purposes. You can see all the houses in the database.
     * @return all the houses in the database in a List.
     */
    @Override
    public List<House> getHouses() {
        return houseRepository.findAll();
    }

    /**
     * Creates a House in the database, given the data in the parameter.
     * @param house JSON with data for a house.
     */
    @Override
    public void createHouse(House house) {
        validateName(house.getName());
        houseRepository.save(house);
    }

    /**
     * First, this method checks to see if the database have a house with the specified id. If yes,
     * the house is being deleted, otherwise, an exception is thrown.
     * @param id the id of the House to be deleted.
     * @throws IllegalStateException if the database doesn't have a house with the specified id.
     */
    @Override
    public void deleteHouse(Long id) {
        boolean houseExists = houseRepository.existsById(id);
        if(!houseExists){
            throw new IllegalStateException(String.format("The house with id %s doesn't exist.", id));
        }
        houseRepository.deleteById(id);
    }

    /**
     * Checks if the database have a House called "name". If yes, then this method throws
     * an error with a message, if not, it does nothing.
     * @param name the name of a possible House.
     * @throws IllegalStateException if the database already have a house called "name".
     */
    private void validateName(String name){
        Optional<House> houseOptional = houseRepository.getHouseByname(name);
        if(houseOptional.isPresent()){
            throw new IllegalStateException(String.format("The house %s already exists", name));
        }
    }
}
