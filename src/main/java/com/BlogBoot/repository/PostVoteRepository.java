package com.BlogBoot.repository;

import com.BlogBoot.model.PostVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostVoteRepository extends JpaRepository<PostVote, Integer> {

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM post_votes WHERE value = 1 AND post_id = :postId")
    Integer countLikes(int postId);

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM post_votes WHERE value = -1 AND post_id = :postId")
    Integer countDislikes(int postId);

}
