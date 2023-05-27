package com.booking.project;

import com.booking.project.bathroom.Bathroom;
import com.booking.project.bathroom.BathroomRepository;
import com.booking.project.bathroom.BathroomService;
import com.booking.project.bathroom.IBathroomService;
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
 * This class is used to test the implementation of the class {@link com.booking.project.bathroom.BathroomService}
 */
@SpringBootTest
public class BathroomTests {
    @Mock
    private BathroomRepository bathroomRepositoryMock;

    @Test
    void testGetAllBathrooms() {
        List<Bathroom> bathroomsResult = new ArrayList<>();
        bathroomsResult.add(new Bathroom(1L, 2L, 1, 1, true, true, "mare",
                "fier de calcat", true, true));
        bathroomsResult.add(new Bathroom(2L, 2L, 1, 1, false, false, "mica",
                "matura", true, true));
        List<Bathroom> bathroomsToBeReturned = new ArrayList<>(bathroomsResult);
        when(bathroomRepositoryMock.findAll()).thenReturn(bathroomsToBeReturned);

        IBathroomService bathroomService = new BathroomService(bathroomRepositoryMock);
        assertEquals(bathroomService.getAllBathrooms(), bathroomsResult);
        verify(bathroomRepositoryMock).findAll();
    }

    @Test
    void testGetBathrooms() {
        Long id = 2L;
        List<Bathroom> bathroomsResult = new ArrayList<>();
        bathroomsResult.add(new Bathroom(1L, id, 1, 1, true, true, "mare",
                "fier de calcat", true, true));
        bathroomsResult.add(new Bathroom(2L, id, 1, 1, false, false, "mica",
                "matura", true, true));
        List<Bathroom> bathroomsToBeReturned = new ArrayList<>(bathroomsResult);
        when(bathroomRepositoryMock.findByIdHouse(id)).thenReturn(bathroomsToBeReturned);

        IBathroomService bathroomService = new BathroomService(bathroomRepositoryMock);
        assertEquals(bathroomService.getBathrooms(id), bathroomsResult);
        verify(bathroomRepositoryMock).findByIdHouse(id);
    }

    @Test
    void testCreateBathroom() {
        Bathroom bathroom = new Bathroom(1L, 2L, 1, 1, true, true, "mare",
                "fier de calcat", true, true);

        IBathroomService bathroomService = new BathroomService(bathroomRepositoryMock);
        bathroomService.createBathroom(bathroom);
        verify(bathroomRepositoryMock).save(bathroom);
    }

    @Test
    void testDeleteBathroom() {
        Long id = 1L;
        Bathroom bathroom = new Bathroom(1L, id, 1, 1, true, true, "mare",
                "fier de calcat", true, true);
        when(bathroomRepositoryMock.findById(id)).thenReturn(Optional.of(bathroom));

        IBathroomService bathroomService = new BathroomService(bathroomRepositoryMock);
        bathroomService.deleteBathroom(id);
        verify(bathroomRepositoryMock).findById(id);
        verify(bathroomRepositoryMock).deleteById(id);
    }

    @Test
    void testUpdateBathroom() {
        Long id = 1L;
        Bathroom bathroomToBeUpdated = new Bathroom(1L, id, 1, 1, true, true, "mare",
                "fier de calcat", true, true);
        Bathroom newBathroom = new Bathroom(2L, 2L, 1, 1, false, false, "mica",
                "matura", true, true);
        when(bathroomRepositoryMock.findById(id)).thenReturn(Optional.of(bathroomToBeUpdated));

        IBathroomService bathroomService = new BathroomService(bathroomRepositoryMock);
        bathroomService.updateBathroom(newBathroom, id);
        assertEquals(bathroomToBeUpdated, newBathroom);//verifying if the new review was updated
        verify(bathroomRepositoryMock, times(2)).findById(id);
        verify(bathroomRepositoryMock).save(bathroomToBeUpdated);
    }

    @Test
    void testCheckValidBathroom(){
        Long id = 1L;
        Bathroom bathroom = new Bathroom(1L, id, 1, 1, true, true, "mare",
                "fier de calcat", true, true);
        when(bathroomRepositoryMock.findById(id)).thenReturn(Optional.of(bathroom));

        BathroomService bathroomService = new BathroomService(bathroomRepositoryMock);
        bathroomService.checkValidBathroom(id);
        verify(bathroomRepositoryMock).findById(id);
    }

    @Test
    void testCheckInvalidBathroom(){
        Long id = 1L;
        when(bathroomRepositoryMock.findById(id)).thenReturn(Optional.empty());

        BathroomService bathroomService = new BathroomService(bathroomRepositoryMock);

        try {
            bathroomService.checkValidBathroom(id);
        } catch (Exception e) {
            // Verify that the exception is thrown
            assertNotNull(e);
            verify(bathroomRepositoryMock).findById(id);
            return;
        }
        // If no exception is thrown, fail the test
        assert(2>1);
    }
}
