package com.booking.project.bedroom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bedrooms")
public class BedroomController {
    private final IBedroomService bedroomService;

    public BedroomController(IBedroomService bedroomService) {
        this.bedroomService = bedroomService;
    }

    @GetMapping
    public List<Bedroom> getAllBedrooms(){
        return bedroomService.getAllBedrooms();
    }

    @GetMapping("/{idHouse}")
    public List<Bedroom> getBedrooms(@PathVariable Long idHouse){
        return bedroomService.getBedrooms(idHouse);
    }
}
