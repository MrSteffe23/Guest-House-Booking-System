package com.booking.project;

import com.booking.project.facility.FacilityRepository;
import com.booking.project.review.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * This class is used to test the implementation of the class {@link com.booking.project.facility.FacilityService}
 */
@SpringBootTest
public class FacilityTests {
    @Mock
    private FacilityRepository facilityRepositoryMock;

    @Test
    void testGetAllFacilities() {

    }

}
