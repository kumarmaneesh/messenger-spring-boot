package org.mk.model;

/**
 * Created by 1556780 on 1/7/2019.
 */
public class Comment {
    private long id;
    private String comment;
    private String commentedBy;

    public Comment(long id, String comment,String commentedBy){
        this.id=id;
        this.comment=comment;
        this.commentedBy=commentedBy;
    }

    public Comment(){}

    public String getCommentedBy() {
        return commentedBy;
    }

    public void setCommentedBy(String commentedBy) {
        this.commentedBy = commentedBy;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
