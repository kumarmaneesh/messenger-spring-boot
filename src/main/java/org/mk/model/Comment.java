package org.mk.model;

/**
 * Created by 1556780 on 1/7/2019.
 */
public class Comment {
    private long id;
    private String comment;

    public Comment(long id, String comment){
        this.id=id;
        this.comment=comment;
    }

    public Comment(){}

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
