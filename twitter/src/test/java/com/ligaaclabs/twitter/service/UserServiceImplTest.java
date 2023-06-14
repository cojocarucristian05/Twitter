package com.ligaaclabs.twitter.service;


import com.ligaaclabs.twitter.advice.exception.UserNotFoundException;
import com.ligaaclabs.twitter.mapper.UserMapper;
import com.ligaaclabs.twitter.model.dto.UserRegisterDTO;
import com.ligaaclabs.twitter.model.entities.User;
import com.ligaaclabs.twitter.repository.PostRepository;
import com.ligaaclabs.twitter.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test User Search - Returns true if User exists")
    void search_UserExists_ReturnsTrue() {
        // Arrange
        UUID userId = UUID.randomUUID();
        User user = new User();
        user.setUserId(userId);

        when(userRepository.existsById(userId)).thenReturn(true);

        // Act
        boolean result = userService.search(user);

        // Assert
        assertTrue(result);
        verify(userRepository, times(1)).existsById(userId);
    }

    @Test
    @DisplayName("Test User Search - Returns false if User does not exist")
    void search_UserDoesNotExist_ReturnsFalse() {
        // Arrange
        UUID userId = UUID.randomUUID();
        User user = new User();
        user.setUserId(userId);

        when(userRepository.existsById(userId)).thenReturn(false);

        // Act
        boolean result = userService.search(user);

        // Assert
        assertFalse(result);
        verify(userRepository, times(1)).existsById(userId);
    }

    @Test
    @DisplayName("Test User Registration - Returns OK Response")
    void registerUser_ValidUserRegisterDTO_ReturnsOkResponse() {
        // Arrange
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO("john",
                                                               "firstname",
                                                               "lastname",
                                                                  "email",
                                                               "pass");
        User user = new User();

        when(userRepository.findUserByUsername(userRegisterDTO.getUsername())).thenReturn(Optional.empty());
        when(userMapper.userRegisterDTOToUser(userRegisterDTO)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);

        // Act
        ResponseEntity<?> response = userService.registerUser(userRegisterDTO);

        // Assert
        assertEquals(ResponseEntity.ok("User registered successfully!"), response);
        verify(userRepository, times(1)).findUserByUsername(userRegisterDTO.getUsername());
        verify(userMapper, times(1)).userRegisterDTOToUser(userRegisterDTO);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    @DisplayName("Test User Registration - Returns Bad Request Response if User already exists")
    void registerUser_UserAlreadyExists_ReturnsBadRequestResponse() {
        // Arrange
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO("john",
                                                               "firstname",
                                                               "lastname",
                                                                  "email",
                                                               "pass");
        userRegisterDTO.setUsername("john");

        when(userRepository.findUserByUsername(userRegisterDTO.getUsername())).thenReturn(Optional.of(new User()));

        // Act
        ResponseEntity<?> response = userService.registerUser(userRegisterDTO);

        // Assert
        assertEquals(ResponseEntity.badRequest().body("User already exists!"), response);
        verify(userRepository, times(1)).findUserByUsername(userRegisterDTO.getUsername());
        verify(userMapper, never()).userRegisterDTOToUser(any());
        verify(userRepository, never()).save(any());
    }

    @Test
    @DisplayName("Test User Follow - Returns OK Response")
    void follow_ValidFollowerAndFollowed_ReturnsOkResponse() {
        // Arrange
        UUID idFollower = UUID.randomUUID();
        UUID idFollowed = UUID.randomUUID();
        User follower = createUser(idFollower);
        User followed = createUser(idFollowed);

        when(userRepository.findById(idFollower)).thenReturn(Optional.of(follower));
        when(userRepository.findById(idFollowed)).thenReturn(Optional.of(followed));

        // Act
        ResponseEntity<?> response = userService.follow(idFollower, idFollowed);

        // Assert
        assertEquals(ResponseEntity.ok("Follow succeed!"), response);
        assertTrue(follower.getFollowing().contains(followed));
        assertTrue(followed.getFollowers().contains(follower));
        verify(userRepository, times(2)).findById(any(UUID.class));
    }

    @Test
    @DisplayName("Test User Follow - Throws UserNotFoundException if Follower does not exist")
    void follow_FollowerNotFound_ThrowsUserNotFoundException() {
        // Arrange
        UUID idFollower = UUID.randomUUID();
        UUID idFollowed = UUID.randomUUID();

        when(userRepository.findById(idFollower)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> userService.follow(idFollower, idFollowed));
        verify(userRepository, times(1)).findById(idFollower);
        verify(userRepository, never()).findById(idFollowed);
    }

    @Test
    @DisplayName("Test User Follow - Throws UserNotFoundException if Followed does not exist")
    void follow_FollowedNotFound_ThrowsUserNotFoundException() {
        // Arrange
        UUID idFollower = UUID.randomUUID();
        UUID idFollowed = UUID.randomUUID();
        User follower = createUser(idFollower);

        when(userRepository.findById(idFollower)).thenReturn(Optional.of(follower));
        when(userRepository.findById(idFollowed)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> userService.follow(idFollower, idFollowed));
        verify(userRepository, times(1)).findById(idFollower);
        verify(userRepository, times(1)).findById(idFollowed);
    }

    @Test
    @DisplayName("Test User Follow - Returns Bad Request Response if already following the account")
    void follow_AlreadyFollowing_ReturnsBadRequestResponse() {
        // Arrange
        UUID idFollower = UUID.randomUUID();
        UUID idFollowed = UUID.randomUUID();
        User follower = createUser(idFollower);
        User followed = createUser(idFollowed);
        follower.getFollowing().add(followed);

        when(userRepository.findById(idFollower)).thenReturn(Optional.of(follower));
        when(userRepository.findById(idFollowed)).thenReturn(Optional.of(followed));

        // Act
        ResponseEntity<?> response = userService.follow(idFollower, idFollowed);

        // Assert
        assertEquals(ResponseEntity.badRequest().body("You already follow this account!"), response);
        verify(userRepository, times(2)).findById(any(UUID.class));
    }

    @Test
    @DisplayName("Test User Unfollow - Returns OK Response")
    void unfollow_ValidFollowerAndFollowed_ReturnsOkResponse() {
        // Arrange
        UUID idFollower = UUID.randomUUID();
        UUID idFollowed = UUID.randomUUID();
        User follower = createUser(idFollower);
        User followed = createUser(idFollowed);
        follower.getFollowing().add(followed);
        followed.getFollowers().add(follower);

        when(userRepository.findById(idFollower)).thenReturn(Optional.of(follower));
        when(userRepository.findById(idFollowed)).thenReturn(Optional.of(followed));

        // Act
        ResponseEntity<?> response = userService.unfollow(idFollower, idFollowed);

        // Assert
        assertEquals(ResponseEntity.ok("Unfollow succeed!"), response);
        assertFalse(follower.getFollowing().contains(followed));
        assertFalse(followed.getFollowers().contains(follower));
        verify(userRepository, times(2)).findById(any(UUID.class));
    }

    @Test
    @DisplayName("Test User Unfollow - Throws UserNotFoundException if Follower does not exist")
    void unfollow_FollowerNotFound_ThrowsUserNotFoundException() {
        // Arrange
        UUID idFollower = UUID.randomUUID();
        UUID idFollowed = UUID.randomUUID();

        when(userRepository.findById(idFollower)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> userService.unfollow(idFollower, idFollowed));
        verify(userRepository, times(1)).findById(idFollower);
        verify(userRepository, never()).findById(idFollowed);
    }

    @Test
    @DisplayName("Test User Unfollow - Throws UserNotFoundException if Followed does not exist")
    void unfollow_FollowedNotFound_ThrowsUserNotFoundException() {
        // Arrange
        UUID idFollower = UUID.randomUUID();
        UUID idFollowed = UUID.randomUUID();
        User follower = createUser(idFollower);

        when(userRepository.findById(idFollower)).thenReturn(Optional.of(follower));
        when(userRepository.findById(idFollowed)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> userService.unfollow(idFollower, idFollowed));
        verify(userRepository, times(1)).findById(idFollower);
        verify(userRepository, times(1)).findById(idFollowed);
    }

    @Test
    @DisplayName("Test User Unfollow - Returns Bad Request Response if not following the account")
    void unfollow_NotFollowing_ReturnsBadRequestResponse() {
        // Arrange
        UUID idFollower = UUID.randomUUID();
        UUID idFollowed = UUID.randomUUID();
        User follower = createUser(idFollower);
        User followed = createUser(idFollowed);

        when(userRepository.findById(idFollower)).thenReturn(Optional.of(follower));
        when(userRepository.findById(idFollowed)).thenReturn(Optional.of(followed));

        // Act
        ResponseEntity<?> response = userService.unfollow(idFollower, idFollowed);

        // Assert
        assertEquals(ResponseEntity.badRequest().body("Unfollow denied!"), response);
        verify(userRepository, times(2)).findById(any(UUID.class));
    }

    @Test
    @DisplayName("Test User Unregister - Throws UserNotFoundException if User does not exist")
    void unregister_UserNotFound_ThrowsUserNotFoundException() {
        // Arrange
        UUID userId = UUID.randomUUID();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> userService.unregister(userId));
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, never()).delete(any(User.class));
        verify(postRepository, never()).deleteAll(anyList());
    }

    private User createUser(UUID userId) {
        User user = new User();
        user.setUserId(userId);
        user.setUsername("testuser");
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setFollowers(new ArrayList<>());
        user.setFollowing(new ArrayList<>());
        user.setPosts(new ArrayList<>());
        user.setLikes(new ArrayList<>());
        user.setReplies(new ArrayList<>());
        return user;
    }
}