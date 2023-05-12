package com.ligaaclabs.twitter.repository;

import org.springframework.stereotype.Repository;

@Repository
public class PostRepositoryImpl {
//        implements PostRepository{
//
//    private Map<Integer, Post> POSTS = new HashMap<>();
//    private Integer index = 0;
//
//    @Override
//    public void createPost(Post post) {
//        UUID id = UUID.fromString(index.toString());
//        post.setId(index);
//        POSTS.put(index, post);
//        index++;
//    }
//
//    @Override
//    public List<Post> getAllPosts() {
//        return POSTS.values().stream().collect(Collectors.toList());
//    }
//
//    @Override
//    public List<Post> getOwnPostsByTimestamp(User user, LocalDateTime timestamps) {
//        return POSTS.values()
//                .stream()
//                .filter(post -> post.getUser().equals(user.getUsername()))
//                .filter(post -> post.getDate().isAfter(timestamps))
//                .sorted(Comparator.comparing(Post::getDate).reversed())
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<Post> getOwnPosts(User user) {
//        return POSTS.values()
//                .stream()
//                .filter(post -> post.getUser().equals(user.getUsername()))
//                .sorted(Comparator.comparing(Post::getDate).reversed())
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public Post getPostById(Integer id) {
//        Post post = POSTS.get(id);
//        if(Objects.isNull(post)) {
//            throw new PostNotFoundException("Post not found!");
//        }
//
//        return post;
//    }


}
