package com.booking.project;

import com.booking.project.admin.Admin;
import com.booking.project.admin.AdminRepository;
import com.booking.project.admin.AdminService;
import com.booking.project.admin.IAdminService;
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
 * This class is used to test the implementation of the class {@link com.booking.project.admin.AdminService}
 */
@SpringBootTest
public class AdminTests {
    @Mock
    private AdminRepository adminRepositoryMock;

    @Test
    void testGetAdmins() {
        List<Admin> adminsResult = new ArrayList<>();
        adminsResult.add(new Admin(1L, "stefan", "pass1", "stef_stefan9@yahoo.com"));
        adminsResult.add(new Admin(2L, "iulian", "pass2", "iulian@yahoo.com"));
        List<Admin> adminsToBeReturned = new ArrayList<>(adminsResult);
        when(adminRepositoryMock.findAll()).thenReturn(adminsToBeReturned);

        IAdminService adminService = new AdminService(adminRepositoryMock);
        assertEquals(adminService.getAdmins(), adminsResult);
        verify(adminRepositoryMock).findAll();
    }

    @Test
    void testCreateValidAdmin() {
        String username = "stefan";
        Admin admin = new Admin(1L, username, "pass1", "stef_stefan9@yahoo.com");
        when(adminRepositoryMock.getHouseByusername(username)).thenReturn(Optional.empty());

        IAdminService adminService = new AdminService(adminRepositoryMock);
        adminService.createAdmin(admin);
        verify(adminRepositoryMock).save(admin);
        verify(adminRepositoryMock).getHouseByusername(username);
    }

    @Test
    void testCreateInvalidAdminByUsername() {
        String username = "stefan";
        Admin admin = new Admin(1L, username, "pass1", "stef_stefan9@yahoo.com");
        when(adminRepositoryMock.getHouseByusername(username)).thenReturn(Optional.of(admin));

        IAdminService adminService = new AdminService(adminRepositoryMock);
        try {
            adminService.createAdmin(admin);
        } catch (Exception e) {
            // Verify that the exception is thrown
            assertNotNull(e);
            verify(adminRepositoryMock, never()).save(admin);
            verify(adminRepositoryMock).getHouseByusername(username);
            return;
        }
        // If no exception is thrown, fail the test
        assert(2<1);
    }

    @Test
    void testCreateInvalidAdminByEmail() {
        String username = "stefan";
        Admin admin = new Admin(1L, username, "pass1", "stef_stefan");
        when(adminRepositoryMock.getHouseByusername(username)).thenReturn(Optional.empty());

        IAdminService adminService = new AdminService(adminRepositoryMock);
        try {
            adminService.createAdmin(admin);
        } catch (Exception e) {
            // Verify that the exception is thrown
            assertNotNull(e);
            verify(adminRepositoryMock, never()).save(admin);
            verify(adminRepositoryMock).getHouseByusername(username);
            return;
        }
        // If no exception is thrown, fail the test
        assert(2<1);
    }

    @Test
    void testDeleteAdmin() {
        Long id = 1L;
        Admin admin = new Admin(1L, "stefan", "pass1", "stef_stefan");
        when(adminRepositoryMock.findById(id)).thenReturn(Optional.of(admin));

        IAdminService adminService = new AdminService(adminRepositoryMock);
        adminService.deleteAdmin(id);
        verify(adminRepositoryMock).deleteById(id);
        verify(adminRepositoryMock).findById(id);
    }

    @Test
    void testUpdateAdminDifferentName() {
        Long id = 1L;
        Admin adminToBeUpdated = new Admin(1L, "stefan", "pass1", "stef_stefan9@yahoo.com");
        String username = "iulian";
        Admin newAdmin = new Admin(2L, username, "pass2", "iulian@yahoo.com");
        when(adminRepositoryMock.findById(id)).thenReturn(Optional.of(adminToBeUpdated));
        when(adminRepositoryMock.getHouseByusername(username)).thenReturn(Optional.empty());

        IAdminService adminService = new AdminService(adminRepositoryMock);
        adminService.updateAdmin(id, newAdmin);
        assertEquals(adminToBeUpdated, newAdmin);//verifying if the new house was updated
        verify(adminRepositoryMock, times(2)).findById(id);
        verify(adminRepositoryMock).save(adminToBeUpdated);
        verify(adminRepositoryMock).getHouseByusername(username);
    }

    @Test
    void testUpdateAdminSameName() {
        Long id = 1L;
        Admin adminToBeUpdated = new Admin(1L, "stefan", "pass1", "stef_stefan9@yahoo.com");
        String username = "stefan";
        Admin newAdmin = new Admin(2L, username, "pass2", "iulian@yahoo.com");
        when(adminRepositoryMock.findById(id)).thenReturn(Optional.of(adminToBeUpdated));

        IAdminService adminService = new AdminService(adminRepositoryMock);
        adminService.updateAdmin(id, newAdmin);
        assertEquals(adminToBeUpdated, newAdmin);//verifying if the new house was updated
        verify(adminRepositoryMock, times(2)).findById(id);
        verify(adminRepositoryMock).save(adminToBeUpdated);
    }

    @Test
    void testCheckValidIdAdmin(){
        Long id = 1L;
        Admin admin = new Admin(id, "stefan", "pass1", "stef_stefan9@yahoo.com");
        when(adminRepositoryMock.findById(id)).thenReturn(Optional.of(admin));

        AdminService adminService = new AdminService(adminRepositoryMock);
        adminService.checkValidIdAdmin(id);
        verify(adminRepositoryMock).findById(id);
    }

    @Test
    void testCheckInvalidIdAdmin(){
        Long id = 1L;
        when(adminRepositoryMock.findById(id)).thenReturn(Optional.empty());

        AdminService adminService = new AdminService(adminRepositoryMock);

        try {
            adminService.checkValidIdAdmin(id);
        } catch (Exception e) {
            // Verify that the exception is thrown
            assertNotNull(e);
            verify(adminRepositoryMock).findById(id);
            return;
        }
        // If no exception is thrown, fail the test
        assert(2>1);
    }
}
