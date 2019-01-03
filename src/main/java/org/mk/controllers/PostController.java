package org.mk.controllers;

import org.mk.model.Post;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by 1556780 on 1/3/2019.
 */
@RestController
public class PostController {
    private List<Post> posts = new ArrayList<>();
    private AtomicLong nextId = new AtomicLong();

    @GetMapping("/posts")
    public List<Post> getAllPosts(){
        return posts;
    }

    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createNewPost(@RequestBody Post post){
        post.setId(nextId.incrementAndGet());
        posts.add(post);
        return post;
    }
}
