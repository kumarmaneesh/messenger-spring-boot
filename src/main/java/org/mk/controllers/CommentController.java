package org.mk.controllers;

import org.mk.cache.PostCache;
import org.mk.model.Comment;
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
public class CommentController {
    private AtomicLong nextId = new AtomicLong();

    @Autowired
    private PostCache postCache;

    @GetMapping("/posts/{postId}/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> getAllCommentsForPost(@PathVariable("postId") long postId){
        for( Post post: postCache.getAllPosts()){
            if(post.getId()==postId)
                return post.getComments();
        }
        throw new IllegalArgumentException();
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public Comment getCommentForPost(
            @PathVariable("postId") long postId,
            @PathVariable("commentId") long commentId){
        for( Post post: postCache.getAllPosts()){
            if(post.getId()==postId){
                for(Comment comment: post.getComments()){
                    if(comment.getId()==commentId){
                        return comment;
                    }
                }
            }
        }
        throw new IllegalArgumentException();
    }

    @PostMapping("/posts/{postId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createCommentForPost(@PathVariable("postId") long postId, Comment comment){
        for(Post post: postCache.getAllPosts()){
            if(post.getId()==postId){
                comment.setId(nextId.incrementAndGet());
                comment.setComment("I'm feeling lucky!");
                comment.setCommentedBy("Johny Depp");
                post.setComment(comment);
                return comment;
            }
        }
        throw new IllegalArgumentException();
    }

    @PostMapping("/posts/{postId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public Comment updateCommentForPost(
            @PathVariable("postId") long postId,
            @PathVariable("commentId") long commentId) {
        for (Post post : postCache.getAllPosts()) {
            if (post.getId() == postId) {
                for(Comment comment: post.getComments()){
                    if(comment.getId()==commentId){
                        comment.setCommentedBy("Firangi Malla");
                        return comment;
                    }
                }

                //post.removeComment(comment);
                //comment.setId(commentId);
                //comment.setComment("I'm flying...");
                //post.setComment(comment);
                //return comment;
            }
        }
        throw new IllegalArgumentException();
    }

    @DeleteMapping("posts/{postId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteCommentForPost(@PathVariable("postId") long postId,
                                        @PathVariable("commentId") long commentId){
        for(Post post: postCache.getAllPosts()){
            if(post.getId()==postId){
                for(Comment comment: post.getComments()){
                    if(comment.getId()==commentId){
                        post.removeComment(comment);
                        return true;
                    }
                }
            }
        }
        throw new IllegalArgumentException();
    }
}
