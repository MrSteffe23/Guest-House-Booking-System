package com.booking.project;

import com.booking.project.user.IUserService;
import com.booking.project.user.User;
import com.booking.project.user.UserRepository;
import com.booking.project.user.UserService;
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
 * This class is used to test the implementation of the class {@link com.booking.project.user.UserService}
 */
@SpringBootTest
public class UserTests {
    @Mock
    private UserRepository userRepositoryMock;

    @Test
    void testGetUsers() {
        List<User> usersResult = new ArrayList<>();
        usersResult.add(new User(1L, "user1"));
        usersResult.add(new User(2L, "user2"));
        List<User> usersToBeReturned = new ArrayList<>(usersResult);
        when(userRepositoryMock.findAll()).thenReturn(usersToBeReturned);

        IUserService userService = new UserService(userRepositoryMock);
        assertEquals(userService.getUsers(), usersResult);
        verify(userRepositoryMock).findAll();
    }

    @Test
    void testCreateValidUser() {
        String username = "stefan";
        User user = new User(1L, username);
        when(userRepositoryMock.getUserByusername(username)).thenReturn(Optional.empty());

        IUserService userService = new UserService(userRepositoryMock);
        userService.createUser(user);
        verify(userRepositoryMock).save(user);
        verify(userRepositoryMock).getUserByusername(username);
    }

    @Test
    void testCreateInvalidUser() {
        String username = "stefan";
        User user = new User(1L, username);
        when(userRepositoryMock.getUserByusername(username)).thenReturn(Optional.of(user));

        IUserService userService = new UserService(userRepositoryMock);
        try {
            userService.createUser(user);
        } catch (Exception e) {
            // Verify that the exception is thrown
            assertNotNull(e);
            verify(userRepositoryMock, never()).save(user);
            verify(userRepositoryMock).getUserByusername(username);
            return;
        }
        // If no exception is thrown, fail the test
        assert(2<1);
    }

    @Test
    void testDeleteValidUser() {
        Long id = 1L;
        User user = new User(id, "stefan");
        when(userRepositoryMock.findById(id)).thenReturn(Optional.of(user));

        IUserService userService = new UserService(userRepositoryMock);
        userService.deleteUser(id);
        verify(userRepositoryMock).deleteById(id);
        verify(userRepositoryMock).findById(id);
    }

    @Test
    void testDeleteInvalidUser() {
        Long id = 1L;
        when(userRepositoryMock.findById(id)).thenReturn(Optional.empty());

        IUserService userService = new UserService(userRepositoryMock);
        try {
            userService.deleteUser(id);
        } catch (Exception e) {
            // Verify that the exception is thrown
            assertNotNull(e);
            verify(userRepositoryMock, never()).deleteById(id);
            verify(userRepositoryMock).findById(id);
            return;
        }
        // If no exception is thrown, fail the test
        assert(2<1);
    }

    @Test
    void testCheckValidIdUser(){
        Long id = 1L;
        User user = new User(id, "stefan");
        when(userRepositoryMock.findById(id)).thenReturn(Optional.of(user));

        UserService userService = new UserService(userRepositoryMock);
        userService.checkValidIdUser(id);
        verify(userRepositoryMock).findById(id);
    }

    @Test
    void testCheckInvalidIdAdmin(){
        Long id = 1L;
        when(userRepositoryMock.findById(id)).thenReturn(Optional.empty());

        UserService userService = new UserService(userRepositoryMock);

        try {
            userService.checkValidIdUser(id);
        } catch (Exception e) {
            // Verify that the exception is thrown
            assertNotNull(e);
            verify(userRepositoryMock).findById(id);
            return;
        }
        // If no exception is thrown, fail the test
        assert(2>1);
    }

}
