package com.booking.project.house;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This Class is the closest point from the user. It defines the end points of the
 * application and lets the {@link HouseService} to deal with the logic behind this operations.<br>
 * All requests from the users are coming here first.
 */
@RestController
@RequestMapping("/api/v1/houses")
public class HouseController {
    private final IHouseService houseService;

    /**
     * Constructor which have the role to implement Dependency Injection for the houseService attribute.
     * @param houseService the reference to the Service layer.
     */
    public HouseController(IHouseService houseService) {
        this.houseService = houseService;
    }

    /**
     * This method is used for display purposes, or for debugging.
     * @return a list with all the Houses in the database.
     */
    @GetMapping()
    public List<House> getHouses(){
        return houseService.getHouses();
    }

    /**
     * This method is used for display purposes, or for debugging.
     * @return a House given by an id
     */
    @GetMapping("/{id}")
    public House getHouse(@PathVariable Long id){
        return houseService.getHouse(id);
    }

    /**
     * Inserts a new House in the database, if possible.
     * @param house JSON with all the data for a House
     */
    @PostMapping()
    public void createHouse(@RequestBody House house){
        houseService.createHouse(house);
    }

    /**
     * Deletes a House from the database, if possible.
     * @param id the id of the House to be deleted.
     */
    @DeleteMapping("/{id}")
    public void deleteHouse(@PathVariable Long id){
        houseService.deleteHouse(id);
    }

    /**
     * Updates a House from the database with new specifications.
     * @param id the id of the House to be updated.
     * @param house the House with new specifications.
     */
    @PutMapping("/{id}")
    public void updateHouse(@PathVariable Long id,@RequestBody House house){
        houseService.updateHouse(id, house);
    }
}
