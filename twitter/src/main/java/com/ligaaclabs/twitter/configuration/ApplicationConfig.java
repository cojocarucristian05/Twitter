package com.ligaaclabs.twitter.configuration;

import com.ligaaclabs.twitter.controller.PostController;
import com.ligaaclabs.twitter.controller.UserController;
import com.ligaaclabs.twitter.model.Post;
import com.ligaaclabs.twitter.model.User;
import com.ligaaclabs.twitter.repository.PostRepository;
import com.ligaaclabs.twitter.repository.UserRepository;
import com.ligaaclabs.twitter.service.PostServiceImpl;
import com.ligaaclabs.twitter.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Configuration
public class ApplicationConfig {

    @Bean
    public UserController getUserController() {
        return new UserController(getUserService(), getPostService());
    }

    @Bean
    public PostController getPostController() {
        return new PostController(getPostService(), getUserService());
    }

    @Bean
    public UserServiceImpl getUserService() {
        return new UserServiceImpl(getUserRepository());
    }

    @Bean
    public PostServiceImpl getPostService() {
        return new PostServiceImpl(getUserRepository(), getPostRepository());
    }

    @Bean
    public PostRepository getPostRepository() {
        return new PostRepository() {
            @Override
            public List<Post> findByUserId(UUID userId) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends Post> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends Post> List<S> saveAllAndFlush(Iterable<S> entities) {
                return null;
            }

            @Override
            public void deleteAllInBatch(Iterable<Post> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<UUID> uuids) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public Post getOne(UUID uuid) {
                return null;
            }

            @Override
            public Post getById(UUID uuid) {
                return null;
            }

            @Override
            public Post getReferenceById(UUID uuid) {
                return null;
            }

            @Override
            public <S extends Post> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends Post> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public <S extends Post> List<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public List<Post> findAll() {
                return null;
            }

            @Override
            public List<Post> findAllById(Iterable<UUID> uuids) {
                return null;
            }

            @Override
            public <S extends Post> S save(S entity) {
                return null;
            }

            @Override
            public Optional<Post> findById(UUID uuid) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(UUID uuid) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(UUID uuid) {

            }

            @Override
            public void delete(Post entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends UUID> uuids) {

            }

            @Override
            public void deleteAll(Iterable<? extends Post> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public List<Post> findAll(Sort sort) {
                return null;
            }

            @Override
            public Page<Post> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Post> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends Post> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Post> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends Post> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends Post, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }
        };
    }

    @Bean
    public UserRepository getUserRepository() {
        return new UserRepository() {
            @Override
            public Optional<User> findByUsername(String username) {
                return Optional.empty();
            }

            @Override
            public List<User> findByUsernameOrFirstnameOrLastname(String username, String firstname, String lastname) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends User> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends User> List<S> saveAllAndFlush(Iterable<S> entities) {
                return null;
            }

            @Override
            public void deleteAllInBatch(Iterable<User> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<UUID> uuids) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public User getOne(UUID uuid) {
                return null;
            }

            @Override
            public User getById(UUID uuid) {
                return null;
            }

            @Override
            public User getReferenceById(UUID uuid) {
                return null;
            }

            @Override
            public <S extends User> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public <S extends User> List<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public List<User> findAll() {
                return null;
            }

            @Override
            public List<User> findAllById(Iterable<UUID> uuids) {
                return null;
            }

            @Override
            public <S extends User> S save(S entity) {
                return null;
            }

            @Override
            public Optional<User> findById(UUID uuid) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(UUID uuid) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(UUID uuid) {

            }

            @Override
            public void delete(User entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends UUID> uuids) {

            }

            @Override
            public void deleteAll(Iterable<? extends User> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public List<User> findAll(Sort sort) {
                return null;
            }

            @Override
            public Page<User> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends User> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends User> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends User> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends User, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }
        };
    }

}
