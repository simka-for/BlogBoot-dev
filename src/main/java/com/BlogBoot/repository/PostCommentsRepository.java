package com.BlogBoot.repository;

import com.BlogBoot.model.PostComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostCommentsRepository extends JpaRepository<PostComments, Integer> {

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM post_comments WHERE post_id = :postId")
    Integer countOfComments(int postId);

}
