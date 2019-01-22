package org.mk.model;

/**
 * Created by 1556780 on 1/7/2019.
 */
public class Like {
    private long id;
    private long postId;
    private String likedBy;

    public Like(long id,long postId,String likedBy){
        this.postId=postId;
        this.id=id;
        this.likedBy=likedBy;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public Like(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(String likedBy) {
        this.likedBy = likedBy;
    }
}
