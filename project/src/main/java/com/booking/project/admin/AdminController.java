package com.booking.project.admin;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This Class is the closest point from the user. It defines the end points of the
 * application and lets the {@link AdminService} to deal with the logic behind this operations.<br>
 * All requests from the users are coming here first.
 */
@RestController
@RequestMapping("/api/v1/admins")
public class AdminController{
    private final IAdminService adminService;

    /**
     * Constructor which have the role to implement Dependency Injection for the adminService attribute.
     * @param adminService the reference to the Service layer.
     */
    public AdminController(IAdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * This method is used for display purposes, or for debugging.
     * @return a list with all the Admins in the database.
     */
    @GetMapping
    public List<Admin> getAdmins(){
        return adminService.getAdmins();
    }

    /**
     * Inserts a new Admin in the database, if possible.
     * @param admin JSON with all the data for an Admin
     */
    @PostMapping
    public void createAdmin(@RequestBody Admin admin){
        adminService.createAdmin(admin);

    }

    /**
     * Deletes an Admin from the database, if possible.
     * @param id the id of the Admin to be deleted.
     */
    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable Long id){
        adminService.deleteAdmin(id);
    }

    /**
     * Updates an Admin from the database with new specifications.
     * @param id the id of the Admin to be updated.
     * @param admin the Admin with new specifications.
     */
    @PutMapping("/{id}")
    public void updateClient(@RequestBody Admin admin, @PathVariable Long id){
        adminService.updateAdmin(id, admin);
    }
}
