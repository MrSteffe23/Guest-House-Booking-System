package com.booking.project;

import org.springframework.stereotype.Service;

import java.util.List;

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
}
