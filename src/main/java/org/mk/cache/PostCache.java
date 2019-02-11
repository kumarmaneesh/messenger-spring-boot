package org.mk.cache;

import org.mk.model.Post;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1556780 on 1/18/2019.
 */
@Component
public class PostCache {
    private List<Post> posts = new ArrayList<>();

    public List<Post> getAllPosts(){
        return posts;
    }

    public void addPost (Post post){
        posts.add(post);
    }
    public void removePost(Post post){
        posts.remove(post);
    }
}
