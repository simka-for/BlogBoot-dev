package com.BlogBoot.repository;

import com.BlogBoot.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM posts "
            + "WHERE is_active = 1 AND moderator_status = 'ACCEPTED' AND time <= NOW()")
    int countSuitablePost();

    @Query(nativeQuery = true, value = "SELECT posts.id, posts.is_active, posts.moderator_status, posts.text, \n" +
            " posts.time, posts.title, posts.view_count, posts.moderator_id, posts.user_id FROM post_comments\n" +
            "LEFT JOIN posts ON post_comments.post_id = posts.id GROUP BY posts.id ORDER BY count(posts.id) DESC")
    List<Post> popularPosts(Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT posts.id, posts.is_active, posts.moderator_status, posts.text, \n" +
            " posts.time, posts.title, posts.view_count, posts.moderator_id, posts.user_id FROM post_votes\n" +
            "LEFT JOIN posts ON post_votes.post_id = posts.id GROUP BY posts.id ORDER BY count(posts.id) DESC")
    List<Post> bestPosts(Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM posts WHERE is_active = 1 "
            + "AND moderator_status = 'ACCEPTED' AND time <= NOW()")
    List<Post> suitablePosts(Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM posts WHERE is_active = 1 AND moderator_status = 'ACCEPTED'" +
            "AND time <= NOW() AND text LIKE %:query% OR title LIKE %:query%")
    List<Post> findPostByQuery(String query, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM posts WHERE is_active = 1 AND moderator_status = 'ACCEPTED'" +
            "AND time <= :endDate AND time >= :startDate")
    List<Post> findPostByDate(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT p.* FROM posts p" +
            " JOIN tag2post t2p ON t2p.post_id = p.id" +
            " JOIN tag t ON t.id = t2p.tag_id" +
            " WHERE is_active = 1 AND moderator_status = 'ACCEPTED'" +
            "AND t.name = :tag")
    List<Post> findPostByTag(String tag, Pageable pageable);
}
