package com.booking.project.bedroom;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BedroomService implements IBedroomService{

    private final BedroomRepository bedroomRepository;

    public BedroomService(BedroomRepository bedroomRepository) {
        this.bedroomRepository = bedroomRepository;
    }

    @Override
    public List<Bedroom> getAllBedrooms() {
        return null;
    }

    @Override
    public List<Bedroom> getBedrooms(Long idHouse) {
        return null;
    }

    @Override
    public void createBedroom(Bedroom bedroom) {

    }

    @Override
    public void deleteBedroom(Long id) {

    }

    @Override
    public void updateBedroom(Bedroom bedroom, Long id) {

    }
}
