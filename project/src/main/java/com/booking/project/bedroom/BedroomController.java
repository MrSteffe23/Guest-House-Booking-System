package com.booking.project.bedroom;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This Class is the closest point from the user. It defines the end points of the
 * application and lets the {@link BedroomService} to deal with the logic behind this operations.<br>
 * All requests from the users are coming here first.
 */
@RestController
@RequestMapping("/api/v1/bedrooms")
public class BedroomController {
    private final IBedroomService bedroomService;

    /**
     * Constructor which have the role to implement Dependency Injection for the bedroomService attribute.
     * @param bedroomService the reference to the Service layer.
     */
    public BedroomController(IBedroomService bedroomService) {
        this.bedroomService = bedroomService;
    }

    /**
     * This method is used for display purposes, for debugging, or for statistics.
     * @return a list with all the Bedrooms in the database.
     */
    @GetMapping
    public List<Bedroom> getAllBedrooms(){
        return bedroomService.getAllBedrooms();
    }

    /**
     * This method is used for display purposes, or for debugging.
     * @param idHouse the id of the House whose Bedrooms I want to obtain.
     * @return a list with all the Bedrooms in the database, associated with a specified house by that id variable.
     */
    @GetMapping("/{idHouse}")
    public List<Bedroom> getBedrooms(@PathVariable Long idHouse){
        return bedroomService.getBedrooms(idHouse);
    }

    /**
     * Inserts a new Bedroom in the database, if possible.
     * @param bedroom JSON with all the data for a Bedroom
     */
    @PostMapping
    public void createBedroom(@RequestBody Bedroom bedroom){
        bedroomService.createBedroom(bedroom);
    }

    /**
     * Deletes a Bedroom from the database, if possible.
     * @param id the id of the Bedroom to be deleted.
     */
    @DeleteMapping("/{id}")
    public void deleteBedroom(@PathVariable Long id){
        bedroomService.deleteBedroom(id);
    }

    /**
     * Updates a Bedroom from the database with new specifications.
     * @param id the id of the Bedroom to be updated.
     * @param bedroom the Bedroom with new specifications.
     */
    @PutMapping("/{id}")
    public void updateBedroom(@PathVariable Long id, @RequestBody Bedroom bedroom){
        bedroomService.updateBedroom(bedroom, id);
    }
}
