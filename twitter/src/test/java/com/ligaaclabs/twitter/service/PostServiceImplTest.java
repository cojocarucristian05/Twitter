package com.ligaaclabs.twitter.service;

import com.ligaaclabs.twitter.advice.exception.PostNotFoundException;
import com.ligaaclabs.twitter.advice.exception.UserNotFoundException;
import com.ligaaclabs.twitter.mapper.LikeMapper;
import com.ligaaclabs.twitter.mapper.PostMapper;
import com.ligaaclabs.twitter.mapper.ReplyMapper;
import com.ligaaclabs.twitter.model.dto.PostResponseDTO;
import com.ligaaclabs.twitter.model.dto.ReplyDTO;
import com.ligaaclabs.twitter.model.entities.Post;
import com.ligaaclabs.twitter.model.entities.Reply;
import com.ligaaclabs.twitter.model.entities.User;
import com.ligaaclabs.twitter.repository.LikeRepository;
import com.ligaaclabs.twitter.repository.PostRepository;
import com.ligaaclabs.twitter.repository.ReplyRepository;
import com.ligaaclabs.twitter.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private LikeRepository likeRepository;

    @Mock
    private ReplyRepository replyRepository;

    @Mock
    private PostMapper postMapper;

    @Mock
    private LikeMapper likeMapper;

    @Mock
    private ReplyMapper replyMapper;

    @InjectMocks
    private PostServiceImpl postService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test Add Post with Valid User and Content - Returns OK Response")
    void addPost_ValidUserAndContent_ReturnsOkResponse() {
        // Arrange
        UUID userId = UUID.randomUUID();
        String content = "Test post content";
        User user = new User();
        user.setPosts(new ArrayList<>()); // Initialize posts list
        user.setUserId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(postRepository.save(any(Post.class))).thenReturn(new Post());

        // Act
        ResponseEntity<?> response = postService.addPost(userId, content);

        // Assert
        assertEquals(ResponseEntity.ok("Post added!"), response);
        verify(userRepository, times(1)).findById(userId);
        verify(postRepository, times(1)).save(any(Post.class));
    }

    @Test
    @DisplayName("Test Add Post with Invalid User - Returns Bad Request Response")
    void addPost_InvalidUser_ReturnsBadRequestResponse() {
        // Arrange
        UUID userId = UUID.randomUUID();
        String content = "Test post content";
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act
        assertThrows(UserNotFoundException.class, () -> postService.addPost(userId, content));

        // Assert
        verify(userRepository, times(1)).findById(userId);
        verify(postRepository, never()).save(any(Post.class));
    }

    @Test
    @DisplayName("getOwnPostsByTimestamp with valid user and null timestamp should return PostResponseDTO list")
    void testGetOwnPostsByTimestamp_WithValidUserAndNullTimestamp_ReturnsPostResponseDTOList() {
        UUID userId = UUID.randomUUID();
        LocalDateTime timestamps = null;

        User user = createUser(userId);
        List<Post> posts = List.of(
                createPost(LocalDateTime.now().minusDays(2)),
                createPost(LocalDateTime.now().minusDays(1)),
                createPost(LocalDateTime.now().minusHours(12)),
                createPost(LocalDateTime.now().minusHours(6)),
                createPost(LocalDateTime.now().minusHours(3)),
                createPost(LocalDateTime.now().minusHours(1))
        );

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(postRepository.findPostsByUser(user)).thenReturn(posts);

        List<PostResponseDTO> result = postService.getOwnPostsByTimestamp(userId, timestamps);

        assertEquals(posts.size(), result.size());
        verify(userRepository, times(1)).findById(userId);
        verify(postRepository, times(1)).findPostsByUser(user);
    }

    @Test
    @DisplayName("getOwnPostsByTimestamp with valid user and non-null timestamp should return filtered PostResponseDTO list")
    void testGetOwnPostsByTimestamp_WithValidUserAndNonNullTimestamp_ReturnsFilteredPostResponseDTOList() {
        UUID userId = UUID.randomUUID();
        LocalDateTime timestamps = LocalDateTime.now().minusDays(1);

        User user = createUser(userId);
        List<Post> posts = List.of(
                createPost(LocalDateTime.now().minusDays(2)),
                createPost(LocalDateTime.now().minusDays(1)),
                createPost(LocalDateTime.now().minusHours(12)),
                createPost(LocalDateTime.now().minusHours(6)),
                createPost(LocalDateTime.now().minusHours(3)),
                createPost(LocalDateTime.now().minusHours(1))
        );
        List<Post> filteredPosts = List.of(posts.get(0), posts.get(2), posts.get(3), posts.get(4)); // Filtered posts

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(postRepository.findPostsByUser(user)).thenReturn(posts);

        List<PostResponseDTO> result = postService.getOwnPostsByTimestamp(userId, timestamps);

        assertEquals(filteredPosts.size(), result.size());
        verify(userRepository, times(1)).findById(userId);
        verify(postRepository, times(1)).findPostsByUser(user);
    }

    @Test
    @DisplayName("getOwnPostsByTimestamp with invalid user should throw UserNotFoundException")
    void testGetOwnPostsByTimestamp_WithInvalidUser_ThrowsUserNotFoundException() {
        UUID userId = UUID.randomUUID();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () ->
                postService.getOwnPostsByTimestamp(userId, null));

        verify(userRepository, times(1)).findById(userId);
        verify(postRepository, never()).findPostsByUser(any());
    }

    @Test
    @DisplayName("Test getFeed with invalid user - throws UserNotFoundException")
    void testGetFeed_WithInvalidUser_ThrowsUserNotFoundException() {
        UUID userId = UUID.randomUUID();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> postService.getFeed(userId));

        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    @DisplayName("Test Add Reply with Invalid User - Throws UserNotFoundException")
    void addReply_InvalidUser_ThrowsUserNotFoundException() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UUID postId = UUID.randomUUID();
        ReplyDTO replyDTO =  new ReplyDTO("Nice post!", true, userId, postId);

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Assert
        assertThrows(UserNotFoundException.class, () -> postService.addReply(replyDTO));
        verify(postRepository, never()).findById(any(UUID.class));
        verify(replyRepository, never()).save(any(Reply.class));
    }

    @Test
    @DisplayName("Test Get All Posts - Returns List of Posts")
    void getAllPosts_ReturnsListOfPosts() {
        // Arrange
        Post post1 = createPost(UUID.randomUUID());
        Post post2 = createPost(UUID.randomUUID());

        when(postRepository.findAll()).thenReturn(Arrays.asList(post1, post2));

        PostResponseDTO postResponseDTO1 = new PostResponseDTO(
                post1.getPostId(),
                "Sample post",
                LocalDateTime.now(),
                new ArrayList<>(),
                new ArrayList<>()
        );
        PostResponseDTO postResponseDTO2 = new PostResponseDTO(
                post2.getPostId(),
                "Sample post",
                LocalDateTime.now(),
                new ArrayList<>(),
                new ArrayList<>()
        );

        when(postMapper.postToPostResponseDTO(post1)).thenReturn(postResponseDTO1);
        when(postMapper.postToPostResponseDTO(post2)).thenReturn(postResponseDTO2);

        // Act
        List<PostResponseDTO> result = postService.getAllPosts();

        // Assert
        assertEquals(2, result.size());
        assertEquals(postResponseDTO1, result.get(0));
        assertEquals(postResponseDTO2, result.get(1));
    }

    // Helper method to create a User instance
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

    private Post createPost(UUID postId) {
       Post post = new Post();
       post.setPostId(postId);
       return post;
    }

    private Post createPost(LocalDateTime postDate) {
        Post post = new Post();
        post.setPostDate(postDate);
        return post;
    }

    private Reply createReply() {
        return new Reply();
    }
}
