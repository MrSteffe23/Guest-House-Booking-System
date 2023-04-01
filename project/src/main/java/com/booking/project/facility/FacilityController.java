package com.booking.project.facility;

import com.booking.project.house.HouseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This Class is the closest point from the user. It defines the end points of the
 * application and lets the {@link FacilityService} to deal with the logic behind this operations.<br>
 * All requests from the users are coming here first.
 */
@RestController
@RequestMapping("/api/v1/facilities")
public class FacilityController {
    private final IFacilityService facilityService;

    /**
     * Constructor which have the role to implement Dependency Injection for the facilityService attribute.
     * @param facilityService the reference to the Service layer.
     */
    public FacilityController(IFacilityService facilityService) {
        this.facilityService = facilityService;
    }

    /**
     * This method is used for display purposes, for debugging, or for statistics.
     * @return a list with all the Facilities in the database.
     */
    @GetMapping()
    public List<Facility> getAllFacilities(){
        return facilityService.getAllFacilities();
    }

    /**
     * Inserts a new Facility in the database, if possible.
     * @param facility JSON with all the data for a Facility
     * @param id_house the id of the house which gets a new facility
     */
    @PostMapping("/{id_house}")
    public void createFacility(@RequestBody Facility facility,@PathVariable Long id_house){
        facilityService.createFacility(facility, id_house);
    }

    /**
     * Deletes a Facility from the database, if possible.
     * @param id the id of the Facility to be deleted.
     */
    @DeleteMapping("/{id}")
    public void deleteFacility(@PathVariable Long id){
        facilityService.deleteFacility(id);
    }

    /**
     * Updates a Facility from the database with new specifications.
     * @param id the id of the Facility to be updated.
     * @param facility the Facility with new specifications.
     */
    @PutMapping("/{id}")
    public void updateFacility(@PathVariable Long id, @RequestBody Facility facility){
        facilityService.updateFacility(id, facility);
    }

}
