package com.booking.project;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/houses")
public class HouseController {
    private final IHouseService houseService;

    public HouseController(IHouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping()
    public List<House> getHouses(){
        return houseService.getHouses();
    }

    @PostMapping()
    public void createHouse(@RequestBody House house){
        houseService.createHouse(house);
    }
}
