package org.mk.controllers;

import org.mk.cache.PostCache;
import org.mk.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by 1556780 on 1/3/2019.
 */
@RestController
public class PostController {
    //private List<Post> posts = new ArrayList<>();
    private AtomicLong nextId = new AtomicLong();
    @Autowired
    private PostCache postCache;

    @GetMapping("/posts")
    public List<Post> getAllPosts(){
        return postCache.getAllPosts();
    }

    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createNewPost(@RequestBody Post post){
        post.setId(nextId.incrementAndGet());
        postCache.addPost(post);
        return post;
    }

    @GetMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post getOnePost(@PathVariable long id){
        for(Post post: postCache.getAllPosts()) {
            if(post.getId()==id)
            return post;
        }
        throw new IllegalArgumentException();
    }

    @PostMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Post updatePost(
            @PathVariable("id") long id,
            @RequestBody Post newPost
    ){
        for(Post post: postCache.getAllPosts()) {
            if(post.getId()==id){
                postCache.removePost(post);
                newPost.setId(id);
                postCache.addPost(newPost);
                return newPost;
            }
        }
        throw new IllegalArgumentException();
    }

    @DeleteMapping("/posts/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean deletePost(@PathVariable("postId") long postId){
        for(Post post: postCache.getAllPosts()){
            if(post.getId()==postId){
                postCache.removePost(post);
                return true;
            }
        }
        throw new IllegalArgumentException();
    }

    // Create Exception Handle
    @ResponseStatus(value = HttpStatus.BAD_REQUEST,
            reason = "Request ID not found.")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badIdExceptionHandler() {
        // Nothing to do
    }
}
