package com.booking.project;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
