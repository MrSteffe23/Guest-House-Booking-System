package com.booking.project.user;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This Class is the closest point from the user. It defines the end points of the
 * application and lets the {@link UserService} to deal with the logic behind this operations.<br>
 * All requests from the users are coming here first.
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final IUserService userService;

    /**
     * Constructor which have the role to implement Dependency Injection for the userService attribute.
     * @param userService the reference to the Service layer.
     */
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * This method is used for display purposes, or for debugging.
     * @return a list with all the Users in the database.
     */
    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    /**
     * This method is used for display purposes, or for debugging.
     * @return An user from the database.
     */
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    /**
     * Deletes a User from the database, if possible.
     * @param id the id of the User to be deleted.
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    /**
     * Inserts a new User in the database, if possible.
     * @param user JSON with all the data for a User
     */
    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }
}
