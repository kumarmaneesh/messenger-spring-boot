package org.mk.model;

/**
 * Created by 1556780 on 1/3/2019.
 */
public class Post {
    private long id;
    private String message;

    Post(long id, String message){
        this.id=id;
        this.message=message;
    }

    Post(){
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
}
