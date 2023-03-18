package com.booking.project;

import java.util.List;

public interface IHouseService {
    List<House> getHouses();
    void createHouse(House house);
}
