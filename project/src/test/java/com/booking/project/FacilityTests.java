package com.booking.project;
import com.booking.project.facility.Facility;
import com.booking.project.facility.FacilityRepository;
import com.booking.project.facility.FacilityService;
import com.booking.project.facility.IFacilityService;
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
import static org.mockito.Mockito.verify;

/**
 * This class is used to test the implementation of the class {@link com.booking.project.facility.FacilityService}
 */
@SpringBootTest
public class FacilityTests {
    @Mock
    private FacilityRepository facilityRepositoryMock;

    @Test
    void testGetAllFacilities() {
        List<Facility> facilitiesResult = new ArrayList<>();
        facilitiesResult.add(new Facility(1L, 1L, "foisor", "contine gratar, chiuveta, plita, dulap, masa"));
        facilitiesResult.add(new Facility(1L, 2L, "terasa de jocuri", "contine masa de tenis, dart"));
        List<Facility> facilitiesToBeReturned = new ArrayList<>(facilitiesResult);
        when(facilityRepositoryMock.findAll()).thenReturn(facilitiesToBeReturned);

        IFacilityService facilityService = new FacilityService(facilityRepositoryMock);
        assertEquals(facilityService.getAllFacilities(), facilitiesResult);
        verify(facilityRepositoryMock).findAll();
    }

    @Test
    void testGetFacilities() {
        Long id = 2L;
        List<Facility> facilitiesResult = new ArrayList<>();
        facilitiesResult.add(new Facility(1L, id, "foisor", "contine gratar, chiuveta, plita, dulap, masa"));
        facilitiesResult.add(new Facility(1L, id, "terasa de jocuri", "contine masa de tenis, dart"));
        List<Facility> facilitiesToBeReturned = new ArrayList<>(facilitiesResult);
        when(facilityRepositoryMock.findByIdHouse(id)).thenReturn(facilitiesToBeReturned);

        IFacilityService facilityService = new FacilityService(facilityRepositoryMock);
        assertEquals(facilityService.getFacilities(id), facilitiesResult);
        verify(facilityRepositoryMock).findByIdHouse(id);
    }

    @Test
    void testCreateFacility() {
        Facility facility = new Facility(1L, 2L, "foisor", "contine gratar, chiuveta, plita, dulap, masa");

        IFacilityService facilityService = new FacilityService(facilityRepositoryMock);
        facilityService.createFacility(facility);
        verify(facilityRepositoryMock).save(facility);
    }

    @Test
    void testDeleteFacility() {
        Long id = 2L;
        Facility facility = new Facility(1L, id, "foisor", "contine gratar, chiuveta, plita, dulap, masa");
        when(facilityRepositoryMock.findById(id)).thenReturn(Optional.of(facility));

        IFacilityService facilityService = new FacilityService(facilityRepositoryMock);
        facilityService.deleteFacility(id);
        verify(facilityRepositoryMock).findById(id);
        verify(facilityRepositoryMock).deleteById(id);
    }

    @Test
    void testUpdateFacility() {
        Long id = 2L;
        Facility facilityToBeUpdated = new Facility(1L, id, "foisor", "contine gratar, chiuveta, plita, dulap, masa");
        Optional<Facility> facilityOptional = Optional.ofNullable(facilityToBeUpdated);
        Facility newFacility = new Facility(1L, id, "terasa de jocuri", "contine masa de tenis, dart");
        when(facilityRepositoryMock.findById(id)).thenReturn(facilityOptional);

        IFacilityService facilityService = new FacilityService(facilityRepositoryMock);
        facilityService.updateFacility(id, newFacility);
        assertEquals(facilityToBeUpdated, newFacility);//verifying if the new review was updated
        verify(facilityRepositoryMock, times(2)).findById(id);
        verify(facilityRepositoryMock).save(facilityToBeUpdated);
    }

    @Test
    void testCheckValidIdFacility(){
        Long id = 2L;
        Facility facility = new Facility(1L, id, "terasa de jocuri", "contine masa de tenis, dart");
        when(facilityRepositoryMock.findById(id)).thenReturn(Optional.of(facility));

        FacilityService facilityService = new FacilityService(facilityRepositoryMock);
        facilityService.checkValidFacility(id);
        verify(facilityRepositoryMock).findById(id);
    }

    @Test
    void testCheckInvalidIdFacility(){
        Long id = 2L;
        Facility facility = new Facility(1L, id, "terasa de jocuri", "contine masa de tenis, dart");
        when(facilityRepositoryMock.findById(id)).thenReturn(Optional.empty());

        FacilityService facilityService = new FacilityService(facilityRepositoryMock);

        try {
            facilityService.checkValidFacility(id);
        } catch (Exception e) {
            // Verify that the exception is thrown
            assertNotNull(e);
            verify(facilityRepositoryMock).findById(id);
            return;
        }
        // If no exception is thrown, fail the test
        fail("Expected exception was not thrown.");
    }
}
