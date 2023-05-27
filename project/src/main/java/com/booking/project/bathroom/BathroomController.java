package com.booking.project.bathroom;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This Class is the closest point from the user. It defines the end points of the
 * application and lets the {@link BathroomService} to deal with the logic behind this operations.<br>
 * All requests from the users are coming here first.
 */
@RestController
@RequestMapping("/api/v1/bathrooms")
public class BathroomController {
    private final IBathroomService bathroomService;

    /**
     * Constructor which have the role to implement Dependency Injection for the bathroomService attribute.
     * @param bathroomService the reference to the Service layer.
     */
    public BathroomController(IBathroomService bathroomService) {
        this.bathroomService = bathroomService;
    }

    /**
     * This method is used for display purposes, for debugging, or for statistics.
     * @return a list with all the Bathrooms in the database.
     */
    @GetMapping
    public List<Bathroom> getAllBathrooms(){
        return bathroomService.getAllBathrooms();
    }

    /**
     * This method is used for display purposes, or for debugging.
     * @param idHouse the id of the House whose Bathrooms I want to obtain.
     * @return a list with all the Bathrooms in the database, associated with a specified house by that id variable.
     */
    @GetMapping("/{idHouse}")
    public List<Bathroom> getBathrooms(@PathVariable Long idHouse){
        return bathroomService.getBathrooms(idHouse);
    }

    /**
     * Inserts a new Bathroom in the database, if possible.
     * @param bathroom JSON with all the data for a Bathroom
     */
    @PostMapping
    public void createBathroom(@RequestBody Bathroom bathroom){
        bathroomService.createBathroom(bathroom);
    }

    /**
     * Deletes a Bathroom from the database, if possible.
     * @param id the id of the Bathroom to be deleted.
     */
    @DeleteMapping("/{id}")
    public void deleteBathroom(@PathVariable Long id){
        bathroomService.deleteBathroom(id);
    }

    /**
     * Updates a Bathroom from the database with new specifications.
     * @param id the id of the Bathroom to be updated.
     * @param bathroom the Bathroom with new specifications.
     */
    @PutMapping("/{id}")
    public void updateBathroom(@PathVariable Long id, @RequestBody Bathroom bathroom){
        bathroomService.updateBathroom(bathroom, id);
    }
}
