package com.booking.project.user;

import java.util.List;

/**
 * Interface which separates the database from the java logic. <br>
 * Here are all the methods used to interact with the lower level of database
 */
public interface IUserService {

    /**
     * Get all the Users in the database
     * @return The list of the Users found in the database
     */
    List<User> getUsers();

    /**
     * Get a User in the database
     * @param id the id of the User to be returned.
     * @return A User with the specified id found in the database.
     */
    User getUser(Long id);

    /**
     * Method used to insert a new User in the database
     * @param user a new User to insert in the database
     */
    User createUser(User user);

    /**
     * Method used to delete a User from the database (if the User exists).
     * @param id the id of the User to be deleted.
     */
    void deleteUser(Long id);
}
