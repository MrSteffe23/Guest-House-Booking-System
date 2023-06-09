package com.booking.project.admin;

import java.util.List;

/**
 * Interface which separates the database from the java logic. <br>
 * Here are all the methods used to interact with the lower level of database
 */
public interface IAdminService {

    /**
     * Get all the Admins in the database.
     * @return The list of the Admins found in the database.
     */
    List<Admin> getAdmins();

    /**
     * Method used to insert a new Admin in the database.
     * @param admin a new Admin to insert in the database.
     */
    void createAdmin(Admin admin);

    /**
     * Method used to delete an Admin from the database (if the Admin exists).
     * @param id the id of the Admin to be deleted.
     */
    void deleteAdmin(Long id);

    /**
     * Method used to update an Admin from the database (if the Admin exists).
     * @param id the id of the Admin to be updated.
     * @param admin the Admin with new details.
     */
    void updateAdmin(Long id, Admin admin);

    /**
     * Checks if an admin is in the database
     * @param username username of the admin to search for
     * @param password password of the admin to search for
     */
    boolean checkLoginAdmin(String username, String password);
}
