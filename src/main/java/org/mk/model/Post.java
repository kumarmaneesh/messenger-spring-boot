package org.mk.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1556780 on 1/3/2019.
 */
public class Post {
    private long id;
    private String message;
    private List<Like> likes = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();

    Post(long id, String message){
        this.id=id;
        this.message=message;
    }

    Post(){
    }

    public void setLike(Like like) {
        this.likes.add(like);
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComment(Comment comment) {
        this.comments.add(comment);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void removeLike(Like like){
        likes.remove(like);
    }
}
