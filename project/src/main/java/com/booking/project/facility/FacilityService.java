package com.booking.project.facility;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Class which implements the contract of the interface {@link IFacilityService}. <br>
 * It has the implementation of the methods for PostgreSQL database.
 * Here is all the logic of the application.
 */
@Service
public class FacilityService implements IFacilityService{
    /**
     * Attribute which represents the DataAccess layer.
     */
    private final FacilityRepository facilityRepository;

    /**
     * Constructor which have the role to implement Dependency Injection for the facilityRepository attribute.
     * @param facilityRepository the reference to the DataAccess layer.
     */
    public FacilityService(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    /**
     * This method is used for display purposes, or for statistics. You can see all the facilities in the database, for
     * every house you want.
     * @return all the houses in the database in a List.
     */
    @Override
    public List<Facility> getAllFacilities() {
        return facilityRepository.findAll();
    }

    /**
     * This method is used for display purposes, or for statistics. You can see all the facilities in the database, for
     * a specific house given by an id
     * @param id_house the id of the House whose facilities you want to get
     * @return all the houses in the database in a List.
     */
    @Override
    public List<Facility> getFacilities(Long id_house) {
        return facilityRepository.findByIdHouse(id_house);
    }

    /**
     * Creates a Facility in the database, given the data in the parameter.
     * @param facility JSON with data for a facility.
     * @param id_house id for the house which gets a new facility (this "id" must come from a valid house)
     */
    @Override
    public void createFacility(Facility facility, Long id_house) {
        facilityRepository.save(facility);
    }

    /**
     * First, this method checks to see if the database have a facility with the specified id. If yes,
     * the facility is being deleted, otherwise, an exception is thrown.
     * @param id the id of the Facility to be deleted.
     * @throws IllegalStateException if the database doesn't have a facility with the specified id.
     */
    @Override
    public void deleteFacility(Long id) {
        checkValidFacility(id);
        facilityRepository.deleteById(id);
    }

    /**
     * First, this method checks to see if a Facility with the specified id exists, so that it can be updated.<br>
     * Then, the Facility extracted with the specified id is updated and then saved in the database.
     * @param id the id of the Facility to be updated.
     * @param facility the Facility with new specifications.
     */
    @Override
    public void updateFacility(Long id, Facility facility) {
        checkValidFacility(id);
        Facility facilityToUpdate = facilityRepository.findById(id).get();

        facilityToUpdate.setFacilityName(facility.getFacilityName());
        facilityToUpdate.setDetails(facilityToUpdate.getDetails());
        facilityToUpdate.setIdHouse(facility.getId());

        facilityRepository.save(facilityToUpdate);
    }

    /**
     * Functions which verifies if the database has a facility with the specified id
     * @param id id of the facility that you want to look for
     */
    private void checkValidFacility(Long id){
        Optional<Facility> facilityOptional = facilityRepository.findById(id);
        if(!facilityOptional.isPresent()){
            throw new IllegalStateException(String.format("The Facility with id %s doesn't exist.", id));
        }
    }
}
