package com.mdrahman.stockbull.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.mdrahman.stockbull.model.User;
import com.mdrahman.stockbull.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearchByEmail() {
        // Create some mock User objects
        User user1 = new User("John", "Doe", "john@example.com", "password");
        User user2 = new User("Jane", "Smith", "jane@example.com", "password");

        // Create a mock list of users with the search email
        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(user1);
        mockUsers.add(user2);

        // Mock the behavior of the userRepository.findByEmailContainingIgnoreCase() method
        String searchEmail = "example.com";
        when(userRepository.findByEmailContainingIgnoreCase(searchEmail)).thenReturn(mockUsers);

        // Call the method under test
        List<User> foundUsers = userService.searchByEmail(searchEmail);

        // Verify the result
        assertNotNull(foundUsers);
        assertEquals(2, foundUsers.size());
        assertEquals(user1.getFirstName(), foundUsers.get(0).getFirstName());
        assertEquals(user1.getLastName(), foundUsers.get(0).getLastName());
        assertEquals(user1.getEmail(), foundUsers.get(0).getEmail());
        assertEquals(user2.getFirstName(), foundUsers.get(1).getFirstName());
        assertEquals(user2.getLastName(), foundUsers.get(1).getLastName());
        assertEquals(user2.getEmail(), foundUsers.get(1).getEmail());

        // Verify that the userRepository.findByEmailContainingIgnoreCase() method was called once
        verify(userRepository, times(1)).findByEmailContainingIgnoreCase(searchEmail);
    }
}

