package com.BlogBoot.controller;

import com.BlogBoot.responses.PostResponseBody;
import com.BlogBoot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
public class ApiPostController {

    private final PostService postService;

    @Autowired
    public ApiPostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PostResponseBody getPosts(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "mode", defaultValue = "BEST") String mode){

        return postService.getPosts(offset, limit, mode);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public PostResponseBody searchPost(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "query", defaultValue = "") String query){

        return postService.searchPost(offset, limit, query);
    }




}
