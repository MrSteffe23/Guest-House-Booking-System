package com.booking.project.house;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * This Interface is responsible for the lower level logic.<br>
 * Here are all the methods used for interacting directly with the database.
 * Spring does all the work behind this methods.<br>
 * Here you can define queries, or use some predefined methods.
 */
public interface HouseRepository extends JpaRepository<House, Long> {
    /**
     * Custom method which gets a specific house from the database
     * @param name The name of the house that I want to get from the database
     * @return A house from the database
     */
    Optional<House> getHouseByname(String name);
}
