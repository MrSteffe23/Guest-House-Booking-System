package com.booking.project;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class HouseService implements IHouseService{
    private final HouseRepository houseRepository;

    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    @Override
    public List<House> getHouses() {
        return houseRepository.findAll();
    }

    @Override
    public void createHouse(House house) {
        validateName(house.getName());
        houseRepository.save(house);
    }

    private void validateName(String name){
        Optional<House> houseOptional = houseRepository.getHouseByname(name);
        if(houseOptional.isPresent()){
            throw new IllegalStateException(String.format("The house %s already exists", name));
        }
    }
}
