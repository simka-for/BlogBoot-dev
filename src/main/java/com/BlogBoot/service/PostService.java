package com.BlogBoot.service;

import com.BlogBoot.converter.DateConverter;
import com.BlogBoot.dto.UserBody;
import com.BlogBoot.model.Post;
import com.BlogBoot.model.enums.PostSort;
import com.BlogBoot.repository.PostCommentsRepository;
import com.BlogBoot.repository.PostRepository;
import com.BlogBoot.repository.PostVoteRepository;
import com.BlogBoot.repository.UserRepository;
import com.BlogBoot.responses.PostResponseBody;
import com.BlogBoot.dto.PostBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Sort.Direction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostVoteRepository postVoteRepository;
    private final PostCommentsRepository postCommentsRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository, PostVoteRepository postVoteRepository, PostCommentsRepository postCommentsRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postVoteRepository = postVoteRepository;
        this.postCommentsRepository = postCommentsRepository;
    }


    @Transactional(readOnly = true)
    public PostResponseBody getPosts(int offset, int limit, String mode) {

        int count = postRepository.countSuitablePost();
        PostSort postSortMode = PostSort.valueOf(mode.toUpperCase());
        Pageable pageable = PageRequest.of(offset / limit, limit);

        List<Post> postList;

        if (postSortMode == PostSort.POPULAR) {
            postList = postRepository.popularPosts(pageable);
        } else if (postSortMode == PostSort.BEST) {
            postList = postRepository.bestPosts(pageable);
        } else {

            Direction direction = Direction.valueOf("DESC");
            if (postSortMode == PostSort.EARLY) {
                direction = Direction.valueOf("ASC");
            }
            pageable = PageRequest.of(offset / limit, limit, direction, "time");
            postList = postRepository.suitablePosts(pageable);
        }
        return PostResponseBody.builder().
                count(count)
                .posts(postConvert(postList))
                .build();
    }

    @Transactional(readOnly = true)
    public PostResponseBody searchPost(int offset, int limit, String query){

        if (query.equals("")){
            return getPosts(offset,limit, "BEST");
        } else {
            Pageable pageable = PageRequest.of(offset / limit, limit);

            List<Post> searchPost = postRepository.findPostByQuery(query, pageable);
            int count = searchPost.size();

            return PostResponseBody.builder()
                    .count(count)
                    .posts(postConvert(searchPost))
                    .build();

        }
    }

    @Transactional(readOnly = true)
    public PostResponseBody postByDate(int offset, int limit, String date){

        LocalDateTime startDate = LocalDate.parse(date).atStartOfDay();
        LocalDateTime endDate = startDate.plusDays(1);

        Pageable pageable = PageRequest.of(offset / limit, limit);

        List<Post> posts = postRepository.findPostByDate(startDate, endDate, pageable);

        return PostResponseBody.builder()
                .count(posts.size())
                .posts(postConvert(posts))
                .build();
    }

    @Transactional(readOnly = true)
    public PostResponseBody postByTag(int offset, int limit, String tag){

        Pageable pageable = PageRequest.of(offset / limit, limit);

        List<Post> posts = postRepository.findPostByTag(tag, pageable);

        return PostResponseBody.builder()
                .count(posts.size())
                .posts(postConvert(posts))
                .build();
    }

    private List<PostBody> postConvert(List<Post> posts) {

        List<PostBody> finalPost = new ArrayList<>();


        for (Post post : posts) {
            int userId = post.getUser().getId();

            String userName = userRepository.findById(userId).get().getName();
            int postId = post.getId();
            String announce;

            if (post.getText().length() > 500) {
                announce = post.getText().substring(0, 499) + "...";
            } else announce = post.getText();

            UserBody userBody = UserBody.builder()
                    .id(userId)
                    .name(userName)
                    .build();

            PostBody postBody = PostBody.builder()
                    .id(post.getId())
                    .time(DateConverter.asEpochMillis(post.getTime()))
                    .userBody(userBody)
                    .title(post.getTitle())
                    .announce(announce)
                    .likeCount(postVoteRepository.countLikes(postId))
                    .dislikeCount(postVoteRepository.countDislikes(postId))
                    .commentCount(postCommentsRepository.countOfComments(postId))
                    .viewCount(post.getViewCount())
                    .build();

            finalPost.add(postBody);
        }
        return finalPost;
    }


}
