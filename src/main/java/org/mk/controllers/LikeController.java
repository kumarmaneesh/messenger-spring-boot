package org.mk.controllers;

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

    @GetMapping("/posts/{postId}/likes")
    @ResponseStatus(HttpStatus.OK)
    public List<Like> getAllLikesForPost(@PathVariable("postId") long postId){
        for (Post post: postCache.getAllPosts()) {
            if (post.getId() == postId)
                return post.getLikes();
        }
        throw new IllegalArgumentException();
    }

    @PostMapping("/posts/{postId}/likes")
    @ResponseStatus(HttpStatus.CREATED)
    public Like createLikeForPost(@PathVariable("postId") long postId, @RequestBody Like like){
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

    @PostMapping("/posts/{postId}/likes/{likeId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Like updateLikeForPost(@PathVariable("postId") long postId, @PathVariable("likeId") long likeId){
        for (Post post: postCache.getAllPosts()){
            if(post.getId()==postId) {
                for (Like eLike : post.getLikes()) {
                    if (eLike.getId() == likeId) {
                        eLike.setLikedBy("Roger Federrer");
                        return eLike;
                    }
                }
            }
        }
        throw new IllegalArgumentException();
    }

    @DeleteMapping("/posts/{postId}/likes/{likeId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteLikeForPost(@PathVariable("postId") long postId, @PathVariable("likeId") long likeId){
        for (Post post: postCache.getAllPosts()){
            if(post.getId()==postId){
                for(Like eLike: post.getLikes()){
                    if(eLike.getId()==likeId){
                        post.removeLike(eLike);
                        return true;
                    }
                }
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
