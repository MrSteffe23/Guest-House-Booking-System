package com.booking.project.bedroom;

import java.util.List;

public interface IBedroomService {
    List<Bedroom> getAllBedrooms();

    List<Bedroom> getBedrooms(Long idHouse);

    void createBedroom(Bedroom bedroom);

    void deleteBedroom(Long id);

    void updateBedroom(Bedroom bedroom, Long id);

}
