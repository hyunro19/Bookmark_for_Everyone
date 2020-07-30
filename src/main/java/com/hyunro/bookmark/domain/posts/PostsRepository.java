package com.hyunro.bookmark.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

    @Query("SELECT p FROM Posts p WHERE p.user.user_id = :user_id ORDER BY p.posts_id DESC")
    List<Posts> findAllByUserIdDesc(@Param("user_id") Long user_id);

    @Query("SELECT p FROM Posts p WHERE p.topic = :topic ORDER BY p.posts_id DESC")
    List<Posts> findAllByTopicDesc(@Param("topic") String topic);
}
