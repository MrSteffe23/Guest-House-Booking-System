package com.booking.project.user;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Class which implements the contract of the interface {@link IUserService}. <br>
 * It has the implementation of the methods for PostgreSQL database.
 * Here is all the logic of the application.
 */
@Service
public class UserService implements IUserService{
    /**
     * Attribute which represents the DataAccess layer.
     */
    private final UserRepository userRepository;

    /**
     * Constructor which have the role to implement Dependency Injection for the userRepository attribute.
     * @param userRepository the reference to the DataAccess layer.
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * This method is used for display purposes. You can see all the users in the database.
     * @return all the users in the database in a List.
     */
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * Creates a User in the database, given the data in the parameter.
     * @param user JSON with data for a user.
     */
    @Override
    public void createUser(User user) {
        validateUserame(user.getUsername());
        userRepository.save(user);
    }

    /**
     * First, this method checks to see if the database have a user with the specified id. If yes,
     * the user is being deleted, otherwise, an exception is thrown.
     * @param id the id of the User to be deleted.
     * @throws IllegalStateException if the database doesn't have a user with the specified id.
     */
    @Override
    public void deleteUser(Long id) {
        checkValidIdUser(id);
        userRepository.deleteById(id);
    }

    /**
     * Checks if the database have a User called "username". If yes, then this method throws
     * an error with a message, if not, it does nothing.
     * @param username the username of a possible User.
     * @throws IllegalStateException if the database already have a user called "username".
     */
    private void validateUserame(String username){
        Optional<User> userOptional = userRepository.getUserByusername(username);
        if(userOptional.isPresent()){
            throw new IllegalStateException(String.format("The User %s already exists. Please try another username.", username));
        }
    }

    /**
     * Functions which verifies if the database has a user with the specified id
     * @param id id of the user that you want to look for
     */
    public void checkValidIdUser(Long id){
        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent()){
            throw new IllegalStateException(String.format("The User with id %s doesn't exist.", id));
        }
    }
}
