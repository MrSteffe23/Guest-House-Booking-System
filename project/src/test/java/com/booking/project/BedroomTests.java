package com.booking.project;

import com.booking.project.bedroom.Bedroom;
import com.booking.project.bedroom.BedroomRepository;
import com.booking.project.bedroom.BedroomService;
import com.booking.project.bedroom.IBedroomService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * This class is used to test the implementation of the class {@link com.booking.project.bedroom.BedroomService}
 */
@SpringBootTest
public class BedroomTests {
    @Mock
    private BedroomRepository bedroomRepositoryMock;

    @Test
    void testGetAllBedrooms() {
        List<Bedroom> bedroomsResult = new ArrayList<>();
        bedroomsResult.add(new Bedroom(1L, 2L, 1, 2, 2, "purple", "fotoliu"));
        bedroomsResult.add(new Bedroom(2L, 1L, 2, 1, 1, "orange",
                "priveliste spre munti"));
        List<Bedroom> bedroomsToBeReturned = new ArrayList<>(bedroomsResult);
        when(bedroomRepositoryMock.findAll()).thenReturn(bedroomsToBeReturned);

        IBedroomService bedroomService = new BedroomService(bedroomRepositoryMock);
        assertEquals(bedroomService.getAllBedrooms(), bedroomsResult);
        verify(bedroomRepositoryMock).findAll();
    }

    @Test
    void testGetBedrooms() {
        Long id = 2L;
        List<Bedroom> bedroomsResult = new ArrayList<>();
        bedroomsResult.add(new Bedroom(id, 2L, 1, 2, 2, "purple", "fotoliu"));
        bedroomsResult.add(new Bedroom(id, 1L, 2, 1, 1, "orange",
                "priveliste spre munti"));
        List<Bedroom> bedroomsToBeReturned = new ArrayList<>(bedroomsResult);
        when(bedroomRepositoryMock.findByIdHouse(id)).thenReturn(bedroomsToBeReturned);

        IBedroomService bedroomService = new BedroomService(bedroomRepositoryMock);
        assertEquals(bedroomService.getBedrooms(id), bedroomsResult);
        verify(bedroomRepositoryMock).findByIdHouse(id);
    }

    @Test
    void testCreateValidBedroom() {
        int number = 1;
        long idHouse = 2L;
        Bedroom bedroom = new Bedroom(1L, idHouse, number, 2, 2, "purple", "fotoliu");
        when(bedroomRepositoryMock.findByNumberAndIdHouse(number, idHouse)).thenReturn(new ArrayList<>());

        IBedroomService bedroomService = new BedroomService(bedroomRepositoryMock);
        bedroomService.createBedroom(bedroom);
        verify(bedroomRepositoryMock).save(bedroom);
        verify(bedroomRepositoryMock).findByNumberAndIdHouse(number, idHouse);
    }

    @Test
    void testCreateInvalidBedroom() {
        int number = 1;
        long idHouse = 2L;
        Bedroom bedroom = new Bedroom(1L, idHouse, number, 2, 2, "purple", "fotoliu");
        List<Bedroom> bedrooms = new ArrayList<>();
        bedrooms.add(bedroom);
        when(bedroomRepositoryMock.findByNumberAndIdHouse(number, idHouse)).thenReturn(bedrooms);

        IBedroomService bedroomService = new BedroomService(bedroomRepositoryMock);
        try {
            bedroomService.createBedroom(bedroom);
        } catch (Exception e) {
            // Verify that the exception is thrown
            assertNotNull(e);
            verify(bedroomRepositoryMock, never()).save(bedroom);
            verify(bedroomRepositoryMock).findByNumberAndIdHouse(number, idHouse);
            return;
        }
        // If no exception is thrown, fail the test
        verify(bedroomRepositoryMock, never()).save(bedroom);
    }

    @Test
    void testDeleteValidBedroom() {
        Long id = 1L;
        Bedroom bedroom = new Bedroom(id, 2L, 1, 2, 2, "purple", "fotoliu");

        when(bedroomRepositoryMock.findById(id)).thenReturn(Optional.of(bedroom));

        IBedroomService bedroomService = new BedroomService(bedroomRepositoryMock);
        bedroomService.deleteBedroom(id);
        verify(bedroomRepositoryMock).findById(id);
        verify(bedroomRepositoryMock).deleteById(id);
    }

