package org.mk.controllers;

import org.mk.cache.LikeCache;
import org.mk.cache.PostCache;
import org.mk.model.Like;
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
public class LikeController {
    //private List<Like> likes = new ArrayList<>();
    private AtomicLong nextId = new AtomicLong();

    @Autowired
    private PostCache postCache;

    @Autowired
    private LikeCache likeCache;

    @GetMapping("/posts/{postId}/likes")
    @ResponseStatus(HttpStatus.OK)
    public List<Like> getAllLikesForPost(@PathVariable long postId){
        for (Post post: postCache.getAllPosts()) {
            if (post.getId() == postId)
                return post.getLikes();
        }
        throw new IllegalArgumentException();
    }

    @PostMapping("/posts/{postId}/likes")
    @ResponseStatus(HttpStatus.CREATED)
    public Like createLikeForPost(@PathVariable long postId, @RequestBody Like like){
        for (Post post: postCache.getAllPosts()){
            if(post.getId()==postId) {
                like.setId(nextId.incrementAndGet());
                like.setPostId(postId);
                like.setLikedBy("Maneesh Kumar");
                post.setLike(like);
                return like;
            }
        }
        throw new IllegalArgumentException();
    }

    // Exception Handler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST,
            reason = "Request ID not found.")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badIdExceptionHandler() {
        // Nothing to do
    }
}
