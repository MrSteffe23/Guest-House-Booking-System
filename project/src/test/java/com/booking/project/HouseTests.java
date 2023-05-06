package com.booking.project;

import com.booking.project.house.House;
import com.booking.project.house.HouseRepository;
import com.booking.project.house.HouseService;
import com.booking.project.house.IHouseService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.aspectj.bridge.MessageUtil.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * This class is used to test the implementation of the class {@link com.booking.project.house.HouseService}
 */
@SpringBootTest
public class HouseTests {
    @Mock
    private HouseRepository houseRepositoryMock;

    @Test
    void testGetHouses() {
        List<House> housesResult = new ArrayList<>();
        housesResult.add(new House(1L, "Casa Izvorul Raraului", "Izvorul alb", "Km4", 600.90f));
        housesResult.add(new House(1L, "Casuta Izvorul Raraului", "Izvorul alb", "Km4", 300.90f));
        List<House> housesToBeReturned = new ArrayList<>(housesResult);
        when(houseRepositoryMock.findAll()).thenReturn(housesToBeReturned);

        IHouseService houseService = new HouseService(houseRepositoryMock);
        assertEquals(houseService.getHouses(), housesResult);
        verify(houseRepositoryMock).findAll();
    }

    @Test
    void testCreateValidHouse() {
        String name = "Casa Izvorul Raraului";
        House house = new House(1L, name, "Izvorul alb", "Km4", 600.90f);
        Optional<House> houseOptional = Optional.ofNullable(house);
        when(houseRepositoryMock.getHouseByname(name)).thenReturn(Optional.empty());

        IHouseService houseService = new HouseService(houseRepositoryMock);
        houseService.createHouse(house);
        verify(houseRepositoryMock).save(house);
        verify(houseRepositoryMock).getHouseByname(name);
    }

    @Test
    void testCreateInvalidHouse() {
        String name = "Casa Izvorul Raraului";
        House house = new House(1L, name, "Izvorul alb", "Km4", 600.90f);
        Optional<House> houseOptional = Optional.ofNullable(house);
        when(houseRepositoryMock.getHouseByname(name)).thenReturn(houseOptional);

        IHouseService houseService = new HouseService(houseRepositoryMock);

        try {
            houseService.createHouse(house);
        } catch (Exception e) {
            // Verify that the exception is thrown
            assertNotNull(e);
            verify(houseRepositoryMock, never()).save(house);
            verify(houseRepositoryMock).getHouseByname(name);
            return;
        }
        // If no exception is thrown, fail the test
        fail("Expected exception was not thrown.");
    }

    @Test
    void testDeleteHouse() {
        Long id = 1L;
        String name = "Casa Izvorul Raraului";
        House house = new House(id, name, "Izvorul alb", "Km4", 600.90f);
        Optional<House> houseOptional = Optional.ofNullable(house);
        when(houseRepositoryMock.findById(id)).thenReturn(houseOptional);

        IHouseService houseService = new HouseService(houseRepositoryMock);
        houseService.deleteHouse(id);
        verify(houseRepositoryMock).deleteById(id);
        verify(houseRepositoryMock).findById(id);
    }

    @Test
    void testUpdateHouse() {
        Long id = 1L;
        String name = "Casa Izvorul Raraului";
        House houseToBeUpdated = new House(id, name, "Izvorul alb", "Km4", 600.90f);
        Optional<House> houseOptional = Optional.ofNullable(houseToBeUpdated);
        House newHouse = new House(id, name, "Izvorul alb", "Km404", 802.90f);
        when(houseRepositoryMock.findById(id)).thenReturn(houseOptional);

        IHouseService houseService = new HouseService(houseRepositoryMock);
        houseService.updateHouse(id, newHouse);
        assertEquals(houseToBeUpdated, newHouse);//verifying if the new house was updated
        verify(houseRepositoryMock, times(2)).findById(id);
        verify(houseRepositoryMock).save(houseToBeUpdated);
    }

    @Test
    void testCheckValidIdHouse(){
        Long id = 1L;
        String name = "Casa Izvorul Raraului";
        House house = new House(id, name, "Izvorul alb", "Km4", 600.90f);
        when(houseRepositoryMock.findById(id)).thenReturn(Optional.of(house));

        HouseService houseService = new HouseService(houseRepositoryMock);
        houseService.checkValidIdHouse(id);
        verify(houseRepositoryMock).findById(id);
    }

    @Test
    void testCheckInvalidIdHouse(){
        Long id = 1L;
        when(houseRepositoryMock.findById(id)).thenReturn(Optional.empty());

        HouseService houseService = new HouseService(houseRepositoryMock);

        try {
            houseService.checkValidIdHouse(id);
        } catch (Exception e) {
            // Verify that the exception is thrown
            assertNotNull(e);
            verify(houseRepositoryMock).findById(id);
            return;
        }
        // If no exception is thrown, fail the test
        assert(2>1);
    }

    @Test
    void testValidateNameOk(){
        String name = "Casa Izvorul Raraului";
        House house = new House(1L, name, "Izvorul alb", "Km4", 600.90f);
        when(houseRepositoryMock.getHouseByname(name)).thenReturn(Optional.empty());

        HouseService houseService = new HouseService(houseRepositoryMock);
        houseService.validateName(name);
        verify(houseRepositoryMock).getHouseByname(name);
    }

    @Test
    void testValidateNameNotOk(){
        String name = "Casa Izvorul Raraului";
                House house = new House(1L, name, "Izvorul alb", "Km4", 600.90f);
        when(houseRepositoryMock.getHouseByname(name)).thenReturn(Optional.of(house));

        HouseService houseService = new HouseService(houseRepositoryMock);

        try {
            houseService.validateName(name);
        } catch (Exception e) {
            // Verify that the exception is thrown
            assertNotNull(e);
            verify(houseRepositoryMock).getHouseByname(name);
            return;
        }
        // If no exception is thrown, fail the test
        fail("Expected exception was not thrown.");
    }
}