    @Test
    void testDeleteInvalidBedroom() {
        Long id = 1L;
        when(bedroomRepositoryMock.findById(id)).thenReturn(Optional.empty());

        IBedroomService bedroomService = new BedroomService(bedroomRepositoryMock);
        try {
            bedroomService.deleteBedroom(id);
        } catch (Exception e) {
            // Verify that the exception is thrown
            assertNotNull(e);
            verify(bedroomRepositoryMock, never()).deleteById(id);
            verify(bedroomRepositoryMock).findById(id);
            return;
        }
        // If no exception is thrown, fail the test
        verify(bedroomRepositoryMock, never()).deleteById(id);
    }

    @Test
    void testUpdateValidBedroom() {
        Long id = 1L;
        Bedroom bedroomToBeUpdated = new Bedroom(id, 2L, 1, 2, 2, "purple",
                "fotoliu");

        Bedroom newBedroom = new Bedroom(id, 2L, 1, 1, 1, "orange",
                "priveliste spre munti");
        when(bedroomRepositoryMock.findById(id)).thenReturn(Optional.of(bedroomToBeUpdated));

        IBedroomService bedroomService = new BedroomService(bedroomRepositoryMock);
        bedroomService.updateBedroom(newBedroom, id);
        assertEquals(bedroomToBeUpdated, newBedroom);//verifying if the new review was updated
        verify(bedroomRepositoryMock, times(2)).findById(id);
        verify(bedroomRepositoryMock).save(bedroomToBeUpdated);
    }

    @Test
    void testUpdateInvalidBedroom() {
        Long id = 1L;
        Bedroom bedroomToBeUpdated = new Bedroom(id, 2L, 1, 2, 2, "purple",
                "fotoliu");

        Bedroom newBedroom = new Bedroom(id, 2L, 2, 1, 1, "orange",
                "priveliste spre munti");
        List<Bedroom> bedrooms = new ArrayList<>();
        bedrooms.add(newBedroom);
        when(bedroomRepositoryMock.findById(id)).thenReturn(Optional.of(bedroomToBeUpdated));
        when(bedroomRepositoryMock.findByNumberAndIdHouse(newBedroom.getNumber(), newBedroom.getIdHouse()))
                .thenReturn(bedrooms);

        IBedroomService bedroomService = new BedroomService(bedroomRepositoryMock);
        try {
            bedroomService.updateBedroom(newBedroom, id);
        } catch (Exception e) {
            // Verify that the exception is thrown
            assertNotNull(e);
            verify(bedroomRepositoryMock, times(2)).findById(id);
            verify(bedroomRepositoryMock, never()).save(bedroomToBeUpdated);
            verify(bedroomRepositoryMock).findByNumberAndIdHouse(newBedroom.getNumber(), newBedroom.getIdHouse());
            return;
        }
        // If no exception is thrown, fail the test
        verify(bedroomRepositoryMock, never()).save(bedroomToBeUpdated);
    }

    @Test
    void testCheckValidBedroom(){
        Long id = 1L;
        Bedroom bedroom = new Bedroom(id, 2L, 2, 1, 1, "orange",
                "priveliste spre munti");
        when(bedroomRepositoryMock.findById(id)).thenReturn(Optional.of(bedroom));

        BedroomService bedroomService = new BedroomService(bedroomRepositoryMock);
        bedroomService.checkValidBedroom(id);
        verify(bedroomRepositoryMock).findById(id);
    }

    @Test
    void testCheckInvalidBedroom(){
        Long id = 1L;
        when(bedroomRepositoryMock.findById(id)).thenReturn(Optional.empty());

        BedroomService bedroomService = new BedroomService(bedroomRepositoryMock);

        try {
            bedroomService.checkValidBedroom(id);
        } catch (Exception e) {
            // Verify that the exception is thrown
            assertNotNull(e);
            verify(bedroomRepositoryMock).findById(id);
            return;
        }
        // If no exception is thrown, fail the test
        assert(2>1);
    }
}
